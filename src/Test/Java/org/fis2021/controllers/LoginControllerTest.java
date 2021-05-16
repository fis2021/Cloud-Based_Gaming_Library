package org.fis2021.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.services.FileSystemService;
import org.fis2021.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class LoginControllerTest {

    @BeforeEach
    void setUp() throws IOException, UsernameAlreadyExistsException {
        FileSystemService.initTestDirectory();
        FileUtils.cleanDirectory(FileSystemService.getTestPathToFile().toFile());
        UserService.initTestDatabase();
        UserService.addUser("user","user","User");
    }

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
    }

    @Start
    void start(Stage primaryStage)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Cloud-Based Gaming Library - Login");
        primaryStage.setScene(new Scene(root, 840, 560));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Test
    @DisplayName("Basic Login test")
    void test1(FxRobot robot) {

        robot.clickOn("#username");
        robot.write("user");
        robot.clickOn("#password");
        robot.write("user");
        robot.clickOn("#login");

    }
}