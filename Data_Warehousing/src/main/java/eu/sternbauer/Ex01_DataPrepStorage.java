package eu.sternbauer;

import org.apache.spark.sql.Dataset;

public class Ex01_DataPrepStorage {
    public static void main(String[] args) {
        CommonUtils utils = new CommonUtils("Ex01");
        String dirPath01 = "../../maildir/allen-p/_sent_mail";
        String parPath01 = "data/parquet1";

        //Read data from path and write to parquet file
        Dataset<Email> emails = utils.getMailDatasetStatic(dirPath01);

        //Store mails into parquet
        utils.storeParquet(emails, parPath01);

        //Read mails again from parquet
        emails = utils.readParquets(parPath01);
        emails.show();
    }
}
