package com.oocl.cultivation.test;

public class ParkingBoy {
    String note = "noteNumber:";

    public String parking(Car car) {
        return note + car.getCarId();
    }
}
