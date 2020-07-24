package com.oocl.cultivation.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    String ticket = "";
    List<Car> cars = new ArrayList<>();
    int parkingLot = 0;

    public String parking(Car car) {
        if (parkingLot >= 10) {
            return null;
        }
        cars.add(car);
        parkingLot++;
        ticket = car.getCarId();
        return ticket;
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
