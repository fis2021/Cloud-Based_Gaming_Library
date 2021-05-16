package org.fis2021.services;

import org.apache.commons.io.FileUtils;
import org.fis2021.exceptions.GameAlreadySentException;
import org.fis2021.models.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

class AdminServiceTest {
    @BeforeEach
    void setUp() throws IOException {
        FileSystemService.initTestDirectory();
        FileUtils.cleanDirectory(FileSystemService.getTestPathToFile().toFile());
        AdminService.initTestDatabase();
    }
    @AfterEach
    void tearDown() {
        AdminService.closeDataBase();
    }

    @Test
    @DisplayName("Checking if the admin's database is empty and not null")
    void test1() {

        assertThat(AdminService.getAllGames()).isNotNull();
        assertThat(AdminService.getAllGames()).isEmpty();

    }

    @Test
    @DisplayName("Checking if there can be sent any game to the admin")
    void test2() throws GameAlreadySentException {

        AdminService.addGame("Mario","123");
        assertThat(AdminService.getAllGames()).isNotEmpty();
        Admin admin = AdminService.getAllGames().get(0);
        assertThat(admin.getGameName()).isEqualTo("Mario");

    }

    @Test
    @DisplayName("Checking if there can be removed any game from the admin's database")
    void test3() throws GameAlreadySentException {

        AdminService.addGame("Mario","123");
        assertThat(AdminService.getAllGames()).isNotEmpty();
        AdminService.removeGame("Mario");
        assertThat(AdminService.getAllGames()).isEmpty();
    }
}