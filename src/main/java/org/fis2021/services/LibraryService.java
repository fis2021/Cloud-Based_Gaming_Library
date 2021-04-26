package org.fis2021.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.models.Library;
import org.dizitart.no2.objects.Cursor;
import org.fis2021.models.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.fis2021.services.FileSystemService.getPathToFile;

public class LibraryService {

    private static ObjectRepository<Library> libRepository;

    private static Nitrite database;

    public static void initDatabase(){
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("cbglapp-library.db").toFile())
                .openOrCreate("admin", "admin");

        libRepository = database.getRepository(Library.class);
    }

    public static void closeDataBase(){
        database.close();
    }

    public static void addGame(String gameName, NitriteId userId) throws GameAlreadyExistsException {
        checkGameAlreadyExist(gameName,userId);
        libRepository.insert(new Library(gameName, userId));
    }

    private static void checkGameAlreadyExist(String gameName, NitriteId userId) throws GameAlreadyExistsException {
        Library l = new Library(gameName,userId);

        for (Library lib : libRepository.find()) {
            if (Objects.equals(l, lib))
                throw new GameAlreadyExistsException(gameName);
        }
    }
}
