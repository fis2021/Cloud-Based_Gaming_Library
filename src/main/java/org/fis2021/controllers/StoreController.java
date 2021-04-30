package org.fis2021.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import org.fis2021.models.Store;
import org.fis2021.models.User;
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

