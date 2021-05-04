package org.fis2021.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.GameAlreadyInStoreException;
import org.fis2021.exceptions.NoGameFoundException;
import org.fis2021.models.User;
import org.fis2021.models.Admin;
import org.fis2021.services.AdminService;
import org.fis2021.services.LibraryService;
import org.fis2021.services.StoreService;

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


}
