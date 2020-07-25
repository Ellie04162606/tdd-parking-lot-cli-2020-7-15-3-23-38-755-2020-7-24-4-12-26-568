package com.oocl.cultivation.test.test;

import com.oocl.cultivation.test.Car;
import com.oocl.cultivation.test.ParkingBoy;
import com.oocl.cultivation.test.Ticket;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_return_note_number_when_parking_given_a_car() {
        //given
        String carId = "0691";
        Car car = new Car(carId);
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        Ticket result = parkingBoy.parking(car);

        //then
        assertEquals("0691", result.getTicket());
    }

    @Test
    void should_return_a_car_when_fetching_given_note_number() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("0691");
        Ticket ticket = new Ticket(parkingBoy.parking(car).getTicket());

        //when
        Car fetchCar = parkingBoy.fetching(ticket);

        //then
        assertEquals(car, fetchCar);
    }

    @Test
    void should_return_no_car_when_fetching_given_wrong_ticket() {
        //given
        Ticket ticket = new Ticket("0000");
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
    void should_return_no_car_when_fetching_given_ticket_has_been_used() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("0691");
        Ticket ticket = new Ticket(parkingBoy.parking(car).getTicket());

        //when
        parkingBoy.fetching(ticket);
        Car result = parkingBoy.fetching(ticket);

        //then
        assertNull(result);
    }


    @Test
    void should_return_no_ticket_when_parking_given_parking_lot_more_than_10() {
        //given
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            cars.add(new Car("000" + i));
        }

        Car car = new Car("0691");
        ParkingBoy parkingBoy = new ParkingBoy();
        cars.forEach(parkingBoy::parking);

        //when
        Ticket result = parkingBoy.parking(car);

        //then
        assertNull(result);
    }

    @Test
    void should_return_wrong_when_parking_given_parked_car() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("0691");
        parkingBoy.parking(car);

        //when
        Ticket result = parkingBoy.parking(car);

        //then
        assertNull(result);
    }
}
