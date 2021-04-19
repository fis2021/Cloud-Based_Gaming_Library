package org.fis2021.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.services.UserService;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("User", "Developer", "Admin");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void goToLoginAction(){

       try {
           Stage stage = (Stage) registrationMessage.getScene().getWindow();

           Parent root_register = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
           stage.setTitle("CBGL - Register");
           stage.setScene(new Scene(root_register, 840, 560));
       }catch (IOException e){
           e.printStackTrace();
       }

    }
}