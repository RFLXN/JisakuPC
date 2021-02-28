import resources.csv.CsvDbInserter;

public class Test {
    public static void main(String[] args) {
        try {
            CsvDbInserter inserter = new CsvDbInserter();
            inserter.insertAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
