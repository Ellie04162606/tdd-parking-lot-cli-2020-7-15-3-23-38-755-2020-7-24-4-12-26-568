package com.oocl.cultivation.test.test;

import com.oocl.cultivation.test.Car;
import com.oocl.cultivation.test.ParkingBoy;
import org.junit.jupiter.api.Test;

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

}
