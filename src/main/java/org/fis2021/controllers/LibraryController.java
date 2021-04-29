package org.fis2021.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.NoGameFoundException;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.models.Library;
import org.fis2021.models.User;
import org.fis2021.services.LibraryService;
import org.fis2021.services.UserService;

import java.io.IOException;
import java.util.ArrayList;

public class LibraryController {

    @FXML
    private TextField gamenameField;

    @FXML
    private ListView<String> gameslist;

    @FXML
    private Text libMessage;

    @FXML
    private Label userName;

    private User user;

    public LibraryController() throws NoGameFoundException {
    }

    public void setUser(User u) {
        user = u;
        userName.setText(String.format("%s",user.getUsername()));
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


    ObservableList list = FXCollections.observableArrayList();


    @FXML
    public void listInit() throws NoGameFoundException {
        list.removeAll(list);
        ArrayList<String> libs = LibraryService.getGame(user.getId());
        list.addAll(libs);
        gameslist.getItems().addAll(list);
    }

}
