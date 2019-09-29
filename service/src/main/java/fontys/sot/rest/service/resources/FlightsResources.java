package fontys.sot.rest.service.resources;

import fontys.sot.rest.service.model.Flight;
import fontys.sot.rest.service.model.FlightsCollection;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
            @QueryParam("flight_number") Integer flight_number,
            @QueryParam("origin") String origin,
            @QueryParam("destination") String destination,
            @QueryParam("departure") String departure,
            @QueryParam("departure_before") String departure_before,
            @QueryParam("departure_after") String departure_after,
            @QueryParam("arrival") String arrival,
            @QueryParam("arrival_before") String arrival_before,
            @QueryParam("arrival_after") String arrival_after,
            @QueryParam("price") Integer price,
            @QueryParam("max_price") Integer max_price,
            @QueryParam("airline") String airline) {
        Stream<Flight> filteredFlights = flights.getAll().stream();

        if (flight_number != null) filteredFlights = filteredFlights.filter(flight -> flight_number == flight.getFlight_number());
        if (origin != null)        filteredFlights = filteredFlights.filter(flight -> origin.equals(flight.getOrigin()));
        if (destination != null)   filteredFlights = filteredFlights.filter(flight -> destination.equals(flight.getDestination()));
        if (airline != null)       filteredFlights = filteredFlights.filter(flight -> airline.equals(flight.getAirline()));
        if (price != null)         filteredFlights = filteredFlights.filter(flight -> price.equals(flight.getPrice()));

        if (max_price != null) {
            filteredFlights = filteredFlights.filter(flight -> {
                Integer flight_price = flight.getPrice();
                return flight_price != null && flight_price <= max_price;
            });
        }

        if (departure != null) {
            if (!Flight.isValidDate(departure)) return Response.status(422).entity("Parameter 'departure' must be in this format: YYYY-MM-dd hh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(departure).equals(Flight.getDateFromString(flight.getDeparture())));
        }
        if (arrival != null) {
            if (!Flight.isValidDate(arrival)) return Response.status(422).entity("Parameter 'arrival' must be in this format: YYYY-MM-dd hh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(arrival).equals(Flight.getDateFromString(flight.getArrival())));
        }
        if (departure_before != null) {
            if (!Flight.isValidDate(departure_before)) return Response.status(422).entity("Parameter 'departure_before' must be in this format: YYYY-MM-dd hh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(departure_before).after(Flight.getDateFromString(flight.getDeparture())));
        }
        if (departure_after != null) {
            if (!Flight.isValidDate(departure_after)) return Response.status(422).entity("Parameter 'departure_after' must be in this format: YYYY-MM-dd hh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(departure_after).before(Flight.getDateFromString(flight.getDeparture())));
        }
        if (arrival_before != null) {
            if (!Flight.isValidDate(arrival_before)) return Response.status(422).entity("Parameter 'arrival_before' must be in this format: YYYY-MM-dd hh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(arrival_before).after(Flight.getDateFromString(flight.getArrival())));
        }
        if (arrival_after != null) {
            if (!Flight.isValidDate(arrival_after)) return Response.status(422).entity("Parameter 'arrival_after' must be in this format: YYYY-MM-dd hh:mm").build();
            filteredFlights = filteredFlights.filter(flight -> Flight.getDateFromString(arrival_after).before(Flight.getDateFromString(flight.getArrival())));
        }

        return Response.ok().entity(filteredFlights.collect(Collectors.toList())).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createFlight(Flight flight) {
        if (flight.getOrigin() == null) return Response.status(422).entity("Missing 'origin' parameter").build();
        if (flight.getDestination() == null) return Response.status(422).entity("Missing 'destination' parameter").build();
        if (flight.getDeparture() == null) return Response.status(422).entity("Missing 'departure' parameter").build();
        if (flight.getArrival() == null) return Response.status(422).entity("Missing 'arrival' parameter").build();
        if (flight.getAirline() == null) return Response.status(422).entity("Missing 'airline' parameter").build();
        if (flight.getDeparture() != null && !Flight.isValidDate(flight.getDeparture())) return Response.status(422).entity("Parameter 'departure' must be in this format: YYYY-MM-dd hh:mm").build();
        if (flight.getArrival() != null && !Flight.isValidDate(flight.getArrival())) return Response.status(422).entity("Parameter 'arrival' must be in this format: YYYY-MM-dd hh:mm").build();

        int flight_number = flights.add(flight);

        return Response.ok().entity(flights.get(flight_number)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response createFlight(@FormParam("origin") String origin, @FormParam("destination") String destination, @FormParam("departure") String departure, @FormParam("arrival") String arrival, @FormParam("airline") String airline) {
        if (origin == null) return Response.status(422).entity("Missing 'origin' parameter").build();
        if (destination == null) return Response.status(422).entity("Missing 'destination' parameter").build();
        if (departure == null) return Response.status(422).entity("Missing 'departure' parameter").build();
        if (arrival == null) return Response.status(422).entity("Missing 'arrival' parameter").build();
        if (airline == null) return Response.status(422).entity("Missing 'airline' parameter").build();
        if (!Flight.isValidDate(departure)) return Response.status(422).entity("Parameter 'departure' must be in this format: YYYY-MM-dd hh:mm").build();
        if (!Flight.isValidDate(arrival)) return Response.status(422).entity("Parameter 'arrival' must be in this format: YYYY-MM-dd hh:mm").build();

        int flight_number = flights.add(new Flight(origin, destination, departure, arrival, airline));

        return Response.ok().entity(flights.get(flight_number)).build();
    }

    @PUT
    @Path("{flight_number}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateFlight(Flight flight, @PathParam("flight_number") int flight_number) {
        if (flights.exists(flight_number)){
            if (flight.getDeparture() != null && !Flight.isValidDate(flight.getDeparture())) return Response.status(422).entity("Parameter 'departure' must be in this format: YYYY-MM-dd hh:mm").build();
            if (flight.getArrival() != null && !Flight.isValidDate(flight.getArrival())) return Response.status(422).entity("Parameter 'arrival' must be in this format: YYYY-MM-dd hh:mm").build();

            Flight oldFlight = flights.get(flight_number);

            if (flight.getOrigin() != null) oldFlight.setOrigin(flight.getOrigin());
            if (flight.getDestination() != null) oldFlight.setDestination(flight.getDestination());
            if (flight.getDeparture() != null) oldFlight.setDeparture(flight.getDeparture());
            if (flight.getArrival() != null) oldFlight.setArrival(flight.getArrival());
            if (flight.getAirline() != null) oldFlight.setAirline(flight.getAirline());

            flights.update(oldFlight);

            return Response.ok().entity(flights.get(flight_number)).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity("Flight with flight_number " + flight_number + " doesn't exist").build();
        }
    }

    @GET
    @Path("{flight_number}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFlightById(@PathParam("flight_number") int flight_number) {
        if (flights.exists(flight_number)) {
            return Response.ok().entity(flights.get(flight_number)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Flight with flight_number " + flight_number + " doesn't exist").build();
        }
    }

    @DELETE
    @Path("{flight_number}")
    public Response deleteFlight(@PathParam("flight_number") int flight_number) {
        flights.remove(flight_number);
        return Response.noContent().build();
    }
}
