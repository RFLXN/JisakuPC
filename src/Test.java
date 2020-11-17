import db.MySQLConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) {
        MySQLConnector connector = new MySQLConnector();
        try {
            Connection connection
                    = connector.getConnection("jdbc:mysql://localhost/jisaku?&serverTimezone=UTC", "jisaku", "jisaku");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product_table");

            while (resultSet.next()) {
                String[] s = new String[6];
                for (int i=0 ; i<6 ; i++) {
                    s[i] = resultSet.getString(i+1);
                }

                for(String st : s) {
                    System.out.print(st + "\t");
                }
                System.out.println();
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
