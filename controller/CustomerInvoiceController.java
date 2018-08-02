package sample.no.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.no.model.*;
import sample.no.util.SQLConnection;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CustomerInvoiceController implements Initializable {

    private final String getCust = "SELECT customer_id, customer_name FROM customer";

    private CustomerInvoiceDAO dao = new CustomerInvoiceDAO();

    @FXML
    private Label userLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label billingAccLabel;
    @FXML
    private Label totalSumResultLabel;

    @FXML
    private ListView<String> userList;
    @FXML
    private ListView<String> productList;

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<CustomerInvoice, String> productColumn;
    @FXML
    private TableColumn<CustomerInvoice, String> priceColumn;
    @FXML
    private TableColumn<CustomerInvoice, String> dateColumn;
    @FXML
    private TableColumn<CustomerInvoice, String> priceSumColumn;


    private ObservableList<CustomerInvoice> invoiceData;

    private ObservableList<String> userData;

    private ObservableList<String> userDetails;

    private void setItems() {
        productColumn.setText("product");
        priceColumn.setText("price");
        dateColumn.setText("date");
        userList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov,
                                        String old_val, String new_val) {
                        int ressie = userList.getSelectionModel().getSelectedIndex() + 1; //Because List starts at 0

                        loadUserInvoiceInfo(ressie);
                    }
                });

    }

    private void loadUserList() {
//        try {
//            Connection conn = SQLConnection.getConnection();
//
//            this.userData = FXCollections.observableArrayList();
//            ResultSet res = conn.createStatement().executeQuery(getCust);
//
//            while (res.next()) {
//                this.userData.add(res.getString(2));
//            }
//            conn.close();
//        }catch (SQLException e) { e.printStackTrace();}

        this.userData = dao.getCustomers();

        this.userList.setItems(null);
        this.userList.setItems(userData);
    }


    /**
     * Retrieves and prints out all relevant info about customer.
     * NOTE: the total sum only shows the sum of first found invoice currently.
     * @param id
     */
    private void loadUserInvoiceInfo(Integer id) {
        this.invoiceData =  dao.getQueryResult(id);

        this.productColumn.setCellValueFactory(cellData -> cellData.getValue().product_nameProperty());
        this.priceColumn.setCellValueFactory(cellData -> cellData.getValue().product_priceProperty());
        this.dateColumn.setCellValueFactory(cellData -> cellData.getValue().invoice_datoProperty());


        this.tableView.setItems(null);
        this.tableView.setItems(this.invoiceData);

        this.userDetails = dao.getUserInfo(id);
        if (userDetails.size() == 0) {
            this.userLabel.setText("No Information found");
            this.phoneNumberLabel.setText("");
            this.billingAccLabel.setText("");
            this.addressLabel.setText("");
            this.totalSumResultLabel.setText("");
            return;
        } else {
            this.userLabel.setText(userDetails.get(0));
            this.phoneNumberLabel.setText(userDetails.get(1));
            this.billingAccLabel.setText(userDetails.get(2));
            this.addressLabel.setText(userDetails.get(3) + " " +
                    userDetails.get(4) + " " + userDetails.get(5) + " " + userDetails.get(6) );

            Integer total = dao.getInvoiceTotal(id);
            this.totalSumResultLabel.setText(total.toString());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserList();
        setItems();
    }
}
