package sample.no.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import sample.no.model.Category;
import sample.no.model.CategoryDAO;
import sample.no.util.SQLConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    //Is responsible for manipulating database
    private CategoryDAO dao = new CategoryDAO();
    @FXML
    private Button insertButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button loadTableBtn;


    @FXML
    private TextArea statusField;
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
    private TableView tableView;
    @FXML
    private TableColumn<Category, Integer> columnId;
    @FXML
    private TableColumn<Category, String> column2;


    private ObservableList<Category> data;
    private String getQ = "SELECT * FROM category";
    private SQLConnection sqlConn;

    @FXML
    private void prepareContent() {
        column2.setText("Type");

        col2Field.setPromptText("Type");
        col3Field.setDisable(true);
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
//                this.data.add(new Category(
//                        res.getInt(1),
//                        res.getString(2)));
//            }
//        } catch (SQLException e) { e.printStackTrace();}
        this.data = dao.getAllEntries();
        this.columnId.setCellValueFactory(cellData -> cellData.getValue().category_idProperty().asObject());
        this.column2.setCellValueFactory(cellData -> cellData.getValue().category_nameProperty());

        this.tableView.setItems(null);
        this.tableView.setItems(this.data);
    }

    @FXML
    private void insertEntry () {
        Category input = new Category(col2Field.getText());
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
        //Check if id is empty
        if (idField.getText().equals("")) {
            statusField.setText("Specify which with id field");
            return;
        }
        //Check if values are empty
        if (col2Field.getText().equals("")) {
            statusField.setText("Empty value not accepted, add to all fields.");
        } else {
            Category input = new Category(col2Field.getText());

            int savedValue = Integer.parseInt(idField.getText());
            dao.updateEntry(savedValue, input);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        this.sqlConn = new SQLConnection();
        prepareContent();
    }
}
