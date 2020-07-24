package com.oocl.cultivation.test;

public class Ticket {
    private String ticket;
    private String status;

    public Ticket(String ticket) {
        this.ticket = ticket;
        this.status = "inactive";
    }

    public String getTicket() {
        return ticket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
