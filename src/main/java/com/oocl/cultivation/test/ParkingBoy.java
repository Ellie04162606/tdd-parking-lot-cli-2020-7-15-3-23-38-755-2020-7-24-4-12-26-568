package com.oocl.cultivation.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    ParkingLot parkingLot = new ParkingLot();
    String responseMessage;
    List<Ticket> ticketList = new ArrayList<>();

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Ticket parking(Car car) {
        if (parkingLot.getPlace() >= 10) {
            return null;
        }
        if (parkingLot.getCars().contains(car)) {
            return null;
        }
        parkingLot.getCars().add(car);
        parkingLot.setPlace(parkingLot.getPlace() + 1);
        Ticket ticket = new Ticket(car);
        ticketList.add(ticket);
        return ticket;
    }

    public Car fetching(Ticket ticket) {
        if (ticket == null) {
            setResponseMessage("Please provide your parking ticket.");
        }
        if (!ticketList.contains(ticket) && ticket != null) {
            setResponseMessage("Unrecognized parking ticket.");
        }
        if (ticketList.contains(ticket)) {
            assert ticket != null;
            String carNumber = ticket.getTicket().substring(0, 4);
            List<Car> fetchCar = parkingLot.getCars().stream().filter((Car car) -> car.getCarId().equals(carNumber)).collect(Collectors.toList());

            if (fetchCar.size() == 1) {
                parkingLot.getCars().removeIf(car -> car.getCarId().equals(carNumber));
                parkingLot.setPlace(parkingLot.getPlace() - 1);
                ticketList.removeIf(x -> x.getTicket().equals(ticket.getTicket()));
                return fetchCar.get(0);
            }
        }

        return null;
    }
}
