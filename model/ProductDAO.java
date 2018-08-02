package sample.no.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.no.util.SQLConnection;

import java.sql.*;
/**
 * Implementation of DAO for Product table
 * AddressDAO, CategoryDAO, CustomerDAO, InvoiceDAO and ProductDAO are all similar
 * aside from what table and DTO they utilize
 */
public class ProductDAO {

    /**
     * To pass all known information to GUI tableview
     * @return ObservableList a list to be used by the responsible controller class
     */
    public static ObservableList<Product> getAllEntries() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        ObservableList list = null;
        String query = "SELECT * FROM product";
        try {

            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement(query);
            res = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (res.next()) {
                list.add(new Product(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getDouble(4),
                        res.getInt(5)));
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
    public static boolean insertEntry(Product entry) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement("INSERT INTO product VALUES(?,?,?,?,?)");
            ps.setString(2, entry.getProduct_name());
            ps.setString(3, entry.getDescription_text());
            ps.setDouble(4, entry.getPrice());
            ps.setInt(5, entry.getCategory());

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
    public static boolean updateEntry(Integer id, Product entry) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement("UPDATE product " +
                    "SET product_name=?, description=?, price=?, category=? WHERE product_id =" + id);

            ps.setString(1, entry.getProduct_name());
            ps.setString(2, entry.getDescription_text());
            ps.setDouble(3, entry.getPrice());
            ps.setInt(4, entry.getCategory());

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
            int i = stmt.executeUpdate("DELETE FROM product WHERE product_id=" + id);

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
