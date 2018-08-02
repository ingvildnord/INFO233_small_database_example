package sample.no.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.no.util.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*SELECTION QUERY
    SELECT customer.customer_name, product.product_name, invoice.dato, product.price FROM invoice_items
    INNER JOIN product ON invoice_items.product = product.product_id
    INNER JOIN invoice ON invoice_items.invoice = invoice.invoice_id
    INNER JOIN customer ON invoice.customer = customer.customer_id WHERE customer.customer_id= ;
     */

    /* GET TOTAL FROM INVOICE
    SELECT SUM(product.price) FROM invoice_items
    INNER JOIN product ON invoice_items.product = product.product_id
    INNER JOIN invoice ON invoice_items.invoice = invoice.invoice_id
    INNER JOIN customer ON invoice.customer = customer.customer_id WHERE customer.customer_id = ?;
     */

    /* Get customer info
    SELECT customer.customer_name, customer.phone_number, customer.billing_account, address.street_number, address.street_name, address.postal_code, address.postal_town FROM invoice_items
    INNER JOIN address ON customer.address = address.address_id
    INNER JOIN invoice ON invoice_items.invoice = invoice.invoice_id
    INNER JOIN customer ON invoice.customer = customer.customer_id GROUP by customer.customer_id
     */

/**
 * Specialized class for handling functionality to CustomerInvoiceController, and respective GUI
  */
public class CustomerInvoiceDAO {

    /**
     *
     * @param id
     * @return
     */
    public static ObservableList<CustomerInvoice> getQueryResult(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        ObservableList list = null;
        String query = "SELECT customer.customer_name, product.product_name, invoice.dato, product.price FROM invoice_items\n" +
                "    INNER JOIN product ON invoice_items.product = product.product_id\n" +
                "    INNER JOIN invoice ON invoice_items.invoice = invoice.invoice_id\n" +
                "    INNER JOIN customer ON invoice.customer = customer.customer_id WHERE customer.customer_id= " + id;
        try {

            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement(query);
            res = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (res.next()) {
                list.add(new CustomerInvoice(
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4)));
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
     * Simple method that only returns a String list of names
     * @return list a list to be used by CustomerController, for showing all known customers.
     */
    public static ObservableList<String> getCustomers() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        ObservableList list = null;
        String query = "SELECT customer_id, customer_name FROM customer ";
        try {

            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement(query);
            res = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (res.next()) {
                list.add(res.getString(2));
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
     * Gets user details and corresponding address details from database
     * @param id the customer to get details about
     * @return List the list to be utilized by CustomerInvoiceController
     */
    public static ObservableList getUserInfo(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        ObservableList list = null;
        String query = "SELECT customer.customer_name, customer.phone_number, customer.billing_account," +
                " address.street_number, address.street_name, address.postal_code, address.postal_town FROM invoice_items\n" +
                "    INNER JOIN address ON customer.address = address.address_id\n" +
                "    INNER JOIN invoice ON invoice_items.invoice = invoice.invoice_id\n" +
                "    INNER JOIN customer ON invoice.customer = customer.customer_id WHERE customer.customer_id=" + id + " GROUP by customer.customer_id   " ;
        try {

            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement(query);
            res = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (res.next()) {
                list.add(res.getString(1));
                list.add(res.getString(2));
                list.add(res.getString(3));
                list.add(res.getString(4));
                list.add(res.getString(5));
                list.add(res.getString(6));
                list.add(res.getString(7));
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
     * Runs a SUM query on table and parses the result for total sum of invoice
     * @param id the customer the invoice belongs to
     * @return integer the total sum of invoice
     */
    public Integer getInvoiceTotal(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        String query = "SELECT SUM(product.price) FROM invoice_items\n" +
                "    INNER JOIN product ON invoice_items.product = product.product_id\n" +
                "    INNER JOIN invoice ON invoice_items.invoice = invoice.invoice_id\n" +
                "    INNER JOIN customer ON invoice.customer = customer.customer_id WHERE customer.customer_id =" + id + " GROUP BY invoice_id";

        Integer returnInt = null;
        try {
            conn = SQLConnection.getConnection();
            ps = conn.prepareStatement(query);
            res = ps.executeQuery();

            returnInt = res.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (res != null) { try { res.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
        }
        return returnInt;
    }
}
