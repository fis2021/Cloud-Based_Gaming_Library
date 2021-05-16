package org.fis2021.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    private static final String APPLICATION_FOLDER = ".cbgl-app";
    private static final String APPLICATION_FOLDER_TEST = ".cbgl-test";
    private static final String USER_FOLDER = System.getProperty("user.home");
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    public static final Path APPLICATION_TEST_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER_TEST);

    public static Path getPathToFile(String... path) {
        return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }

    public static Path getTestPathToFile(String... path) {
        return APPLICATION_TEST_PATH.resolve(Paths.get(".", path));
    }
    public static void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }

    public static void initTestDirectory(){
        Path applicationHomePath = FileSystemService.APPLICATION_TEST_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();

    }
}






