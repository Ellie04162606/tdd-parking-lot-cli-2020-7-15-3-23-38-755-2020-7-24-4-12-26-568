package com.oocl.cultivation.test;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ParkingBoy {
    List<ParkingLot> parkingLots;
    String responseMessage;
    List<Ticket> ticketList;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public abstract Ticket parking(Car car);

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

    public Boolean isPositionEnough(List<ParkingLot> parkingLots) {
        if (parkingLots.stream().mapToInt(ParkingLot::getPlace).sum() >= parkingLots.size() * 10) {
            setResponseMessage("Not enough position.");
            return false;
        }
        return true;
    }

    public Boolean isCarParked(Car car){
        if (parkingLots.stream().filter(parkingLot -> parkingLot.getCars().contains(car)).collect(Collectors.toList()).size() > 0) {
            setResponseMessage("Car is parked");
            return true;
        }
        return false;
    }


}
