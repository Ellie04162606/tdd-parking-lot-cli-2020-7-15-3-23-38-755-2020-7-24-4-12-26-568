package com.oocl.cultivation.test;


import java.util.Date;

public class Ticket {
    private String ticket;
    private ParkingLot parkingLot;


    public Ticket(Car car) {
        this.ticket = car.getCarId() + new Date().getTime();
    }

    public String getTicket() {
        return ticket;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
