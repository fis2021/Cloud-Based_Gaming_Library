package org.fis2021.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis2021.models.User;
import java.io.IOException;
import javafx.scene.control.*;

public class DevsFirstPageController{
    @FXML
    private Label inputUserName;

    @FXML
    private Text devMessage;
    private User user;
    public void setUser(User u)
    {
        user = u;
        inputUserName.setText(String.format("Welcome dev %s !",user.getUsername()));
    }

    public User getUser()
    {
        return user;
    }

    @FXML
    public void handleStoreAction() {
        devMessage.setText("Store page will load now!");
        openStore(user);
    }

    @FXML
    private void openStore(User u) {
        try{
            Stage stage = (Stage) devMessage.getScene().getWindow();
            FXMLLoader loader;
            Parent homeRoot;
            loader = new FXMLLoader(getClass().getResource("/fxml/StorePage.fxml"));
            homeRoot = loader.load();
            StoreController controller = loader.getController();
            controller.setUser(u);
            Scene scene = new Scene(homeRoot, 1280, 718);
            stage.setTitle("CBGL - StorePage");
            stage.setScene(scene);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDevPageAction() {
        devMessage.setText("Developer page will load now!");
        openDevPage(user);
    }

    @FXML
    private void openDevPage(User u) {
        try{
            Stage stage = (Stage) devMessage.getScene().getWindow();
            FXMLLoader loader;
            Parent homeRoot;
            loader = new FXMLLoader(getClass().getResource("/fxml/DeveloperPage.fxml"));
            homeRoot = loader.load();
            DeveloperPageController controller = loader.getController();
            controller.setUser(u);
            Scene scene = new Scene(homeRoot, 1280, 718);
            stage.setTitle("CBGL - DeveloperPage");
            stage.setScene(scene);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void logOutHandle ()
    {
        Stage stage = (Stage) devMessage.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Cloud-Based Gaming Library - Login");
        stage.setScene(new Scene(root, 840, 560));
        stage.setResizable(false);
        stage.show();
    }


}
