package resources;

import collections.FlightsCollection;
import collections.UsersCollection;
import model.*;
import utilities.ResponseCustom;
import utilities.ResponseError;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("flights")
public class FlightsResources {

    /*  - - - - - - - - - -  Data  - - - - - - - - - -  */

    private FlightsCollection flights = FlightsCollection.getInstance();



    /*  - - - - - - - - - -  Endpoints  - - - - - - - - - -  */

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFlightByQuery(
            @QueryParam("flightNumber") Integer flightNumber,
            @QueryParam("origin") String origin,
            @QueryParam("destination") String destination,
            @QueryParam("departure") String departure,
            @QueryParam("departureBefore") String departureBefore,
            @QueryParam("departureAfter") String departureAfter,
            @QueryParam("arrival") String arrival,
            @QueryParam("arrivalBefore") String arrivalBefore,
            @QueryParam("arrivalAfter") String arrivalAfter,
            @QueryParam("price") Integer price,
            @QueryParam("maxPrice") Integer maxPrice,
            @QueryParam("airline") String airline) {
        Stream<Flight> filteredFlights = flights.getAll().stream();

        if (flightNumber != null) filteredFlights = filteredFlights.filter(flight -> flightNumber == flight.getFlightNumber());
        if (origin != null)        filteredFlights = filteredFlights.filter(flight -> origin.equals(flight.getOrigin()));
        if (destination != null)   filteredFlights = filteredFlights.filter(flight -> destination.equals(flight.getDestination()));
        if (airline != null)       filteredFlights = filteredFlights.filter(flight -> airline.equals(flight.getAirline()));
        if (price != null)         filteredFlights = filteredFlights.filter(flight -> price.equals(flight.getPrice()));

        if (maxPrice != null) {
            filteredFlights = filteredFlights.filter(flight -> {
                Integer flightPrice = flight.getPrice();
                return flightPrice != null && flightPrice <= maxPrice;
            });
        }

        // For each date we need to check if it has correct format. This is why there are so many lines...
        if (departure != null) {
            if (!Flight.isValidDate(departure)) return new ResponseError(422, "Parameter 'departure' must be in this format: YYYY-MM-ddThh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(departure).equals(Flight.getDateFromString(flight.getDeparture())));
        }
        if (arrival != null) {
            if (!Flight.isValidDate(arrival)) return new ResponseError(422, "Parameter 'arrival' must be in this format: YYYY-MM-ddThh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(arrival).equals(Flight.getDateFromString(flight.getArrival())));
        }
        if (departureBefore != null) {
            if (!Flight.isValidDate(departureBefore)) return new ResponseError(422, "Parameter 'departureBefore' must be in this format: YYYY-MM-ddThh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(departureBefore).after(Flight.getDateFromString(flight.getDeparture())));
        }
        if (departureAfter != null) {
            if (!Flight.isValidDate(departureAfter)) return new ResponseError(422, "Parameter 'departureAfter' must be in this format: YYYY-MM-ddThh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(departureAfter).before(Flight.getDateFromString(flight.getDeparture())));
        }
        if (arrivalBefore != null) {
            if (!Flight.isValidDate(arrivalBefore)) return new ResponseError(422, "Parameter 'arrivalBefore' must be in this format: YYYY-MM-ddThh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(arrivalBefore).after(Flight.getDateFromString(flight.getArrival())));
        }
        if (arrivalAfter != null) {
            if (!Flight.isValidDate(arrivalAfter)) return new ResponseError(422, "Parameter 'arrivalAfter' must be in this format: YYYY-MM-ddThh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(arrivalAfter).before(Flight.getDateFromString(flight.getArrival())));
        }

        return ResponseCustom.build(200, filteredFlights.collect(Collectors.toList()));
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createFlight(Flight flight) {
        if (flight.getOrigin() == null) return new ResponseError(422, "Missing 'origin' parameter").build();
        if (flight.getDestination() == null) return new ResponseError(422, "Missing 'destination' parameter").build();
        if (flight.getDeparture() == null) return new ResponseError(422, "Missing 'departure' parameter").build();
        if (flight.getArrival() == null) return new ResponseError(422, "Missing 'arrival' parameter").build();
        if (flight.getAirline() == null) return new ResponseError(422, "Missing 'airline' parameter").build();
        if (flight.getDeparture() != null && !Flight.isValidDate(flight.getDeparture())) return new ResponseError(422, "Parameter 'departure' must be in this format: YYYY-MM-ddThh:mm").build();
        if (flight.getArrival() != null && !Flight.isValidDate(flight.getArrival())) return new ResponseError(422, "Parameter 'arrival' must be in this format: YYYY-MM-ddThh:mm").build();

        int flightNumber = flights.add(flight);

        return ResponseCustom.build(200, flights.get(flightNumber));
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response createFlight(@FormParam("origin") String origin, @FormParam("destination") String destination, @FormParam("departure") String departure, @FormParam("arrival") String arrival, @FormParam("airline") String airline) {
        if (origin == null) return new ResponseError(422, "Missing 'origin' parameter").build();
        if (destination == null) return new ResponseError(422, "Missing 'destination' parameter").build();
        if (departure == null) return new ResponseError(422, "Missing 'departure' parameter").build();
        if (arrival == null) return new ResponseError(422, "Missing 'arrival' parameter").build();
        if (airline == null) return new ResponseError(422, "Missing 'airline' parameter").build();
        if (!Flight.isValidDate(departure)) return new ResponseError(422, "Parameter 'departure' must be in this format: YYYY-MM-ddThh:mm").build();
        if (!Flight.isValidDate(arrival)) return new ResponseError(422, "Parameter 'arrival' must be in this format: YYYY-MM-ddThh:mm").build();

        int flightNumber = flights.add(new Flight(origin, destination, departure, arrival, airline));

        return ResponseCustom.build(200, flights.get(flightNumber));
    }

    @PUT
    @Path("{flightNumber}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateFlight(Flight flight, @PathParam("flightNumber") int flightNumber) {
        if (flights.exists(flightNumber)){
            if (flight.getDeparture() != null && !Flight.isValidDate(flight.getDeparture())) return new ResponseError(422, "Parameter 'departure' must be in this format: YYYY-MM-ddThh:mm").build();
            if (flight.getArrival() != null && !Flight.isValidDate(flight.getArrival())) return new ResponseError(422, "Parameter 'arrival' must be in this format: YYYY-MM-ddThh:mm").build();

            Flight oldFlight = flights.get(flightNumber);

            if (flight.getOrigin() != null) oldFlight.setOrigin(flight.getOrigin());
            if (flight.getDestination() != null) oldFlight.setDestination(flight.getDestination());
            if (flight.getDeparture() != null) oldFlight.setDeparture(flight.getDeparture());
            if (flight.getArrival() != null) oldFlight.setArrival(flight.getArrival());
            if (flight.getAirline() != null) oldFlight.setAirline(flight.getAirline());

            flights.update(oldFlight);

            return ResponseCustom.build(200, flights.get(flightNumber));
        }else{
            return new ResponseError(404, "Flight with flightNumber " + flightNumber + " doesn't exist").build();
        }
    }

    @GET
    @Path("{flightNumber}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFlightById(@PathParam("flightNumber") int flightNumber) {
        if (flights.exists(flightNumber)) {
            return ResponseCustom.build(200, flights.get(flightNumber));
        } else {
            return new ResponseError(404, "Flight with flightNumber " + flightNumber + " doesn't exist").build();
        }
    }

    @DELETE
    @Path("{flightNumber}")
    public Response deleteFlight(@PathParam("flightNumber") int flightNumber) {
        flights.remove(flightNumber);
        return ResponseCustom.build();
    }

    @GET
    @Path("{flightNumber}/tickets")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFlightTicketsById(@PathParam("flightNumber") int flightNumber) {
        if (flights.exists(flightNumber)) {
            return ResponseCustom.build(200, flights.get(flightNumber).getTickets());
        } else {
            return new ResponseError(404, "Flight with flightNumber " + flightNumber + " doesn't exist").build();
        }
    }

    @POST
    @Path("{flightNumber}/tickets/{ticketId}/buy")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFlightTicketsById(@PathParam("flightNumber") int flightNumber, @PathParam("ticketId") int ticketId, @QueryParam("buyerId") Integer buyerId) {
        if (buyerId == null) return new ResponseError(422, "Missing 'buyerId' parameter").build();

        if (!flights.exists(flightNumber)) return new ResponseError(404, "Flight with flightNumber " + flightNumber + " doesn't exist").build();
        Flight flight = flights.get(flightNumber);
        List<Ticket> tickets = flight.getTickets();

        if (ticketId >= tickets.size()) return new ResponseError(404, "Ticket with ticketId " + ticketId + " doesn't exist").build();
        Ticket ticket = tickets.get(ticketId);

        if (!ticket.isForSale()) return new ResponseError(400, "Ticket with ticketId " + ticketId + " is not for sale").build();
        UsersCollection users = UsersCollection.getInstance();

        if (!users.exists(buyerId)) return new ResponseError(404, "User with userId " + buyerId + " doesn't exist").build();
        ticket.buy(buyerId);
        return ResponseCustom.build(200, ticket);
    }
}
