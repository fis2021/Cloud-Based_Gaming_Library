package org.fis2021;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fis2021.models.Library;
import org.fis2021.services.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FileSystemService.initDirectory();
        UserService.initDatabase();
        LibraryService.initDatabase();
        StoreService.initDatabase();
        AdminService.initDatabase();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setTitle("Cloud-Based Gaming Library - Login");
        stage.setScene(new Scene(root, 840, 560));
        stage.setResizable(false);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }

}