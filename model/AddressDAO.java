package sample.no.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import sample.no.util.SQLConnection;

/**
 * Implementation of DAO for Address table
 * AddressDAO, CategoryDAO, CustomerDAO, InvoiceDAO and ProductDAO are all similar
 * aside from what table and DTO they utilize
 */
public class AddressDAO {

    /**
     * To pass all known information to GUI tableview
     * @return ObservableList a list to be used by the responsible controller class
     */
    public static ObservableList<Address> getAllEntries() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        ObservableList list = null;
        String query = "SELECT * FROM address";
        try {

            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement(query);
            res = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (res.next()) {
                list.add(new Address(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)));
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
     * @param address the DTO to handle the information
     * @return boolean true if successful false if not
     */
    public static boolean insertEntry(Address address) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement("INSERT INTO address VALUES(?,?,?,?,?)");
            ps.setString(2, address.getStreet_number());
            ps.setString(3, address.getStreet_name());
            ps.setString(4, address.getPostal_code());
            ps.setString(5, address.getPostal_town());
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
    public static boolean updateEntry(Integer id, Address entry) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement("UPDATE address " +
                    "SET street_number=?, street_name=?, postal_code=?," +
                    " postal_town=? WHERE address_id =" + id);

            ps.setString(1, entry.getStreet_number());
            ps.setString(2, entry.getStreet_name());
            ps.setString(3, entry.getPostal_code());
            ps.setString(4, entry.getPostal_town());

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
            int i = stmt.executeUpdate("DELETE FROM address WHERE address_id=" + id);

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



