package org.fis2021.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import org.fis2021.exceptions.UsernameNotFoundException;
import org.fis2021.models.User;
import org.fis2021.services.UserService;

import java.io.IOException;

public class StoreController {

    @FXML
    private Label inputUserName;

    @FXML
    private Text storeMessage;
    private User user;
    public void setUser(User u) {
        user = u;
        inputUserName.setText(String.format("Welcome to the Store %s !",user.getUsername()));
    }
}
