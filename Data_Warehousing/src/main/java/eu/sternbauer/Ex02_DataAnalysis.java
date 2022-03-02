package eu.sternbauer;

import java.sql.Timestamp;

public class Ex02_DataAnalysis {
    public static void main(String[] args) {
        CommonUtils utils = new CommonUtils("Ex02");
        String parPath01 = "data/parquet1";
        String jsoPath01 = "data/json1";
        String jsoPath02 = "data/json2";

        Timestamp from = Timestamp.valueOf("2000-01-12 05:00:00");
        Timestamp to = Timestamp.valueOf("2002-12-5 12:00:00");

        utils.getAvgLengthAndRec(parPath01, from, to, jsoPath01);
        utils.getAvgLengthAndRecforSender(parPath01, from, to, jsoPath02);
    }
}
