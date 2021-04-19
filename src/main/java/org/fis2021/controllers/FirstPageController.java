package org.fis2021.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.fis2021.models.User;


public class FirstPageController {
@FXML
    private Label inputUserName;
    private User user;
    public void setUser(User u)
    {
        user = u;
        inputUserName.setText(String.format("Welcome %s !",user.getUsername()));
    }

    public User getUser()
    {
        return user;
    }

}
