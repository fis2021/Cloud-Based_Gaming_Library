package org.fis2021.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import org.fis2021.models.User;
import org.fis2021.services.UserService;

import java.io.IOException;

public class StoreController {
    private User user;
    public void setUser(User u) {
        user = u;
    }
}

