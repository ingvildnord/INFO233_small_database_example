package sample.no.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.no.model.Invoice;
import sample.no.model.InvoiceDAO;
import sample.no.util.SQLConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    //Is responsible for manipulating database
    private InvoiceDAO dao = new InvoiceDAO();
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
    private TableColumn<Invoice, Integer> columnId;
    @FXML
    private TableColumn<Invoice, Integer> column2;
    @FXML
    private TableColumn<Invoice, String> column3;


    private ObservableList<Invoice> data;
    private String getQ = "SELECT * FROM invoice";
    private SQLConnection sqlConn;

    @FXML
    private void prepareContent() {
        column2.setText("customer-id");
        column3.setText("date");

        col2Field.setPromptText("Customer ID");
        col3Field.setPromptText("Date");
        col4Field.setDisable(true);
        col5Field.setDisable(true);

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
//            ResultSet res = conn.createStatement().executeQuery(getQ);
//
//            while (res.next()) {
//                this.data.add(new Invoice(
//                        res.getInt(1),
//                        res.getInt(2),
//                        res.getString(3)
//                        ));
//            }
//        }catch (SQLException e) { e.printStackTrace();}
        this.data = dao.getAllEntries();

        this.columnId.setCellValueFactory(cellData -> cellData.getValue().invoice_idProperty().asObject());
        this.column2.setCellValueFactory(cellData -> cellData.getValue().customerProperty().asObject());
        this.column3.setCellValueFactory(cellData -> cellData.getValue().datoProperty());

        this.tableView.setItems(null);
        this.tableView.setItems(this.data);
    }

    @FXML
    private void insertEntry () {
        Integer parse = Integer.parseInt(col2Field.getText());
        Invoice input = new Invoice(parse, col3Field.getText());
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

        if (col2Field.getText().equals("") && col3Field.getText().equals("")) {
            statusField.setText("Empty value not accepted, add to all fields.");
        } else {

            Integer parse = Integer.parseInt(col2Field.getText());

            Invoice input = new Invoice(parse, col3Field.getText());
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
