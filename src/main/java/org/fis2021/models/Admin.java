package org.fis2021.models;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.dizitart.no2.objects.Cursor;
public class Admin {
    private String gameName;
    private String devId;

    public Admin(){}

    public Admin(String gameName,String devId)
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
        Admin admin = (Admin) o;
        return gameName.equals(admin.gameName) && devId.equals(admin.devId);
    }
    @Override
    public String toString() {
        return "Admin {" +
                "gameName = '" + gameName + '\'' +
                '}';
    }
}