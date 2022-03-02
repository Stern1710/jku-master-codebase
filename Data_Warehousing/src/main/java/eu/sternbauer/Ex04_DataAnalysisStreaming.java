package eu.sternbauer;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.streaming.*;

import java.util.concurrent.TimeoutException;

import static org.apache.spark.sql.functions.*;

public class Ex04_DataAnalysisStreaming {
    public static void main(String[] args) {
        String generatedPath = "../../mailGenOutput";
        CommonUtils utils = new CommonUtils("Ex04");
        Dataset<Email> dataset = utils.getMailDatasetStream(generatedPath);

        Dataset<Row> resultStream1 = dataset
            .groupBy("from")
            .count()
            .orderBy(col("count").desc())
            .limit(5);

        Dataset<Row> resultStream2 = dataset
            .select(
                    col("from"),
                    functions.size(split(dataset.col("body"), " ")).alias("body_length")
            )
            .groupBy("from")
            .sum("body_length")
            .orderBy(col("SUM(body_length)").desc())
            .limit(5);

        Dataset<Row> resultStream3 = dataset.withColumn("allWords", split(col("body"), " "))
                .withColumn("allWords", array_remove(col("allWords"), ""))
                .withColumn("allWords", array_remove(col("allWords"), " "))
                .groupBy(window(col("date"), "60 seconds", "20 seconds"), col("allWords"))
                .count()
                .orderBy(col("count").desc())
                .limit(5);

        try {
            resultStream1.toJSON().writeStream()
                .trigger(Trigger.ProcessingTime("10 seconds"))
                .outputMode(OutputMode.Complete())
                .format("console")
                .option("truncate", "false")
                .option("path", "data/stream_json_01")
                .start();

            resultStream2.toJSON().writeStream()
                .trigger(Trigger.ProcessingTime("10 seconds"))
                .outputMode(OutputMode.Complete())
                .format("console")
                .option("truncate", "false")
                .option("path", "data/stream_json_02")
                .start();

            resultStream3.toJSON().writeStream()
                .trigger(Trigger.ProcessingTime("10 seconds"))
                .outputMode(OutputMode.Complete())
                .format("console")
                .option("truncate", "false")
                .option("path", "data/stream_json_03")
                .start();

            // run the stream processing until the application is terminated from outside.
            utils.getSparkSession().streams().awaitAnyTermination();
        } catch (TimeoutException | StreamingQueryException e) {
            e.printStackTrace();
        }
    }
}
