package com.oocl.cultivation.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    String ticket = "";
    List<Car> cars = new ArrayList<>();

    public String parking(Car car) {
        cars.add(car);
        ticket = car.getCarId();
        return ticket;
    }

    public Car fetching(String ticket) {
        List<Car> fetchCar = cars.stream().filter((Car car) -> car.getCarId().equals(ticket)).collect(Collectors.toList());
        if (fetchCar.size() == 1) {
            return fetchCar.get(0);
        }
        return null;
    }
}
