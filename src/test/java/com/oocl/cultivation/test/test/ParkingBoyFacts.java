package com.oocl.cultivation.test.test;

import com.oocl.cultivation.test.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_return_a_ticket_when_parking_given_a_car() {
        //given
        String carId = "0691";
        Car car = new Car(carId);
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();

        //when
        Ticket result = notSmartParkingBoy.parking(car);

        //then
        assertNotNull(result);
    }

    @Test
    void should_return_a_car_when_parking_boy_fetching_given_ticket() {
        //given
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();
        Car car = new Car("0691");

        //when
        Car fetchCar = notSmartParkingBoy.fetching(notSmartParkingBoy.parking(car));

        //then
        assertEquals(car, fetchCar);
    }

    @Test
    void should_return_multiple_ticket_when_parking_given_multiple_car() {
        //given
        List<Car> cars = new ArrayList<>();
        Car car1 = new Car("0001");
        Car car2 = new Car("0002");
        cars.add(car1);
        cars.add(car2);

        //when
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();
        List<Ticket> tickets = notSmartParkingBoy.parking(cars);
        //then
        assertNotNull(tickets);
    }

    @Test
    void should_return_corresponding_cars_when_fetching_given_multiple_tickets() {
        //given
        List<Car> cars = new ArrayList<>();
        Car car1 = new Car("0001");
        Car car2 = new Car("0002");
        cars.add(car1);
        cars.add(car2);
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();
        List<Ticket> tickets = notSmartParkingBoy.parking(cars);

        //when
        Car fetchCar = notSmartParkingBoy.fetching(tickets.get(1));

        //then
        assertEquals(car2, fetchCar);
    }

    @Test
    void should_return_no_car_when_fetching_given_wrong_ticket() {
        //given
        Ticket ticket = new Ticket(new Car("0000"));
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();

        //when
        Car car = notSmartParkingBoy.fetching(ticket);

        //then
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_fetching_given_without_ticket() {
        //given
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();

        //when
        Car car = notSmartParkingBoy.fetching(null);

        //then
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_fetching_given_ticket_has_been_used() {
        //given
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();
        Car car = new Car("0691");
        Ticket ticket = notSmartParkingBoy.parking(car);
        notSmartParkingBoy.fetching(ticket);

        //when
        Car result = notSmartParkingBoy.fetching(ticket);

        //then
        assertNull(result);
    }


    @Test
    void should_return_no_ticket_when_parking_given_parking_lot_full() {
        //given
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            cars.add(new Car("000" + i));
        }

        Car car = new Car("0691");
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();
        cars.forEach(notSmartParkingBoy::parking);

        //when
        Ticket result = notSmartParkingBoy.parking(car);

        //then
        assertNull(result);
    }

    @Test
    void should_return_wrong_when_parking_given_parked_car() {
        //given
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();
        Car car = new Car("0691");
        notSmartParkingBoy.parking(car);

        //when
        Ticket result = notSmartParkingBoy.parking(car);

        //then
        assertNull(result);
    }

    @Test
    void should_return_error_message_unrecognized_parking_ticket_when_get_error_message_given_fetch_no_car() {
        //given
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();
        Car car = new Car("0691");
        Ticket ticket = notSmartParkingBoy.parking(car);
        Car car1 = notSmartParkingBoy.fetching(ticket);

        Ticket ticket1 = notSmartParkingBoy.parking(car);
        Car car2 = notSmartParkingBoy.fetching(ticket);

        //when
        String result = notSmartParkingBoy.getResponseMessage();

        //then
        assertEquals("Unrecognized parking ticket.", result);
    }

    @Test
    void should_return_error_message_please_provide_your_parking_ticket_when_get_error_message_given_fetch_no_car() {
        //given
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();
        notSmartParkingBoy.fetching(null);

        //when
        String result = notSmartParkingBoy.getResponseMessage();

        //then
        assertEquals("Please provide your parking ticket.", result);
    }

    @Test
    void should_return_error_message_not_enough_position_when_get_error_message_given_parking_without_places() {
        //given
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            cars.add(new Car("000" + i));
        }

        Car car = new Car("0691");
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();
        cars.forEach(notSmartParkingBoy::parking);

        //when
        notSmartParkingBoy.parking(car);
        String result = notSmartParkingBoy.getResponseMessage();

        //then
        assertEquals("Not enough position.", result);
    }

    @Test
    void should_return_ticket_when_not_smart_parking_boy_parking_given_parking_lot_1_full_and_parking_to_parking_lot_2() {
        //given
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cars.add(new Car("000" + i));
        }

        Car car = new Car("0691");
        NotSmartParkingBoy notSmartParkingBoy = new NotSmartParkingBoy();
        cars.forEach(notSmartParkingBoy::parking);

        //when
        Ticket ticket = notSmartParkingBoy.parking(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_with_ticket_with_car_parking_in_more_empty_position_parking_lot_when_smart_parking_boy_parking_given_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parkingLots.add(new ParkingLot("parkingLot" + i));
        }

        parkingLots.get(0).setPlace(2);
        parkingLots.get(1).setPlace(3);

        Car car = new Car("0619");

        //when
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = smartParkingBoy.parking(car);

        //then
        assertEquals(parkingLots.get(0), ticket.getParkingLot());
    }

    @Test
    void should_return_a_ticket_when_smart_parking_boy_parking_given_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        Car car = new Car("0691");
        for (int i = 0; i < 2; i++) {
            parkingLots.add(new ParkingLot("parkingLot" + i));
        }
        parkingLots.get(0).setPlace(2);
        parkingLots.get(1).setPlace(9);

        //when
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = smartParkingBoy.parking(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_a_car_when_smart_parking_boy_fetching_given_a_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parkingLots.add(new ParkingLot("parkingLot" + i));
        }

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("0691");

        //when
        Car fetchCar = smartParkingBoy.fetching(smartParkingBoy.parking(car));

        //then
        assertEquals(car, fetchCar);
    }

    @Test
    void should_return_multiple_ticket_when_smart_parking_boy_parking_given_multiple_car() {
        //given
        List<Car> cars = new ArrayList<>();
        Car car1 = new Car("0001");
        Car car2 = new Car("0002");
        cars.add(car1);
        cars.add(car2);
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parkingLots.add(new ParkingLot("parkingLot" + i));
        }

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        //when
        List<Ticket> tickets = smartParkingBoy.parking(cars);
        //then
        assertNotNull(tickets);
    }

    @Test
    void should_return_corresponding_cars_when_smart_parking_boy_fetching_given_a_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parkingLots.add(new ParkingLot("parkingLot" + i));
        }
        List<Car> cars = new ArrayList<>();
        Car car1 = new Car("0001");
        Car car2 = new Car("0002");
        cars.add(car1);
        cars.add(car2);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        List<Ticket> tickets = smartParkingBoy.parking(cars);

        //when
        Car fetchCar = smartParkingBoy.fetching(tickets.get(1));

        //then
        assertEquals(car2, fetchCar);
    }

    @Test
    void should_return_no_car_when_smart_parking_boy_fetching_given_wrong_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parkingLots.add(new ParkingLot("parkingLot" + i));
        }
        Ticket ticket = new Ticket(new Car("0000"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        //when
        Car car = smartParkingBoy.fetching(ticket);

        //then
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_smart_parking_boy_fetching_given_without_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parkingLots.add(new ParkingLot("parkingLot" + i));
        }
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        //when
        Car car = smartParkingBoy.fetching(null);

        //then
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_smart_parking_boy_fetching_given_ticket_has_been_used() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parkingLots.add(new ParkingLot("parkingLot" + i));
        }
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("0691");
        Ticket ticket = smartParkingBoy.parking(car);
        smartParkingBoy.fetching(ticket);

        //when
        Car result = smartParkingBoy.fetching(ticket);

        //then
        assertNull(result);
    }


    @Test
    void should_return_ticket_with_car_parking_in_parking_lot_which_has_a_larger_available_position_rate_given_a_car_and_a_super_smart_parking_boy() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            parkingLots.add(new ParkingLot("parkingLot" + i, i + 9));
        }
        parkingLots.get(0).setPlace(8);
        parkingLots.get(1).setPlace(3);
        parkingLots.get(2).setPlace(1);

        Car car = new Car("0619");

        //when
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Ticket ticket = superSmartParkingBoy.parking(car);

        //then
        assertEquals(parkingLots.get(2), ticket.getParkingLot());
    }
}
