import resources.csv.CsvDbInserter;
import resources.csv.CsvProduct;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            CsvDbInserter csvDbInserter = new CsvDbInserter();
            csvDbInserter.insertAll();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
