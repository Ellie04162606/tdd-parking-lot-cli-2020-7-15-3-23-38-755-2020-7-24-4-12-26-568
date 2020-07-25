package com.oocl.cultivation.test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int place;
    private List<Car> cars;
    private String name;

    public ParkingLot(String name) {
        this.place = 0;
        this.cars = new ArrayList<>();
        this.name = name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public List<Car> getCars() {
        return cars;
    }
}
