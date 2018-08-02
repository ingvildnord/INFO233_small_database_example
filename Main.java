package sample.no;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sample.no.util.CreateDB;

import java.sql.Connection;

//Main class which extends from Application Class
public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./view/RootView.fxml"));

        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Database Application");
        stage.show();
    }

    public static void main(String[] args) {
        //CreateDB creates database from SQL file.
        //This SQL file also generates tables and content
        CreateDB db = new CreateDB();
        launch(args);
    }

}
