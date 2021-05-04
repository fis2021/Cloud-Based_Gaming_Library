package org.fis2021.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.fis2021.exceptions.GameAlreadyInStoreException;
import org.fis2021.exceptions.GameAlreadySentException;
import org.fis2021.exceptions.NoGameFoundException;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.models.Library;
import org.fis2021.models.Store;
import org.fis2021.models.Admin;
import org.dizitart.no2.objects.Cursor;
import org.fis2021.models.User;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import static org.fis2021.services.FileSystemService.getPathToFile;

public class AdminService {

    private static ObjectRepository<Admin> adminRepository;

    private static Nitrite database;

    public static void initDatabase(){
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("cbglapp-admin.db").toFile())
                .openOrCreate("admin", "admin");

        adminRepository = database.getRepository(Admin.class);
    }

    public static void closeDataBase(){
        database.close();
    }

    public static ArrayList<Admin> getAllGames()
    {
        ArrayList<Admin> list = new ArrayList<>();
        Cursor<Admin> cursor = adminRepository.find();
        for(Admin a1 : cursor)
            list.add(a1);
        return list;
    }

    public static void addGame(String gameName, String devId) throws GameAlreadySentException {
        checkIfGameWasSentToTheAdmin(gameName,devId);
        adminRepository.insert(new Admin(gameName,devId));

    }

    public static void removeGame(String gameName) {
        adminRepository.remove(eq("gameName",gameName));
    }

    private static void checkIfGameWasSentToTheAdmin(String gameName,String devId) throws GameAlreadySentException {
        Admin a = new Admin(gameName,devId);

        for (Admin admin1 : adminRepository.find()) {
            if (Objects.equals(a, admin1))
                throw new GameAlreadySentException(gameName);
        }
    }
}