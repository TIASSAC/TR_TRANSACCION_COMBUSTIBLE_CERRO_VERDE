package com.example.generartransaccioncombustible.entities;

import java.io.Serializable;

public class StationORMEntity implements Serializable {
    private int IdStation;
    private String StationName;

    public StationORMEntity(){
    }


    public StationORMEntity(int IdStation,String StationName){
        this.IdStation = IdStation;
        this.StationName = StationName;
    }


    public int getIdStation() {
        return IdStation;
    }

    public void setIdStation(int idStation) {
        IdStation = idStation;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }



}
