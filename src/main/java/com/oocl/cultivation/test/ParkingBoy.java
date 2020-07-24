package com.oocl.cultivation.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    List<Car> cars = new ArrayList<>();
    ParkingLot parkingLot = new ParkingLot();

    public String parking(Car car) {
        if (parkingLot.getPlace() >= 10) {
            return null;
        }
        if (cars.contains(car)) {
            return "Car is parked.";
        }
        cars.add(car);
        parkingLot.setPlace(parkingLot.getPlace() + 1);
        parkingLot.setCars(cars);
        Ticket ticket = new Ticket(car.getCarId());
        ticket.setStatus("active");
        return ticket.getTicket();
    }

    public Car fetching(Ticket ticket) {
        List<Car> fetchCar = parkingLot.getCars().stream().filter((Car car) -> car.getCarId().equals(ticket.getTicket())).collect(Collectors.toList());
        if (fetchCar.size() == 1) {
            parkingLot.getCars().removeIf(car -> car.getCarId().equals(ticket.getTicket()));
            ticket.setStatus("inactive");
            return fetchCar.get(0);
        }
        return null;
    }
}
