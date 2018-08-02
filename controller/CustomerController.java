package sample.no.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.no.model.Address;
import sample.no.model.AddressDAO;
import sample.no.model.Customer;
import sample.no.model.CustomerDAO;
import sample.no.util.SQLConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    //Is responsible for manipulating database
    private CustomerDAO dao = new CustomerDAO();

    @FXML
    private Button insertButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button loadTableBtn;

    @FXML
    private TextField idField;
    @FXML
    private TextField col2Field;
    @FXML
    private TextField col3Field;
    @FXML
    private TextField col4Field;
    @FXML
    private TextField col5Field;

    @FXML
    private TextArea statusField;

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Customer, Integer> columnId;
    @FXML
    private TableColumn<Customer, String> column2;
    @FXML
    private TableColumn<Customer, Integer> column3;
    @FXML
    private TableColumn<Customer, String> column4;
    @FXML
    private TableColumn<Customer, String> column5;

    private ObservableList<Customer> data;
    private String getCustomerQ = "SELECT * FROM customer";
    private SQLConnection sqlConn;


    @FXML
    private void prepareContent() {
        column2.setText("Name");
        column3.setText("Address");
        column4.setText("Phone Number");
        column5.setText("Billing account");

        col2Field.setPromptText("Name");
        col3Field.setPromptText("Address");
        col4Field.setPromptText("Phone Number");
        col5Field.setPromptText("Billing account");

        insertButton.setOnAction(event -> insertEntry());
        deleteButton.setOnAction(event -> deleteEntry());
        updateButton.setOnAction(event -> updateEntry());
        loadTableBtn.setOnAction(event -> loadTableData());

    }


    @FXML
    private void loadTableData() {
//        try {
//            Connection conn = SQLConnection.getConnection();
//            this.data = FXCollections.observableArrayList();
//
//            ResultSet res = conn.createStatement().executeQuery(getCustomerQ);
//
//            while (res.next()) {
//                this.data.add(new Customer(
//                        res.getInt(1),
//                        res.getString(2),
//                        res.getInt(3),
//                        res.getString(4),
//                        res.getString(5)));
//            }
//        }catch (SQLException e) { e.printStackTrace();}

        this.data = dao.getAllEntries();
        this.columnId.setCellValueFactory(cellData -> cellData.getValue().customer_idProperty().asObject());
        this.column2.setCellValueFactory(cellData -> cellData.getValue().customer_nameProperty());
        this.column3.setCellValueFactory(cellData -> cellData.getValue().customer_idProperty().asObject());
        this.column4.setCellValueFactory(cellData -> cellData.getValue().phone_numberProperty());
        this.column5.setCellValueFactory(cellData -> cellData.getValue().billing_accountProperty());

        this.tableView.setItems(null);
        this.tableView.setItems(this.data);
    }

    @FXML
    private void insertEntry () {
        Integer parse = Integer.parseInt(col3Field.getText());
        Customer input = new Customer(col2Field.getText(), parse, col4Field.getText(), col5Field.getText());

        dao.insertEntry(input);
    }


    @FXML
    private void deleteEntry() {
        if (idField.getText().equals("")) {
            statusField.setText("Specify which with id field");
            return;
        }

        int savedValue = Integer.parseInt(idField.getText());
        dao.deleteEntry(savedValue);
    }

    @FXML
    private void updateEntry() {
        if (idField.getText().equals("")) {
            statusField.setText("Specify which with id field");
            return;
        }
        if (col2Field.getText().equals("") &&
                col3Field.getText().equals("") &&
                col4Field.getText().equals("") &&
                col5Field.getText().equals("")) {
            statusField.setText("Empty value not accepted, add to all fields.");
        } else {
            Integer parse = Integer.parseInt(col3Field.getText());
            Customer input = new Customer(col2Field.getText(), parse, col4Field.getText(), col5Field.getText());
            int savedValue = Integer.parseInt(idField.getText());
            dao.updateEntry(savedValue, input);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.sqlConn = new SQLConnection();
        prepareContent();
    }
}