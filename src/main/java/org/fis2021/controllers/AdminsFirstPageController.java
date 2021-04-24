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



}
