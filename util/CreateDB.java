package sample.no.util;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Able to run commands from an SQL file
 * Is expected to run files containing commands for creating tables.
 */
public class CreateDB {

    private static final String DRIVER_NAME = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:invoiceDB.db";

    /**
     * Constructor for database.
     */
    public CreateDB() {

        //Check if database-file is already created
        File existing = new File("invoiceDB.db");
        Boolean isExisting = existing.exists();

        //Stop constructor from running if a copy already exists
        if (!isExisting) {
            try {
                System.out.println("Database file has been created");
                resetDatabase();
            } catch (SQLException e) {
                System.out.println("Error occurred at Database class, sample.no.util.CreateDB.java");
                e.printStackTrace();
            }
        } else {
            System.out.println("File already exists");
        }
    }

    static {
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("*** Driver loaded");
        } catch (Exception e) {
            System.out.println("*** Error : " + e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void resetDatabase() throws SQLException {
        String s = new String();
        StringBuffer sb = new StringBuffer();

        try {
            FileReader fr = new FileReader(new File("oblig3v1_database.sql"));

            BufferedReader br = new BufferedReader(fr);

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();

            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");

            Connection c = CreateDB.getConnection();
            Statement st = c.createStatement();

            for (int i = 0; i < inst.length; i++) {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if (!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>" + inst[i]);
                }
            }

        } catch (Exception e) {
            System.out.println("*** Error : " + e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }

    }

}