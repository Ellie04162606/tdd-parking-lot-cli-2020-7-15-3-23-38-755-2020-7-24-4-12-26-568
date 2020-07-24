package com.oocl.cultivation.test.test;

import com.oocl.cultivation.test.Car;
import com.oocl.cultivation.test.ParkingBoy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("noteNumber:0691", result);
    }

    @Test
    void should_return_a_car_when_fetching_given_note_number() {
        //given
        String noteNumber = "0691";
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        Car car = parkingBoy.fetching(noteNumber);

        //then
        assertEquals("0691", car.getCarId());
    }
}
