package sample.no.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.no.model.Address;
import sample.no.model.Product;
import sample.no.model.ProductDAO;
import sample.no.model.Customer;
import sample.no.util.SQLConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    //Is responsible for manipulating database
    private ProductDAO dao = new ProductDAO();

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
    private TableColumn<Product, Integer> columnId;
    @FXML
    private TableColumn<Product, String> column2;
    @FXML
    private TableColumn<Product, String> column3;
    @FXML
    private TableColumn<Product, Double> column4;
    @FXML
    private TableColumn<Product, Integer> column5;

    private ObservableList<Product> data;

    private String getQ = "SELECT * FROM product";
    private SQLConnection sqlConn;

    @FXML
    private void prepareContent() {
        column2.setText("Product Name");
        column3.setText("Description");
        column4.setText("Price");
        column5.setText("Category id");

        col2Field.setPromptText("Product Name");
        col3Field.setPromptText("Description");
        col4Field.setPromptText("Price");
        col5Field.setPromptText("Category id");

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
//                this.data.add(new Product(
//                        res.getInt(1),
//                        res.getString(2),
//                        res.getString(3),
//                        res.getDouble(4),
//                        res.getInt(5)));
//            }
//        }catch (SQLException e) { e.printStackTrace();}

        this.data = dao.getAllEntries();

        //TODO id property causes NullPointerException, probably some issue with the DTO
//        this.columnId.setCellValueFactory(cellData -> cellData.getValue().product_idProperty().asObject());
        this.column2.setCellValueFactory(cellData -> cellData.getValue().product_nameProperty());
        this.column3.setCellValueFactory(cellData -> cellData.getValue().description_textProperty());
        this.column4.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        this.column5.setCellValueFactory(cellData -> cellData.getValue().categoryProperty().asObject());

        this.tableView.setItems(null);
        this.tableView.setItems(this.data);
    }

    @FXML
    private void insertEntry () {
        Integer parse = Integer.parseInt(col5Field.getText());
        Double dparse = Double.parseDouble(col4Field.getText());
        Product input = new Product(col2Field.getText(), col3Field.getText(),dparse, parse );
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
            Integer parse = Integer.parseInt(col5Field.getText());
            Double dparse = Double.parseDouble(col4Field.getText());
            Product input = new Product(col2Field.getText(), col3Field.getText(),dparse, parse );
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
