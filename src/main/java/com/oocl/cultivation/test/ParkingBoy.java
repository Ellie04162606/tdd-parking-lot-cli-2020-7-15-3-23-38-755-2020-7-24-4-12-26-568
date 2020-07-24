package com.oocl.cultivation.test;

public class ParkingBoy {
    String ticket = "ticket:";

    public String parking(Car car) {
        return ticket + car.getCarId();
    }

    public Car fetching(String noteNumber) {
        return new Car(noteNumber);
    }
}
