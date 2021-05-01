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
import org.fis2021.models.User;
import org.fis2021.services.LibraryService;
import org.fis2021.services.StoreService;

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


    public  void addGameToLibrary(String gameName,int userId) {
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
}

