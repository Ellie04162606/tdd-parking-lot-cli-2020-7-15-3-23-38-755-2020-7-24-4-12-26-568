package com.oocl.cultivation.test;

import java.util.ArrayList;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    List<Ticket> tickets = new ArrayList<>();

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.ticketList = new ArrayList<>();
    }

    @Override
    public Ticket parking(Car car) {
        if (!isPositionEnough(parkingLots)) {
            return null;
        }

        if (isCarParked(car)) {
            return null;
        }

        ParkingLot parkingLot = parkingLots.stream().reduce((x, y) -> x.getPlace() < y.getPlace() ? x : y).get();
        if (parkingLot.getPlace() < 10) {
            parkingLot.getCars().add(car);
            parkingLot.setPlace(parkingLot.getPlace() + 1);
            Ticket ticket = new Ticket(car);
            ticketList.add(ticket);
            ticket.setParkingLot(parkingLot);
            return ticket;
        }
        return null;
    }

    public List<Ticket> parking(List<Car> cars) {
        if (!isPositionEnough(parkingLots)) {
            return null;
        }

        if (cars.stream().anyMatch(this::isCarParked)) {
            return null;
        }

        ParkingLot parkingLot = parkingLots.stream().reduce((x, y) -> x.getPlace() < y.getPlace() ? x : y).get();
        for (Car car : cars){
            if (parkingLot.getPlace() < 10) {
                parkingLot.getCars().add(car);
                parkingLot.setPlace(parkingLot.getPlace() + 1);
                Ticket ticket = new Ticket(car);
                ticketList.add(ticket);
                ticket.setParkingLot(parkingLot);
                tickets.add(ticket);
            }
        }
        return tickets;
    }
}
