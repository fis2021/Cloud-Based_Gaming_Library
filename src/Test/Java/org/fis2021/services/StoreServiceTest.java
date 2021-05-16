package org.fis2021.services;

import org.apache.commons.io.FileUtils;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.GameAlreadyInStoreException;
import org.fis2021.models.Store;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StoreServiceTest {

    @BeforeEach
    void setUp() throws IOException {
        FileSystemService.initTestDirectory();
        FileUtils.cleanDirectory(FileSystemService.getTestPathToFile().toFile());
        StoreService.initTestDatabase();

    }
    @AfterEach
    void tearDown() {
        StoreService.closeDataBase();
    }

    @Test
    @DisplayName("Checking if store's database is null or empty")
    void test1() {
        assertThat(StoreService.getAllGames()).isNotNull();
        assertThat(StoreService.getAllGames()).isEmpty();
    }

    @Test
    @DisplayName("Checking if there can be added any game to store")
    void test2() throws GameAlreadyInStoreException {

        StoreService.addGame("Mario", "123");
        assertThat(StoreService.getAllGames()).isNotEmpty();
        assertThat(StoreService.getAllGames()).size().isEqualTo(1);
        String gamename = StoreService.getGame("123").get(0);
        assertThat(gamename).isEqualTo("Mario");

    }

    @Test
    @DisplayName("Checking the number of downloads for each game a developer has")
    void test3() throws GameAlreadyExistsException, GameAlreadyInStoreException {

        LibraryService.initTestDatabase();
        StoreService.addGame("Mario","111");
        LibraryService.addGame("Mario", "123");
        assertThat(StoreService.getAllGames()).isNotEmpty();
        assertThat(LibraryService.getAllGames()).isNotEmpty();
        assertThat(StoreService.getDloads("111")).isNotEmpty();
        Integer downloads = StoreService.getDloads("111").get(0);
        assertThat(downloads).isEqualTo(1);
        LibraryService.closeDataBase();

    }
}