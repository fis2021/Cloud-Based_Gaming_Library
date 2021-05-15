package org.fis2021.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
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
class RegistrationControllerTest {
    @BeforeEach
    void setUp() throws IOException {
        FileSystemService.initTestDirectory();
        FileUtils.cleanDirectory(FileSystemService.getTestPathToFile().toFile());
        UserService.initTestDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
    }

    @Start
    void start(Stage primaryStage)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        primaryStage.setTitle("Cloud-Based Gaming Library - Login");
        primaryStage.setScene(new Scene(root, 840, 560));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Test
    @DisplayName("Basic registration test")
    void test1(FxRobot robot) {
    robot.clickOn("#username");
    robot.write("user");
    robot.clickOn("#password");
    robot.write("pass");
    robot.clickOn("#role");
    robot.clickOn("User");
    robot.clickOn("#registerButton");
    assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
    assertThat(UserService.getAllUsers()).size().isEqualTo(1);
    }

    @Test
    @DisplayName("Registration test with empty fields")
    void test2(FxRobot robot) {
        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Username or password not valid!");
        assertThat(UserService.getAllUsers()).isEmpty();
    }
}
