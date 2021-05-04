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

public class AdminsFirstPageController{
    @FXML
    private Label inputUserName;

    @FXML
    private Text adminMessage;

    private User user;
    public void setUser(User u)
    {
        user = u;
        inputUserName.setText(String.format("Welcome admin %s !",user.getUsername()));
    }


    public User getUser()
    {
        return user;
    }

    @FXML
    public void handleStoreAction() {
        adminMessage.setText("Store page will load now!");
        openStore(user);
    }

    @FXML
    private void openStore(User u) {
        try{
            Stage stage = (Stage) adminMessage.getScene().getWindow();
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
    public void handleAdminAction() {
        adminMessage.setText("AdminPage will load now!");
        openAdmin(user);
    }

    @FXML
    private void openAdmin(User u) {
        try{
            Stage stage = (Stage) adminMessage.getScene().getWindow();
            FXMLLoader loader;
            Parent homeRoot;
            loader = new FXMLLoader(getClass().getResource("/fxml/AdminPage.fxml"));
            homeRoot = loader.load();
            AdminController controller = loader.getController();
            controller.setUser(u);
            Scene scene = new Scene(homeRoot, 1280, 718);
            stage.setTitle("CBGL - AdminPage");
            stage.setScene(scene);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
