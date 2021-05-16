package org.fis2021.controllers;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HorizontalDirection;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.GameAlreadyInStoreException;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.models.User;
import org.fis2021.services.FileSystemService;
import org.fis2021.services.LibraryService;
import org.fis2021.services.StoreService;
import org.fis2021.services.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.robot.Motion;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class StoreControllerTest {

    private StoreController controller;

    @BeforeAll
    static void beforeAll() throws IOException, UsernameAlreadyExistsException, GameAlreadyInStoreException {
        FileSystemService.initTestDirectory();
        FileUtils.cleanDirectory(FileSystemService.getTestPathToFile().toFile());
        UserService.initTestDatabase();
        LibraryService.initTestDatabase();
        StoreService.initTestDatabase();

        UserService.addUser("user","user","User");
        UserService.addUser("dev","dev","Developer");
        User user = UserService.getAllUsers().get(1);
        StoreService.addGame("Mario", user.getId());
        StoreService.addGame("Sonic", user.getId());
        StoreService.addGame("Gta5", user.getId());
    }

    @AfterAll
    static void afterAll() {
        UserService.closeDatabase();
        LibraryService.closeDataBase();
        StoreService.closeDataBase();
    }

    @Start
    void start(Stage primaryStage)throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StorePage.fxml"));
        Parent root = loader.load();
        StoreController controller = loader.getController();
        this.controller = controller;
        controller.setUser(UserService.getUser("user"));
        primaryStage.setUserData(UserService.getUser("user"));
        primaryStage.setTitle("Cloud-Based Gaming Library - Store");
        primaryStage.setScene(new Scene(root, 1280, 718));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Test
    @DisplayName("Initializing store and adding a game to the user's library")
    void test1(FxRobot robot) {
        robot.clickOn("#initStore");
        robot.clickOn("#Mario");
    }

    @Test
    @DisplayName("Initializing store and adding more games to the user's library")
    void test2(FxRobot robot) {
        robot.clickOn("#initStore");
        robot.clickOn("#Mario");
        robot.clickOn("#Gta5");
    }

    @Test
    @DisplayName("Testing the -Back- button")
    void test3(FxRobot robot) {
        robot.clickOn("#backButton");
    }

}