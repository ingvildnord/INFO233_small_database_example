package sample.no.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.no.model.Address;
import sample.no.model.AddressDAO;
import sample.no.util.SQLConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddressController implements Initializable {

    //Is responsible for manipulating database
    private AddressDAO dao = new AddressDAO();

    @FXML
    private Label label;
    @FXML
    private Label isConnectedLabel;

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
    private TableColumn<Address, Integer> columnId;
    @FXML
    private TableColumn<Address, String> column2;
    @FXML
    private TableColumn<Address, String> column3;
    @FXML
    private TableColumn<Address, String> column4;
    @FXML
    private TableColumn<Address, String> column5;

    private ObservableList<Address> data;
    private SQLConnection sqlConn;

    private String getAddressQ = "SELECT * FROM address";

    @FXML
    private void prepareContent() {
        column2.setText("Street Number");
        column3.setText("Street Name");
        column4.setText("Postal Code");
        column5.setText("Postal Town");

        col2Field.setPromptText("Street Number");
        col3Field.setPromptText("Street Name");
        col4Field.setPromptText("Postal Code");
        col5Field.setPromptText("Postal Town");

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
//            ResultSet res = conn.createStatement().executeQuery(getAddressQ);
//
//            while (res.next()) {
//                this.data.add(new Address(
//                        res.getInt(1),
//                        res.getString(2),
//                        res.getString(3),
//                        res.getString(4),
//                        res.getString(5)));
//            }
//        }catch (SQLException e) { e.printStackTrace();}
        this.data = dao.getAllEntries();

        this.columnId.setCellValueFactory(cellData -> cellData.getValue().address_idProperty().asObject());
        this.column2.setCellValueFactory(cellData -> cellData.getValue().street_numberProperty());
        this.column3.setCellValueFactory(cellData -> cellData.getValue().street_nameProperty());
        this.column4.setCellValueFactory(cellData -> cellData.getValue().postal_codeProperty());
        this.column5.setCellValueFactory(cellData -> cellData.getValue().postal_townProperty());

        this.tableView.setItems(null);
        this.tableView.setItems(this.data);
    }

    @FXML
    private void insertEntry () {
        Address input = new Address(col2Field.getText(), col3Field.getText(), col4Field.getText(), col5Field.getText());
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
            Address input = new Address(col2Field.getText(), col3Field.getText(), col4Field.getText(), col5Field.getText());
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


