package fontys.sot.rest.service.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightsCollection {
    private Map<Integer, Flight> flights = new HashMap<>();

    public List<Flight> getAll() {
        return new ArrayList<>(flights.values());
    }

    public List<Flight> filter(Integer flight_number, String origin, String destination, Date departure, Date arrival, String airline) {
        Stream<Flight> filteredFlights = flights.values().stream();

        if (flight_number != null) filteredFlights = filteredFlights.filter(flight -> flight.getFlight_number() == flight_number);
        if (origin != null)        filteredFlights = filteredFlights.filter(flight -> flight.getOrigin().equals(origin));
        if (destination != null)   filteredFlights = filteredFlights.filter(flight -> flight.getDestination().equals(destination));
        if (departure != null)     filteredFlights = filteredFlights.filter(flight -> flight.getDeparture().equals(departure));
        if (arrival != null)       filteredFlights = filteredFlights.filter(flight -> flight.getArrival().equals(arrival));
        if (airline != null)       filteredFlights = filteredFlights.filter(flight -> flight.getAirline().equals(airline));

        return filteredFlights.collect(Collectors.toList());
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
