package fontys.sot.rest.service.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightsCollection {
    private Map<Integer, Flight> flights = new HashMap<>();

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
