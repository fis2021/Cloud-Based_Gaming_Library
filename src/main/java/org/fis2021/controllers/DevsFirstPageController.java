package org.fis2021.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.fis2021.models.User;
import java.io.IOException;
import javafx.scene.control.*;

public class DevsFirstPageController{
    @FXML
    private Label inputUserName;
    private User user;
    public void setUser(User u)
    {
        user = u;
        inputUserName.setText(String.format("Welcome dev %s !",user.getUsername()));
    }

    public User getUser()
    {
        return user;
    }



}
