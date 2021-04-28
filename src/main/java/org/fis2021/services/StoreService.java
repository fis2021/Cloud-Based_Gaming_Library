package org.fis2021.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.fis2021.exceptions.GameAlreadyInStoreException;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.models.Store;
import org.dizitart.no2.objects.Cursor;
import org.fis2021.models.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.fis2021.services.FileSystemService.getPathToFile;

public class StoreService {

    private static ObjectRepository<Store> storeRepository;

    private static Nitrite database;

    public static void initDatabase(){
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("cbglapp-store.db").toFile())
                .openOrCreate("admin", "admin");

        storeRepository = database.getRepository(Store.class);
    }

    public static void closeDataBase(){
        database.close();
    }

    public static void addGame(String gameName, int userId) throws GameAlreadyInStoreException {
        checkGameInStore(gameName,userId);
        storeRepository.insert(new Store(gameName, userId));
    }

    private static void checkGameInStore(String gameName, int userId) throws GameAlreadyInStoreException {
        Store s = new Store(gameName,userId);

        for (Store store1 : storeRepository.find()) {
            if (Objects.equals(s, store1))
                throw new GameAlreadyInStoreException(gameName);
        }
    }
}
