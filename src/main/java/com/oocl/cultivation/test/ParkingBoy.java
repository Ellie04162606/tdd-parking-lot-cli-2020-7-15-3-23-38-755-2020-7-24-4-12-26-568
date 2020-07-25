package com.oocl.cultivation.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    List<ParkingLot> parkingLots;
    String responseMessage;
    List<Ticket> ticketList;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
        this.ticketList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parkingLots.add(new ParkingLot("parkingLot" + i));
        }
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Ticket parking(Car car) {


        if (parkingLots.stream().mapToInt(ParkingLot::getPlace).sum() >= 20) {
            setResponseMessage("Not enough position.");
            return null;
        }

        if (parkingLots.stream().filter(parkingLot -> parkingLot.getCars().contains(car)).collect(Collectors.toList()).size() > 0) {
            setResponseMessage("Car is parked");
            return null;
        }
        ParkingLot parkingLot1 = parkingLots.get(0);
        if (parkingLot1.getPlace()<=10){
            parkingLot1.getCars().add(car);
            parkingLot1.setPlace(parkingLot1.getPlace() + 1);
            Ticket ticket = new Ticket(car);
            ticketList.add(ticket);
            ticket.setParkingLot(parkingLot1);
            return ticket;

        }
        ParkingLot parkingLot2 = parkingLots.get(1);
        parkingLot2.getCars().add(car);
        parkingLot2.setPlace(parkingLot2.getPlace() + 1);
        Ticket ticket = new Ticket(car);
        ticketList.add(ticket);
        ticket.setParkingLot(parkingLot2);
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

            ParkingLot parkingLot = ticket.getParkingLot();
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
