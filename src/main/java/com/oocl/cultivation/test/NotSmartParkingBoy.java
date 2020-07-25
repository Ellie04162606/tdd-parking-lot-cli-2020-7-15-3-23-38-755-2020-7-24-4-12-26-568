package com.oocl.cultivation.test;

import java.util.ArrayList;

public class NotSmartParkingBoy extends ParkingBoy{

    public NotSmartParkingBoy() {
        this.parkingLots = new ArrayList<>();
        this.ticketList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parkingLots.add(new ParkingLot("parkingLot" + i));
        }
    }

    public Ticket parking(Car car) {
        if (!isPositionEnough(parkingLots)){
            return null;
        }

        if (isCarParked(car)) {
            return null;
        }

        ParkingLot parkingLot1 = parkingLots.get(0);
        if (parkingLot1.getPlace()<=10){
            parkingLot1.getCars().add(car);
            parkingLot1.setPlace(parkingLot1.getPlace() + 1);
            Ticket ticket = new Ticket(car);
            ticketList.add(ticket);
            ticket.setParkingLot(parkingLot1);
            return ticket;

        }
        ParkingLot parkingLot2 = parkingLots.get(1);
        parkingLot2.getCars().add(car);
        parkingLot2.setPlace(parkingLot2.getPlace() + 1);
        Ticket ticket = new Ticket(car);
        ticketList.add(ticket);
        ticket.setParkingLot(parkingLot2);
        return ticket;

    }
}
