package org.fis2021.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.fis2021.exceptions.UsernameNotFoundException;
import org.fis2021.services.UserService;



public class LoginController {

    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    @FXML
    public void handleLoginAction() {
        String username,  password;
        username = usernameField.getText();
        password = passwordField.getText();

        String CryptedPassword = UserService.encodePassword(username, password);
        try {
            String DBPassword = UserService.getHashedPassword(username);
            if(DBPassword.equals(CryptedPassword)){
                loginMessage.setText("You have been successfully logged in!");
            }
        } catch(UsernameNotFoundException e) {loginMessage.setText(e.getMessage());}

    }
}
