package org.fis2021.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis2021.exceptions.GameAlreadyInStoreException;
import org.fis2021.exceptions.GameAlreadySentException;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.models.User;
import org.fis2021.services.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class AdminControllerTest {

    private AdminController controller;

    @BeforeAll
    static void beforeAll() throws IOException, UsernameAlreadyExistsException, GameAlreadySentException {
        FileSystemService.initTestDirectory();
        FileUtils.cleanDirectory(FileSystemService.getTestPathToFile().toFile());
        UserService.initTestDatabase();
        LibraryService.initTestDatabase();
        StoreService.initTestDatabase();
        AdminService.initTestDatabase();

        UserService.addUser("admin","admin","Admin");
        UserService.addUser("dev","dev","Developer");
        User user = UserService.getAllUsers().get(1);
        AdminService.addGame("Mario", user.getId());
        AdminService.addGame("Sonic", user.getId());
        AdminService.addGame("Gta5", user.getId());
    }

    @AfterAll
    static void afterAll() {
        UserService.closeDatabase();
        LibraryService.closeDataBase();
        StoreService.closeDataBase();
        AdminService.closeDataBase();
    }

    @Start
    void start(Stage primaryStage)throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminPage.fxml"));
        Parent root = loader.load();
        AdminController controller = loader.getController();
        this.controller = controller;
        controller.setUser(UserService.getUser("admin"));
        primaryStage.setUserData(UserService.getUser("admin"));
        primaryStage.setTitle("Cloud-Based Gaming Library - AdminPage");
        primaryStage.setScene(new Scene(root, 1280, 718));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Test
    @DisplayName("Accepting games to the store")
    void test1(FxRobot robot) {
        robot.clickOn("#initGameList");
        robot.clickOn("#Mario");
        robot.clickOn("#Gta5");
    }

    @Test
    @DisplayName("Testing the -Back- button functionality")
    void test2(FxRobot robot) {
        robot.clickOn("#backButton");
    }
}