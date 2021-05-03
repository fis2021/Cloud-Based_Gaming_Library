package org.fis2021.models;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.dizitart.no2.objects.Cursor;
public class Store {
    private String gameName;
    private String devId;

    public Store(){}

    public Store(String gameName,String devId)
    {
        this.gameName = gameName;
        this.devId = devId;
    }

    public String getGameName()
    {
        return gameName;
    }
    public String getDevId(){return devId;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return gameName.equals(store.gameName) && devId.equals(store.devId);
    }

    @Override
    public String toString() {
        return "Store {" +
                "gameName = '" + gameName + '\'' +
                '}';
    }
}