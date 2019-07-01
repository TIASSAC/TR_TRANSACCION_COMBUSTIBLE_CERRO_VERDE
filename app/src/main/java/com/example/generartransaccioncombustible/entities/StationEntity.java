package com.example.generartransaccioncombustible.entities;

public class StationEntity {
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

    private int IdStation;
    private String StationName;
}
