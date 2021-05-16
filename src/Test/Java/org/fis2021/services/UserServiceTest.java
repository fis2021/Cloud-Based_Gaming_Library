package org.fis2021.services;

import org.apache.commons.io.FileUtils;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.exceptions.UsernameNotFoundException;
import org.fis2021.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {
    @BeforeEach
    void setUp() throws IOException {
        FileSystemService.initTestDirectory();
        FileUtils.cleanDirectory(FileSystemService.getTestPathToFile().toFile());
        UserService.initTestDatabase();
    }

    @Test
    @DisplayName("Checking if there are any user in database/if it is null")
    void test1() {

        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();

    }

    @Test
    @DisplayName("Checking if the there can be any user added to the database")
    void test2() throws UsernameAlreadyExistsException, UsernameNotFoundException {
        UserService.addUser("admin","admin","Admin");
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user.getUsername()).isEqualTo("admin");
        assertThat(user.getRole()).isEqualTo("Admin");
        assertThat(UserService.getHashedPassword(user.getUsername())).isEqualTo(UserService.encodePassword(user.getUsername(),"admin"));
    }


    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
    }
}