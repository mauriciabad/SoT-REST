package fontys.sot.rest.service.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightsCollection {

    // Singleton
    private static final FlightsCollection instance = new FlightsCollection();
    public static FlightsCollection getInstance(){ return instance; }

    // Initialize with some data
    private FlightsCollection(){
        this.add(new Flight(0, "BCN", "EIN", new GregorianCalendar(2019,9,28,8,20), new GregorianCalendar(2019,9,28,10,20), "Ryanair"));
        this.add(new Flight(1, "LHR", "CDG", new GregorianCalendar(2019,9,29,10,10), new GregorianCalendar(2019,9,29,12,10), "Transavia"));
        this.add(new Flight(2, "FRA", "MAD", new GregorianCalendar(2019,9,28,8,25), new GregorianCalendar(2019,9,28,13,10), "Ryanair"));
        this.add(new Flight(3, "ATL", "BCN", new GregorianCalendar(2019,10,1,21,50), new GregorianCalendar(2019,10,1,22,50), "Vueling"));
        this.add(new Flight(4, "HND", "PVG", new GregorianCalendar(2019,10,13,17,30), new GregorianCalendar(2019,10,13,18,0), "Vueling"));
        this.add(new Flight(5, "BCN", "MAD", new GregorianCalendar(2019,10,25,12,15), new GregorianCalendar(2019,10,25,13,30), "Transavia"));
        this.add(new Flight(6, "EIN", "BCN", new GregorianCalendar(2019,10,19,6,45), new GregorianCalendar(2019,10,19,15,30), "Ryanair"));
    }

    // Data structure containing flights
    private Map<Integer, Flight> flights = new HashMap<>();

    // Methods
    public List<Flight> getAll() {
        return new ArrayList<>(flights.values());
    }
    public int total() {
        return flights.size();
    }
    public Flight get(int flight_number) {
        return flights.get(flight_number);
    }
    public boolean exists(int flight_number){
        return flights.containsKey(flight_number);
    }
    public void remove(int flight_number){
        flights.remove(flight_number);
    }
    public Flight add(Flight flight) {
        return flights.putIfAbsent(flight.getFlight_number(), flight);
    }
}
