package sample.no.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sample.no.util.SQLConnection;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Able to contain and handle the different Controllers and FXML-sheets.
 * Responsible for assigning Controllers to nested FXML TableView.fxml depending on buttonpress.
 */
public class RootController implements Initializable {

    private SQLConnection sqlConn;

    @FXML
    private AnchorPane rootPane;//The pane used for loading Tableview.fxml

    @FXML
    private Button getCustInvoicePane;
    @FXML
    private Button getAddressPane;
    @FXML
    private Button getCustomerPane;
    @FXML
    private Button getProductPane;
    @FXML
    private Button getCategoryPane;
    @FXML
    private Button getInvoicePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void loadCustInvoicePane(ActionEvent event) throws IOException {
        CustomerInvoiceController controller = new CustomerInvoiceController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CustomerInvoiceView.fxml"));
        loader.setController(controller);

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadAddressPane(ActionEvent event) throws IOException {
        AddressController controller = new AddressController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TableView.fxml"));
        loader.setController(controller);

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadCustomerPane(ActionEvent event) throws IOException {
        CustomerController controller = new CustomerController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TableView.fxml"));
        loader.setController(controller);

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadProductPane(ActionEvent event) throws IOException {
        ProductController controller = new ProductController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TableView.fxml"));
        loader.setController(controller);

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadCategoryPane(ActionEvent event) throws IOException {
        CategoryController controller = new CategoryController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TableView.fxml"));
        loader.setController(controller);

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadInvoicePane(ActionEvent event) throws IOException {
        InvoiceController controller = new InvoiceController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TableView.fxml"));
        loader.setController(controller);

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

}
