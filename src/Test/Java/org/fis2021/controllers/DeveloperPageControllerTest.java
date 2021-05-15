package org.fis2021.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.GameAlreadyInStoreException;
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
class DeveloperPageControllerTest {

    private DeveloperPageController controller;

    @BeforeAll
    static void beforeAll() throws IOException, UsernameAlreadyExistsException, GameAlreadyExistsException, GameAlreadyInStoreException {
        FileSystemService.initTestDirectory();
        FileUtils.cleanDirectory(FileSystemService.getTestPathToFile().toFile());
        UserService.initTestDatabase();
        LibraryService.initTestDatabase();
        AdminService.initTestDatabase();
        StoreService.initTestDatabase();

        UserService.addUser("developer","developer","Developer");
        UserService.addUser("user","user","User");
        UserService.addUser("user1","user1","User");
        User user = UserService.getAllUsers().get(0);
        StoreService.addGame("Mario",user.getId());
        user = UserService.getAllUsers().get(1);
        LibraryService.addGame("Mario", user.getId());
        user = UserService.getAllUsers().get(2);
        LibraryService.addGame("Mario", user.getId());
    }

    @AfterAll
    static void afterAll() {
        UserService.closeDatabase();
        LibraryService.closeDataBase();
        AdminService.closeDataBase();
        StoreService.closeDataBase();
    }

    @Start
    void start(Stage primaryStage)throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeveloperPage.fxml"));
        Parent root = loader.load();
        DeveloperPageController controller = loader.getController();
        this.controller = controller;
        controller.setUser(UserService.getUser("developer"));
        primaryStage.setUserData(UserService.getUser("developer"));
        primaryStage.setTitle("Cloud-Based Gaming Library - DeveloperPage");
        primaryStage.setScene(new Scene(root, 1280, 718));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Test
    @DisplayName("Testing if there can be sended any game to the admin")
    void test1(FxRobot robot) {
        robot.clickOn("#gameName");
        robot.write("SuperGame");
        robot.clickOn("#sendGameToAdmin");
    }

    @Test
    @DisplayName("Testing if there can be showed the statistics about each game a developer have in store")
    void test2(FxRobot robot) {
        robot.clickOn("#viewStatistics");
    }

    @Test
    @DisplayName("Testing the -Go to store- button functionality")
    void test3(FxRobot robot) {
        robot.clickOn("#goStore");
    }
}