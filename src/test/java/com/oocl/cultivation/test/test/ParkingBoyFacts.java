package com.oocl.cultivation.test.test;

import com.oocl.cultivation.test.Car;
import com.oocl.cultivation.test.ParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParkingBoyFacts {
    @Test
    void should_return_note_number_when_parking_given_a_car() {
        //given
        String carId = "0691";
        Car car = new Car(carId);
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        String result = parkingBoy.parking(car);

        //then
        assertEquals("0691", result);
    }

    @Test
    void should_return_a_car_when_fetching_given_note_number() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("0691");
        String ticket = parkingBoy.parking(car);

        //when
        Car fetchCar = parkingBoy.fetching(ticket);

        //then
        assertEquals(car, fetchCar);
    }

    @Test
    void should_return_no_car_when_fetching_given_wrong_ticket() {
        //given
        String ticket = "0000";
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        Car car = parkingBoy.fetching(ticket);

        //then
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_fetching_given_without_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        Car car = parkingBoy.fetching(null);

        //then
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_fetching_given_ticket_has_benn_used() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("0691");
        String ticket = parkingBoy.parking(car);

        //when
        Car fetchCar = parkingBoy.fetching(ticket);
        Car result = parkingBoy.fetching(ticket);

        //then
        assertNull(result);
    }

    @Test
    void should_return_no_ticket_when_parking_given_parking_lot_more_than_10() {
        //given
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("0001"));
        cars.add(new Car("0002"));
        cars.add(new Car("0003"));
        cars.add(new Car("0004"));
        cars.add(new Car("0005"));
        cars.add(new Car("0006"));
        cars.add(new Car("0007"));
        cars.add(new Car("0008"));
        cars.add(new Car("0009"));
        cars.add(new Car("0010"));
        Car car = new Car("0691");
        ParkingBoy parkingBoy = new ParkingBoy();
        cars.forEach(parkingBoy::parking);

        //when
        String result = parkingBoy.parking(car);

        //then
        assertNull(result);
    }

}
