package fontys.sot.rest.service.model;

import java.util.*;

public class Flight {

    // Attributes
    private int flight_number;
    private String origin;
    private String destination;
    private Date departure;
    private Date arrival;
    private String airline; // IATA code
    private List<Ticket> tickets = new ArrayList<>();
    // Price: of the cheapest ticket

    // Constructors
    public Flight() {}
    public Flight(int flight_number, String origin, String destination, Date departure, Date arrival, String airline) {
        this.flight_number = flight_number;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.airline = airline;
    }
    public Flight(int flight_number, String origin, String destination, Calendar departure, Calendar arrival, String airline) {
        this.flight_number = flight_number;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure.getTime();
        this.arrival = arrival.getTime();
        this.airline = airline;
    }

    // Default Getters and Setters
    public int getFlight_number() { return flight_number; }
    public void setFlight_number(int flight_number) { this.flight_number = flight_number; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public Date getDeparture() { return departure; }
    public void setDeparture(Date departure) { this.departure = departure; }
    public Date getArrival() { return arrival; }
    public void setArrival(Date arrival) { this.arrival = arrival; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }

    // Extra Getters
    public Ticket getCheapestTicket() {
        return tickets.stream().reduce(null, (cheapestTicket, ticket) -> ticket.getPrice() < cheapestTicket.getPrice() ? ticket : cheapestTicket);
    }
    public Integer getPrice() {
        Ticket cheapestTicket = getCheapestTicket();
        return cheapestTicket != null ? cheapestTicket.getPrice() : null;
    }
    public String getCurrency() {
        Ticket cheapestTicket = getCheapestTicket();
        return cheapestTicket != null ? cheapestTicket.getCurrency() : null;
    }


}
