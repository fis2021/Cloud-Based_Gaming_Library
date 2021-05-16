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
import org.fis2021.exceptions.GameAlreadyInStoreException;
import org.fis2021.exceptions.NoGameFoundException;
import org.fis2021.models.User;
import org.fis2021.models.Admin;
import org.fis2021.services.AdminService;
import org.fis2021.services.LibraryService;
import org.fis2021.services.StoreService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;


public class AdminController {

    @FXML
    private GridPane adminGridPane;

    @FXML
    private Text adminPageMessage;

    @FXML
    private Label adminWelcomeMessage;

    private User user;
    public void setUser(User u) {

        user = u;
        adminWelcomeMessage.setText(String.format("Welcome %s!",user.getUsername()));
    }

    @FXML
    public void addGameToStore(String gameName, String devId) {
        try {
            StoreService.addGame(gameName,devId);
            AdminService.removeGame(gameName);
            adminPageMessage.setText("Game Added");
        } catch (GameAlreadyInStoreException e) {
            adminPageMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void initGames()
    {
        adminPageMessage.setText("The games list has been initialized!");

        ArrayList<Admin> list = AdminService.getAllGames();
        int n,m;
        Iterator<Admin> it = list.iterator();
        n=0;
        m=1;

        while(it.hasNext())
        {
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            Label label = new Label();
            Admin admin1 = it.next();
            label.setText(String.format("%s",admin1.getGameName()));
            Button button = new Button("Add To Store");
            button.setId(label.getText());
            button.setOnAction(new EventHandler<ActionEvent>() {
                                   @Override
                                   public void handle(ActionEvent actionEvent) {
                                       addGameToStore(admin1.getGameName(), admin1.getDevId());
                                   }
                               }
            );
            vbox.getChildren().addAll(label,button);
            adminGridPane.add(vbox,m,n);
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
        Stage stage = (Stage) adminPageMessage.getScene().getWindow();
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