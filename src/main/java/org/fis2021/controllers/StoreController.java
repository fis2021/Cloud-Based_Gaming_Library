package org.fis2021.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.NoGameFoundException;
import org.fis2021.exceptions.UsernameNotFoundException;
import org.fis2021.models.User;
import org.fis2021.services.LibraryService;
import org.fis2021.services.StoreService;
import org.fis2021.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class StoreController {

    @FXML
    private Text storeMessage;

    @FXML
    private GridPane grid;

    @FXML
    private Label userName;

    private User user;
    public void setUser(User u) {
        user = u;
        userName.setText(String.format("Welcome %s to the store!",user.getUsername()));
    }

    public  void addGameToLibrary(String gameName,String userId) {
        try {

            LibraryService.addGame(gameName,userId);
            storeMessage.setText("Game Added");
        } catch (GameAlreadyExistsException e) {
            storeMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void initStore()
    {
        storeMessage.setText("Store has been initialized");

        ArrayList<String> list = StoreService.getAllGames();
        int n,m;
        Iterator<String> it = list.iterator();
        n=0;
        m=1;

        while(it.hasNext())
        {
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            Label label = new Label();
            label.setText(String.format("%s",it.next()));
            Button button = new Button("Add To Lib");
            button.setOnAction(new EventHandler<ActionEvent>() {
                                   @Override
                                   public void handle(ActionEvent actionEvent) {
                                       addGameToLibrary(label.getText(), user.getId());
                                   }
                               }
            );
            vbox.getChildren().addAll(label,button);
            grid.add(vbox,m,n);
            m++;
            if(m==4)
            {
                n++;
                m=1;
            }

        }
    }


    @FXML
    private void backButtonAction() {
            User user1 = this.user;
            Stage stage = (Stage) storeMessage.getScene().getWindow();
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