package fontys.sot.rest.service.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Flight {

    // Attributes
    private int flight_number;
    private String origin;
    private String destination;
    private String departure;
    private String arrival;
    private String airline; // IATA code
    private List<Ticket> tickets = new ArrayList<>();
    // cheapestTicket
    // price (of the cheapest ticket)
    // departureDate
    // arrivalDate


    // Constructors
    public Flight() {}
    public Flight(String origin, String destination, String departure, String arrival, String airline) {
        // if (!isValidDate(departure)) throw new Exception("departure date must have this format: YYYY-MM-dd hh:mm");
        // if (!isValidDate(arrival)) throw new Exception("arrival date must have this format: YYYY-MM-dd hh:mm");

        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.airline = airline;
    }
    public Flight(int flight_number, String origin, String destination, String departure, String arrival, String airline) {
        // if (!isValidDate(departure)) throw new Exception("departure date must have this format: YYYY-MM-dd hh:mm");
        // if (!isValidDate(arrival)) throw new Exception("arrival date must have this format: YYYY-MM-dd hh:mm");

        this.flight_number = flight_number;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.airline = airline;
    }


    // Default Getters and Setters
    public int getFlight_number() { return flight_number; }
    public void setFlight_number(int flight_number) { this.flight_number = flight_number; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDeparture() { return departure; }
    public void setDeparture(String departure) {
        // if (!isValidDate(departure)) throw new Exception("departure date must have this format: YYYY-MM-dd hh:mm");
        this.departure = departure;
    }
    public String getArrival() { return arrival; }
    public void setArrival(String arrival) {
        // if (!isValidDate(departure)) throw new Exception("departure date must have this format: YYYY-MM-dd hh:mm");
        this.arrival = arrival;
    }
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


    // Methods
    public static boolean isValidDate(String dateStr){
        return getDateFromString(dateStr) != null;
    }

    public static Date getDateFromString(String dateString) {
        try {
            DateFormat df = new SimpleDateFormat("YYYY-MM-dd hh:mm");
            df.setLenient(false);
            return df.parse(dateString);
        } catch (ParseException e) {
            try {
                DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
                df.setLenient(false);
                return df.parse(dateString);
            } catch (ParseException e2) {
                return null;
            }
        }
    }


}
