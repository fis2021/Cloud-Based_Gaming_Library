package org.fis2021.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.models.User;
import org.fis2021.services.FileSystemService;
import org.fis2021.services.LibraryService;
import org.fis2021.services.UserService;
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
class AdminsFirstPageControllerTest {

    private AdminsFirstPageController controller;

    @BeforeAll
    static void beforeAll() throws IOException, UsernameAlreadyExistsException, GameAlreadyExistsException {
        FileSystemService.initTestDirectory();
        FileUtils.cleanDirectory(FileSystemService.getTestPathToFile().toFile());
        UserService.initTestDatabase();
        LibraryService.initTestDatabase();

        UserService.addUser("admin","admin","Admin");
        User user = UserService.getAllUsers().get(0);
        LibraryService.addGame("Mario", user.getId());
    }

    @AfterAll
    static void afterAll() {
        UserService.closeDatabase();
        LibraryService.closeDataBase();
    }

    @Start
    void start(Stage primaryStage)throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admins_First_Page.fxml"));
        Parent root = loader.load();
        AdminsFirstPageController controller = loader.getController();
        this.controller = controller;
        controller.setUser(UserService.getUser("admin"));
        primaryStage.setUserData(UserService.getUser("admin"));
        primaryStage.setTitle("Cloud-Based Gaming Library - AdminsFirstPage");
        primaryStage.setScene(new Scene(root, 1280, 718));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Test
    @DisplayName("Testing the -Go to AdminPage- button functionality")
    void test1(FxRobot robot) {

        robot.clickOn("#goToAdminPage");

    }

    @Test
    @DisplayName("Testing the -Go to Store- button functionality")
    void test2(FxRobot robot) {

        robot.clickOn("#goToStore");

    }

    @Test
    @DisplayName("Testing the -Log Out- button functionality")
    void test3(FxRobot robot) {

        robot.clickOn("#logOut");

    }
}