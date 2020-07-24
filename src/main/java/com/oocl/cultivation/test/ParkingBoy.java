package com.oocl.cultivation.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    List<Car> cars = new ArrayList<>();
    int parkingLot = 0;

    public String parking(Car car) {
        if (parkingLot >= 10) {
            return null;
        }
        if (cars.contains(car)) {
            return "Car is parked.";
        }
        cars.add(car);
        parkingLot++;
        Ticket ticket = new Ticket(car.getCarId());
        return ticket.getTicket();
    }

    public Car fetching(String ticket) {
        List<Car> fetchCar = cars.stream().filter((Car car) -> car.getCarId().equals(ticket)).collect(Collectors.toList());
        if (fetchCar.size() == 1) {
            cars.removeIf(car -> car.getCarId().equals(ticket));
            return fetchCar.get(0);
        }
        return null;
    }
}
