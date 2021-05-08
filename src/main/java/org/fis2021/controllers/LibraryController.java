package org.fis2021.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.NoGameFoundException;
import org.fis2021.models.User;
import org.fis2021.services.LibraryService;


import java.io.IOException;
import java.util.ArrayList;

public class LibraryController {

    @FXML
    private TextField gamenameField;

    @FXML
    private ListView<String> gameslist;

    @FXML
    private  Text libMessage;

    @FXML
    private Label userName;

    @FXML
    private TextField searchField;

    private User user;

    public LibraryController() throws NoGameFoundException {
    }

    public void setUser(User u) {
        user = u;
        userName.setText(String.format("%s",user.getUsername()));
    }


    ObservableList list = FXCollections.observableArrayList();


    @FXML
    public void listInit() {
        list.removeAll(list);
        ArrayList<String> libs = LibraryService.getGame(user.getId());
        list.addAll(libs);
        gameslist.getItems().addAll(list);
    }

    @FXML
    public void handleStoreAction() {
        libMessage.setText("Store page will load now!");
        openStore(user);
    }


    @FXML
    public void searchList() throws NoGameFoundException{
        list.removeAll(list);
        ArrayList<String> libs = LibraryService.getSearchedGame(searchField.getText(), user.getId());
        if(libs == null)
            throw new NoGameFoundException();
        else
        {
            list.addAll(libs);
            gameslist.getItems().addAll(list);
        }

    }

    @FXML
    private void openStore(User u) {
        try{
            Stage stage = (Stage) libMessage.getScene().getWindow();
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
    private void backButtonAction() {
        User user1 = this.user;
        Stage stage = (Stage) libMessage.getScene().getWindow();
        FXMLLoader loader;
        Parent homeRoot = null;
        switch (user1.getRole()){

            case "Developer":
                loader = new FXMLLoader(getClass().getResource("/fxml/Devs_First_Page.fxml"));
                try {
                    homeRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DevsFirstPageController controller = loader.getController();
                controller.setUser(user1);
                break;
            case "Admin":
                loader = new FXMLLoader(getClass().getResource("/fxml/Admins_First_Page.fxml"));
                try {
                    homeRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                AdminsFirstPageController  controller2 = loader.getController();
                controller2.setUser(user1);
                break;
            case "User":
                loader = new FXMLLoader(getClass().getResource("/fxml/FirstPage.fxml"));
                try {
                    homeRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FirstPageController controller3 = loader.getController();
                controller3.setUser(user1);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + user1.getRole());
        }
        Scene scene = new Scene(homeRoot, 1280, 718);
        stage.setTitle("CBGL - FirstPage");
        stage.setScene(scene);
    }

}
