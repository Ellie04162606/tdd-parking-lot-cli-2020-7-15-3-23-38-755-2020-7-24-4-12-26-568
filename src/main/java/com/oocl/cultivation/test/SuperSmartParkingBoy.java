package com.oocl.cultivation.test;

import java.util.ArrayList;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        this.ticketList = new ArrayList<>();
        this.parkingLots = parkingLots;
    }

    @Override
    public Ticket parking(Car car) {
        if (!isPositionEnough(parkingLots)) {
            return null;
        }

        if (isCarParked(car)) {
            return null;
        }
        ParkingLot parkingLot = parkingLots.stream().reduce((x, y) -> (x.getPlace()/x.getCapacity()) > (y.getPlace()/y.getCapacity()) ? x : y).get();
        if (parkingLot.getPlace() < parkingLot.getCapacity()) {
            parkingLot.getCars().add(car);
            parkingLot.setPlace(parkingLot.getPlace() + 1);
            Ticket ticket = new Ticket(car);
            ticketList.add(ticket);
            ticket.setParkingLot(parkingLot);
            return ticket;
        }
        return null;
    }
}
