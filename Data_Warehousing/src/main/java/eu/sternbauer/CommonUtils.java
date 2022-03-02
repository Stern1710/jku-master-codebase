package eu.sternbauer;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.*;

/**
 * Contains all methods needed for data analysis, including the access to spark sessions and the data schema
 */
public class CommonUtils implements Serializable {
    // required columns
    private static final Pattern PT_ID = Pattern.compile("^Message-ID: <([\\w.@\\-]+)>$", Pattern.MULTILINE);
    private static final Pattern PT_DATE = Pattern.compile("^Date: (.*)$", Pattern.MULTILINE);
    private static final Pattern PT_FROM = Pattern.compile("^From: ([\\w@.]+)$", Pattern.MULTILINE);
    private static final Pattern PT_SUBJECT = Pattern.compile("^Subject: (.*)$", Pattern.MULTILINE);
    private static final Pattern PT_BODY = Pattern.compile("\\n\\n([\\s\\S]*)", Pattern.MULTILINE);

    // optional columns
    private static final Pattern PT_TO = Pattern.compile("^To: ([\\w@.,\\-_\\s<>?]+)$", Pattern.MULTILINE);
    private static final Pattern PT_CC = Pattern.compile("^Cc: ([\\w@.,\\s]+)$", Pattern.MULTILINE);
    private static final Pattern PT_BCC = Pattern.compile("^Bcc:\\s*([\\w@.,\\s]+)$", Pattern.MULTILINE);
    private static final Pattern PT_XTO = Pattern.compile("^X-to: ([\\w@.,\\-_\\s<>?]+)$", Pattern.MULTILINE);
    private static final Pattern PT_XCC = Pattern.compile("^X-cc: ([\\w@.,\\s]+)$", Pattern.MULTILINE);
    private static final Pattern PT_XBCC = Pattern.compile("^X-bcc: ([\\w@.,\\s]+)$", Pattern.MULTILINE);

    // Pattern for the recipient field
    private static final Pattern[] PT_RECIPIENTS = new Pattern[]{
            PT_TO, PT_CC, PT_BCC, PT_XTO, PT_XCC, PT_XBCC
    };
    //Formatter to get a date time format
    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z (zzz)", Locale.US);

    //Table name for filtering and aggregating
    private final String TEMP_TABLE = "tbl_temp";

    //Spark session and the data schema
    private final SparkSession session;
    private final StructType schema;

    /**
     * Starts a local spark session and constructs the schema
     * @param description AppName of the spark session
     */
    public CommonUtils(String description) {
        print("creating session");
        session = SparkSession.builder()
                .appName(description)
                .master("local[*]")
                .getOrCreate();

        schema = DataTypes.createStructType(
            new StructField[]{
                DataTypes.createStructField(
                        "date",
                        DataTypes.TimestampType,
                        false
                ),
                DataTypes.createStructField(
                        "body",
                        DataTypes.StringType,
                        true
                ),
                DataTypes.createStructField(
                        "from",
                        DataTypes.StringType,
                        false
                ),
                DataTypes.createStructField(
                        "id",
                        DataTypes.StringType,
                        false
                ),
                DataTypes.createStructField(
                        "recipients",
                        DataTypes.createArrayType(DataTypes.StringType, false),
                        false
                ),
                DataTypes.createStructField(
                        "subject",
                        DataTypes.StringType,
                        true
                )
            }
        );
        print("created session");
    }

    /*
    --------------- Shared functions between different parts of the program ---------------
     */

    /**
     * Returns the SparkSession
     * @return the spark session
     */
    SparkSession getSparkSession() {
        return session;
    }

    /**
     * Creates a Dataset of {@link eu.sternbauer.Email} from a given file path string
     * @param path Path to the emails, reads everything from this directory (not sub-dirs)
     * @return a {@link Dataset} of Email items
     */
    Dataset<Email> getMailDatasetStatic (String path) {
        print("Loading mail from: " + path);
        return getMailData(session.read().option("wholetext", true).text(path));
    }

    /**
     * Creates a Dataset of {@link eu.sternbauer.Email} from a given streaming path string
     * @param path Path to the emails, reads everything from this stream
     * @return a {@link Dataset} of Email items
     */
    Dataset<Email> getMailDatasetStream (String path) {
        print("Loading stream mail from: " + path);
        return getMailData(session.readStream().option("wholetext", true).text(path));
    }

    /**
     * Reads mail from given Row-Dataset and converts it to a Dateset of Email items.
     * Filters out each mail which does not satisfy the constraints on id, date, from and recipients being non-null.<br />
     * This and the methods it calls are kept private such that no data from the Email-Set is associated by Java with
     * this class and to not cause troubles when saving data into parquet file(s)
     * @param plainMail input Dataset of type Row
     * @return A Dataset of type Email
     */
    private static Dataset<Email> getMailData (Dataset<Row> plainMail) {
        Dataset<Email> email =  plainMail.map(
                (MapFunction<Row, Email>) row -> parseFromPlainText(row.getAs("value")),
                Encoders.bean(Email.class));
        return email.filter(email.col("id").isNotNull()
                .and(email.col("date").isNotNull())
                .and(email.col("from").isNotNull())
                .and(email.col("recipients").isNotNull())
        );
    }

    /**
     * Creates an {@link eu.sternbauer.Email} object out of a given String
     * @param content String content which contains all the data of an email
     * @return An email object
     */
    private static Email parseFromPlainText(String content) {
        String id = parseColumn(PT_ID, content);
        String dateString = parseColumn(PT_DATE, content);
        Timestamp date = null;
        if (dateString != null) {
            if (!dateString.contains("(PST)") && !dateString.contains("(PDT)")) {
                date = Timestamp.valueOf(LocalDateTime.parse(dateString));
            } else {
                date = Timestamp.valueOf(LocalDateTime.parse(dateString, DT_FORMATTER));
            }
        }
        String from = parseColumn(PT_FROM, content);
        List<String> recipients = parseAllRecipients(content);
        String subject = parseColumn(PT_SUBJECT, content);
        String body = parseColumn(PT_BODY, content);

        if (recipients.size() <= 0) recipients = null;
        return new Email(id, date, from, recipients, subject, body);
    }

    /**
     * Streams the potential array of recipients and creates a List of type string out of it
     * @param content
     * @return All recipients, no null values
     */
    private static List<String> parseAllRecipients(final String content) {
        return Arrays.stream(PT_RECIPIENTS)
                .map(p -> parseColumn(p, content))
                .filter(Objects::nonNull)
                .flatMap(
                        rs -> Arrays.stream(rs.split(","))
                                .map(String::trim)
                                .filter(s -> !s.isEmpty()))
                .collect(Collectors.toList());
    }

    /**
     * Gets a single column out of the content string. Is static such that it can be used in java streams
     * @param pattern Pattern that is searched in the content string
     * @param content String with hopefully the content specified in pattern
     * @return Returns the data on group position 1 of the matcher
     */
    private static String parseColumn(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        return matcher.find() ? matcher.group(1) : null;
    }


    /**
     * Reads in parquets file from one or more paths and unions them all into a single Dataset of Emails
     * @param paths Array of 1 to many paths which containt parquet files
     * @return Dataset of Email containg all content of the found parquet files.
     */
    Dataset<Email> readParquets(String ... paths) {
        print("Loading data from parquets from: " + String.join(", ", paths));
        Dataset<Email> allMail = null;
        for (String path : paths) {
            Dataset<Email> mail =  session.read().schema(schema).parquet(path).as(Encoders.bean(Email.class));
            if (allMail == null) {
                allMail = mail;
            } else {
                allMail = allMail.union(mail);
            }
        }

        return allMail;
    }

    /**
     * Writes the given dataset object into parquet file(s) in the given path
     * @param dataset dataset to be stored
     * @param path Location to store the parquet file to
     */
    static void storeParquet(Dataset<Email> dataset, String path) {
        print("Storing data to parquets at: " + path);
        dataset.coalesce(1).write().mode(SaveMode.Overwrite).parquet(path);
    }

    /**
     * Static function to print out a given message and lead+trail it with dashes.
     * Makes it easier to do that for manually written messages to be visible instead
     * of writing this string all the time.
     * @param message Message to be printed with leading+trailing dashes
     */
    static void print(String message) {
        System.out.println("------ " + message + " ------");
    }

    /*
    --------------- Specific for Ex02 ---------------
     */

    void getAvgLengthAndRec (String inputPath, Timestamp from, Timestamp to, String outputPath) {
        print("Generating avg length of mail body and avg number of recipients. Read from: " + inputPath
                + "; Output json to: " + outputPath);
        Dataset<Row> dfMail = getFilteredData(inputPath, from, to);
        dfMail = dfMail.select(
            col("id"),
            functions.size(functions.split(dfMail.col("body"), " ")).alias("msg_length"),
            functions.size(dfMail.col("recipients")).alias("n_recipients"));

        dfMail.createOrReplaceTempView(TEMP_TABLE);
        String sqlS = "SELECT CURRENT_TIMESTAMP as timestamp, AVG(msg_length) AS avgLength, AVG(n_recipients) AS avgNoOfRecipients FROM " + TEMP_TABLE;
        Dataset<Row> avgSet = session.sql(sqlS);
        avgSet.show();

        //Write to json-file
        avgSet.coalesce(1).write().mode(SaveMode.Overwrite).json(outputPath);
    }

    void getAvgLengthAndRecforSender (String inputPath, Timestamp from, Timestamp to, String outputPath) {
        print("Generating avg length of mail body and avg number of recipients per sender. Read from: " + inputPath
                + "; Output json to: " + outputPath);
        Dataset<Row> dfMail = getFilteredData(inputPath, from, to);
        dfMail = dfMail.select(
                col("id"),
                col("from"),
                functions.size(functions.split(dfMail.col("body"), " ")).alias("msg_length"),
                functions.size(dfMail.col("recipients")).alias("n_recipients")
        );

        dfMail.createOrReplaceTempView(TEMP_TABLE);
        String sqlS = "SELECT CURRENT_TIMESTAMP as timestamp, e.from, AVG(e.msg_length) AS avgMsgLen, AVG(e.n_recipients) AS avgNoOfRecipients FROM " + TEMP_TABLE + " e GROUP BY e.from";
        Dataset<Row> avgSet = session.sql(sqlS);
        avgSet.show();
        avgSet = avgSet.select(
                col("timestamp"),
                struct(col("from"), col("avgMsgLen"), col("avgNoOfRecipients")).alias("statistics")
            )
            .select("timestamp","statistics")
            .groupBy("timestamp")
            .agg(collect_list("statistics").alias("statistics"));

        avgSet.show();
        //Write to json-file
        avgSet.coalesce(1).write().mode(SaveMode.Overwrite).json(outputPath);
    }

    /**
     * Loads the data from a parquet and filters the data for the right timespan
     * @param inputPath Path to the parquits
     * @param from Timestamp from which mails should be considered
     * @param to Timestamp to which mails should be considered
     * @return Dataset of Row type
     */
    private Dataset<Row> getFilteredData(String inputPath, Timestamp from, Timestamp to) {
        print(String.format("Filtering between %s and %s", from, to));
        Dataset<Email> emails = readParquets(inputPath);
        return emails.filter(emails.col("date").between(from, to)).toDF();
    }
}
