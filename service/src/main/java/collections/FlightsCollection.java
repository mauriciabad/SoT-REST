package collections;

import model.Flight;

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
        this.add(new Flight("BCN", "EIN", "2019-09-28T08:20", "2019-09-28T10:20", "Ryanair", 2, 120));
        this.add(new Flight("LHR", "CDG", "2019-09-29T10:10", "2019-09-29T12:10", "Transavia", 12, 60));
        this.add(new Flight("FRA", "MAD", "2019-09-28T08:25", "2019-09-28T13:10", "Ryanair", 6, 40));
        this.add(new Flight("ATL", "BCN", "2019-10-01T21:50", "2019-10-01T22:50", "Vueling", 14, 120));
        this.add(new Flight("HND", "PVG", "2019-10-13T17:30", "2019-10-13T108:0", "Vueling", 4, 95));
        this.add(new Flight("BCN", "MAD", "2019-10-25T12:15", "2019-10-25T13:30", "Transavia", 10, 15));
        this.add(new Flight("EIN", "BCN", "2020-10-19T06:45", "2019-10-19T15:30", "Ryanair", 7, 30));
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