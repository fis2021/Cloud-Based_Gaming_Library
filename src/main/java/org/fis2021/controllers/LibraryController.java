package org.fis2021.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.models.Library;
import org.fis2021.models.User;
import org.fis2021.services.LibraryService;
import org.fis2021.services.UserService;

import java.io.IOException;

public class LibraryController {

    @FXML
    private TextField gamenameField;

    @FXML
    private Text libMessage;

    private User user;
    public void setUser(User u) {
        user = u;

    }

    @FXML
    public void addGameToLib() {
        try {

            LibraryService.addGame(gamenameField.getText(),user.getId());
            libMessage.setText("Game Added");
        } catch (GameAlreadyExistsException e) {
            libMessage.setText(e.getMessage());
        }
    }

}
