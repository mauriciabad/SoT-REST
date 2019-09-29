package fontys.sot.rest.service.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightsCollection {

    // Singleton
    private static final FlightsCollection instance = new FlightsCollection();

    public static FlightsCollection getInstance() {
        return instance;
    }

    // Initialize with some data
    private FlightsCollection() {
        this.add(new Flight("BCN", "EIN", "2019-9-28 8:20", "2019-9-28 10:20", "Ryanair"));
        this.add(new Flight("LHR", "CDG", "2019-9-29 10:10", "2019-9-29 12:10", "Transavia"));
        this.add(new Flight("FRA", "MAD", "2019-9-28 8:25", "2019-9-28 13:10", "Ryanair"));
        this.add(new Flight("ATL", "BCN", "2019-10-1 21:50", "2019-10-1 22:50", "Vueling"));
        this.add(new Flight("HND", "PVG", "2019-10-13 17:30", "2019-10-13 18:0", "Vueling"));
        this.add(new Flight("BCN", "MAD", "2019-10-25 12:15", "2019-10-25 13:30", "Transavia"));
        this.add(new Flight("EIN", "BCN", "2020-10-19 6:45", "2019-10-19 15:30", "Ryanair"));
    }

    // Data structure containing flights
    private Map<Integer, Flight> flights = new HashMap<>();
    private int idCount = 0;

    // Methods
    public List<Flight> getAll() {
        return new ArrayList<>(flights.values());
    }
    public int total() {
        return flights.size();
    }
    public Flight get(int flightNumber) {
        return flights.get(flightNumber);
    }
    public boolean exists(int flightNumber) {
        return flights.containsKey(flightNumber);
    }
    public void remove(int flightNumber) {
        flights.remove(flightNumber);
    }
    public int add(Flight flight) {
        flight.setFlightNumber(getNewId());
        flights.putIfAbsent(flight.getFlightNumber(), flight);
        return flight.getFlightNumber();
    }
    public Flight update(Flight flight) {
        return flights.replace(flight.getFlightNumber(), flight);
    }
    public int getNewId() {
        return ++idCount;
    }
}