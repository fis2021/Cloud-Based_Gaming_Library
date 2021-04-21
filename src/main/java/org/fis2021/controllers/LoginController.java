package org.fis2021.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis2021.exceptions.UsernameNotFoundException;
import org.fis2021.models.User;
import org.fis2021.services.UserService;

import java.io.IOException;


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
                firstPage();
            }
        } catch(UsernameNotFoundException e) {loginMessage.setText(e.getMessage());}


    }

    @FXML
    public void goToRegisterAction() {

        try {
            Stage stage = (Stage) loginMessage.getScene().getWindow();

            Parent root_login = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
            stage.setTitle("CBGL - Login");
            stage.setScene(new Scene(root_login,1080,560));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void firstPage() {
        try{
            User user1 = UserService.getUser(usernameField.getText());
            Stage stage = (Stage) loginMessage.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FirstPage.fxml"));
            Parent homeRoot = loader.load();
            FirstPageController controller = loader.getController();
            controller.setUser(user1);
            Scene scene = new Scene(homeRoot, 1280, 718);
            stage.setTitle("CBGL - FirstPage");
            stage.setScene(scene);
            UserService.closeDatabase();
        } catch (UsernameNotFoundException e){
            loginMessage.setText(e.getMessage());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
