package org.fis2021.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.GameAlreadyInStoreException;
import org.fis2021.models.User;
import org.fis2021.services.LibraryService;
import org.fis2021.services.StoreService;

import java.io.IOException;
import java.util.ArrayList;

public class DeveloperPageController {


    @FXML
    private Label devname;

    @FXML
    private VBox vbox;

    @FXML
    private ListView<String> devList;

    @FXML
    private TextField newGameName;

    @FXML
    private Text devMessage;

    private User user;

    public void setUser(User u)
    {
        user = u;
        devname.setText(String.format("%s",user.getUsername()));
    }

    @FXML
    public void addGameToStore() {
        try {

            StoreService.addGame(newGameName.getText(),user.getId());
            devMessage.setText("Game Added");
        } catch (GameAlreadyInStoreException e) {
            devMessage.setText(e.getMessage());
        }
    }

    ObservableList list = FXCollections.observableArrayList();


    @FXML
    public void listInit() {
        list.removeAll(list);
        ArrayList<String> store = StoreService.getGame(user.getId());
        list.addAll(store);
        devList.getItems().addAll(list);
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


}
