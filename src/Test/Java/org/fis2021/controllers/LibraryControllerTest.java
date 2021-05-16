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
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.testfx.assertions.api.Assertions.assertThat;


@ExtendWith(ApplicationExtension.class)
class LibraryControllerTest {

    //private Stage primaryStage;
    private LibraryController controller;

    @BeforeAll
    static void beforeAll() throws IOException, UsernameAlreadyExistsException, GameAlreadyExistsException {
        FileSystemService.initTestDirectory();
        FileUtils.cleanDirectory(FileSystemService.getTestPathToFile().toFile());
        UserService.initTestDatabase();
        LibraryService.initTestDatabase();

        UserService.addUser("user","user","User");
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LibraryPage.fxml"));
        Parent root = loader.load();
        LibraryController controller = loader.getController();
        this.controller = controller;
        controller.setUser(UserService.getUser("user"));
        primaryStage.setUserData(UserService.getUser("user"));
        primaryStage.setTitle("Cloud-Based Gaming Library - Library");
        primaryStage.setScene(new Scene(root, 1280, 718));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Test
    @DisplayName("Testing if the library list is working")
    void test1(FxRobot robot) {
        robot.clickOn("#seeMyGames");
    }

    @Test
    @DisplayName("Testing if the search bar is working")
    void test2(FxRobot robot) {

        robot.clickOn("#searchField");
        robot.eraseText(6);
        robot.write("ri");
        robot.clickOn("#searchButton");

    }

    @Test
    @DisplayName("Testing the -go to store button- funtionality")
    void test3(FxRobot robot) {

        robot.clickOn("#goStore");
        assertThat(robot.lookup("#libMessage").queryText()).hasText("");
    }

    @Test
    @DisplayName("Testing the back button funtionality")
    void test4(FxRobot robot) {
        robot.clickOn("#backButton");

    }
}