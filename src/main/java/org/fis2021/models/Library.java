package org.fis2021.models;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.dizitart.no2.objects.Cursor;
public class Library {
    private String gameName;
    private String userId;


    public Library(){

    }

    public Library(String gameName, String userId)
    {
        this.gameName = gameName;
        this.userId = userId;
    }

    public String getGameName()
    {
        return gameName;
    }

    public String getUserId(){
        return  userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return gameName.equals(library.gameName) && userId.equals(library.userId);
    }

    @Override
    public String toString() {
        return "Library{" +
                "gameName='" + gameName + '\'' +
                '}';
    }
}
