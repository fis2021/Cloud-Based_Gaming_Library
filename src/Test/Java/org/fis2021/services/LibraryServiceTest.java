package org.fis2021.services;

import org.apache.commons.io.FileUtils;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    @BeforeEach
    void setUp() throws IOException {
        FileSystemService.initTestDirectory();
        FileUtils.cleanDirectory(FileSystemService.getTestPathToFile().toFile());
        LibraryService.initTestDatabase();

    }
    @AfterEach
    void tearDown() {
        LibraryService.closeDataBase();
    }

    @Test
    @DisplayName("Checking if the library database is null or empty")
    void test1() {
        assertThat(LibraryService.getAllGames()).isNotNull();
        assertThat(LibraryService.getAllGames()).isEmpty();
    }

    @Test
    @DisplayName("Cheking if there can be added any game to library")
    void test2() throws GameAlreadyExistsException {
        LibraryService.addGame("Mario", "123");
        assertThat(LibraryService.getAllGames()).isNotEmpty();
        assertThat(LibraryService.getAllGames()).size().isEqualTo(1);
        assertThat(LibraryService.getGame("123")).isNotEmpty();
        String gamename = LibraryService.getGame("123").get(0);
        assertThat(gamename).isEqualTo("Mario");
    }

    @Test
    @DisplayName("Cheking the number of downloads a specific game has")
    void test3() throws GameAlreadyExistsException {
        LibraryService.addGame("Mario", "123");
        assertThat(LibraryService.getDloads("Mario")).isEqualTo(1);
    }

    @Test
    @DisplayName("Trying to search a game using a couple of letters from its name")
    void test4() throws GameAlreadyExistsException {
        LibraryService.addGame("Mario", "123");
        assertThat(LibraryService.getSearchedGame("io","123")).isNotEmpty();

    }
}