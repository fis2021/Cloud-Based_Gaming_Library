package org.fis2021.services;

import org.dizitart.no2.Filter;
import org.dizitart.no2.FindOptions;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.fis2021.exceptions.GameAlreadyExistsException;
import org.fis2021.exceptions.NoGameFoundException;
import org.fis2021.models.Library;
import org.dizitart.no2.objects.Cursor;

import java.util.ArrayList;
import java.util.Locale;
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

    public static void addGame(String gameName, String userId) throws GameAlreadyExistsException {
        checkGameAlreadyExist(gameName,userId);
        libRepository.insert(new Library(gameName, userId));
    }

    private static void checkGameAlreadyExist(String gameName, String userId) throws GameAlreadyExistsException {
        Library l = new Library(gameName,userId);

        for (Library lib : libRepository.find()) {
            if (Objects.equals(l, lib))
                throw new GameAlreadyExistsException(gameName);
        }
    }

    public static ArrayList<String> getGame(String userId) {
         ArrayList<String> l = new ArrayList<String>();
        Cursor<Library> cursor = libRepository.find(ObjectFilters.eq("userId",userId));
        for(Library lib : cursor)
            l.add(lib.getGameName());
        return l;

    }

    public static ArrayList<String> getSearchedGame(String strname , String userId){
        ArrayList<String> l = new ArrayList<>();
        Cursor<Library> cursor = libRepository.find(ObjectFilters.eq("userId",userId));
        for(Library lib : cursor)
        {
            if(lib.getGameName().toLowerCase().indexOf(strname.toLowerCase())!= -1)
            l.add(lib.getGameName());
        }

        return l;

    }


}
