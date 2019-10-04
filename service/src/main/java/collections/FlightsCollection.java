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
        this.add(new Flight("BCN", "EIN", "2019-09-28 08:20", "2019-09-28 10:20", "Ryanair", 2, 120));
        this.add(new Flight("LHR", "CDG", "2019-09-29 10:10", "2019-09-29 12:10", "Transavia", 1, 60));
        this.add(new Flight("FRA", "MAD", "2019-09-28 08:25", "2019-09-28 13:10", "Ryanair", 3, 40));
        this.add(new Flight("ATL", "BCN", "2019-10-01 21:50", "2019-10-01 22:50", "Vueling", 1, 120));
        this.add(new Flight("HND", "PVG", "2019-10-13 17:30", "2019-10-13 108:0", "Vueling", 2, 95));
        this.add(new Flight("BCN", "MAD", "2019-10-25 12:15", "2019-10-25 13:30", "Transavia", 2, 15));
        this.add(new Flight("EIN", "BCN", "2020-10-19 06:45", "2019-10-19 15:30", "Ryanair", 25, 30));
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
    public Flight get(int flightId) {
        return flights.get(flightId);
    }
    public boolean exists(int flightId) {
        return flights.containsKey(flightId);
    }
    public void remove(int flightId) {
        flights.remove(flightId);
    }
    public int add(Flight flight) {
        flight.setId(getNewId());
        flights.putIfAbsent(flight.getId(), flight);
        return flight.getId();
    }
    public Flight update(Flight flight) {
        return flights.replace(flight.getId(), flight);
    }
    public int getNewId() {
        return ++idCount;
    }
}