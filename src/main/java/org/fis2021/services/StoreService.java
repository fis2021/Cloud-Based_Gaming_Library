package org.fis2021.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.fis2021.exceptions.GameAlreadyInStoreException;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.models.Library;
import org.fis2021.models.Store;
import org.dizitart.no2.objects.Cursor;
import org.fis2021.models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import static org.fis2021.services.FileSystemService.getPathToFile;

public class StoreService {

    private static ObjectRepository<Store> storeRepository;

    private static Nitrite database;
    private static int index = 0;

    public static void initDatabase(){
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("cbglapp-store.db").toFile())
                .openOrCreate("admin", "admin");

        storeRepository = database.getRepository(Store.class);
    }

    public static void closeDataBase(){
        database.close();
    }

    public static void addGame(String gameName, String devId) throws GameAlreadyInStoreException {
        checkGameInStore(gameName,devId);
        storeRepository.insert(new Store(gameName,devId));
        index ++;
    }
    public static ArrayList<String> getGame(String devId) {
        ArrayList<String> s = new ArrayList<String>();
        Cursor<Store> cursor = storeRepository.find(ObjectFilters.eq("devId",devId));
        for(Store s1 : cursor)
            s.add(s1.getGameName());
        return s;

    }

    public static ArrayList<String> getAllGames()
    {
        ArrayList<String> list = new ArrayList<>();
        Cursor<Store> cursor = storeRepository.find();
        for(Store s1 : cursor)
            list.add(s1.getGameName());
        return list;
    }

    private static void checkGameInStore(String gameName,String devId) throws GameAlreadyInStoreException {
        Store s = new Store(gameName,devId);

        for (Store store1 : storeRepository.find()) {
            if (Objects.equals(s, store1))
                throw new GameAlreadyInStoreException(gameName);
        }
    }

    public static int getIndex()
    {
        return index;
    }
}
