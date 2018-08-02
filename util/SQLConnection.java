package sample.no.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Connects to the DB.
 * Can also create a DB if one is not found as per default SQLite function.
 */
public class SQLConnection {

    public static Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:invoiceDB.db");
            return conn;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}
