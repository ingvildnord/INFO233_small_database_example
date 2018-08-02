package sample.no.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.no.util.SQLConnection;

import java.sql.*;
/**
 * Implementation of DAO for Customer table
 * AddressDAO, CategoryDAO, CustomerDAO, InvoiceDAO and ProductDAO are all similar
 * aside from what table and DTO they utilize
 */
public class CustomerDAO{

    /**
     * To pass all known information to GUI tableview
     * @return ObservableList a list to be used by the responsible controller class
     */
    public static ObservableList<Customer> getAllEntries() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        ObservableList list = null;
        String query = "SELECT * FROM customer";
        try {

            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement(query);
            res = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (res.next()) {
                list.add((new Customer(
                        res.getInt(1),
                        res.getString(2),
                        res.getInt(3),
                        res.getString(4),
                        res.getString(5))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (res != null) { try { res.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
        }
        return list;
    }

    /**
     * Runs INSERT on relevant table
     * @param entry the DTO to handle the information
     * @return boolean true if successful false if not
     */
    public static boolean insertEntry(Customer entry) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?)");
            ps.setString(2, entry.getCustomer_name());
            ps.setInt(3, entry.getAddress());
            ps.setString(4, entry.getPhone_number());
            ps.setString(5, entry.getBilling_account());
            int i = ps.executeUpdate();

            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
        }
        return false;
    }

    /**
     * Runs UPDATE on relevant table
     * @param id the entry's primary key
     * @param entry the DTO to handle the information
     * @return boolean true if successful false if not
     */
    public static boolean updateEntry(Integer id, Customer entry) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement("UPDATE customer " +
                    "SET customer_name=?, address=?, phone_number=?," +
                    " billing_account=? WHERE customer_id =" + id);

            ps.setString(1, entry.getCustomer_name());
            ps.setInt(2, entry.getAddress());
            ps.setString(3, entry.getPhone_number());
            ps.setString(4, entry.getBilling_account());

            int i = ps.executeUpdate();

            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
        }
        return false;
    }

    /**
     * Runs DELETE on relevant table
     * @param id the entry's primary key
     * @return boolean true if successful false if not
     */
    public static boolean deleteEntry(Integer id) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = SQLConnection.getConnection();
            stmt = conn.createStatement();
            int i = stmt.executeUpdate("DELETE FROM customer WHERE customer_id=" + id);

            stmt.close();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
        }
        return false;
    }

}
