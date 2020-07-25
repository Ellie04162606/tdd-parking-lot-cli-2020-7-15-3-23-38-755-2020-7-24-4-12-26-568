package com.oocl.cultivation.test;


import java.util.Date;

public class Ticket {
    private String ticket;

    public Ticket(Car car) {
        this.ticket = car.getCarId() + new Date().getTime();
    }

    public String getTicket() {
        return ticket;
    }
}
