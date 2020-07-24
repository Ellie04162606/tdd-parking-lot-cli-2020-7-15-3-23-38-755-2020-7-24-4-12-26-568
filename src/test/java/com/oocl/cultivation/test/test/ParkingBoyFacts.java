package com.oocl.cultivation.test.test;

import com.oocl.cultivation.test.Car;
import com.oocl.cultivation.test.ParkingBoy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingBoyFacts {
    @Test
    void should_return_note_number_when_parking_a_car() {
        //given
        String carId = "0691";
        Car car = new Car(carId);
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        String result = parkingBoy.parking(car);

        //then
        assertEquals("noteNumber:0691", result);
    }
}
