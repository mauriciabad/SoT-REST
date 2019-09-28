package fontys.sot.rest.service.resources;

import fontys.sot.rest.service.model.Flight;
import fontys.sot.rest.service.model.FlightsCollection;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
        if (price != null)         filteredFlights = filteredFlights.filter(flight -> price.equals(flight.getPrice()));
        if (max_price != null)     filteredFlights = filteredFlights.filter(flight -> flight.getPrice() <= max_price);
        if (origin != null)        filteredFlights = filteredFlights.filter(flight -> origin.equals(flight.getOrigin()));
        if (destination != null)   filteredFlights = filteredFlights.filter(flight -> destination.equals(flight.getDestination()));
        if (airline != null)       filteredFlights = filteredFlights.filter(flight -> airline.equals(flight.getAirline()));

        String date_format = "yyyy-MM-dd";
        if (departure != null) {
            Date departureDate = getDateFromString(departure, date_format);
            if (departureDate == null) return Response.serverError().entity("Query parameter 'departure' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> departureDate.equals(flight.getDeparture()));
        }
        if (arrival != null) {
            Date arrivalDate = getDateFromString(arrival, date_format);
            if (arrivalDate == null) return Response.serverError().entity("Query parameter 'arrival' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> arrivalDate.equals(flight.getArrival()));
        }
        if (departure_before != null) {
            Date departure_beforeDate = getDateFromString(departure_before, date_format);
            if (departure_beforeDate == null) return Response.serverError().entity("Query parameter 'departure_before' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> departure_beforeDate.before(flight.getDeparture()));
        }
        if (departure_after != null) {
            Date departure_afterDate = getDateFromString(departure_after, date_format);
            if (departure_afterDate == null) return Response.serverError().entity("Query parameter 'departure_after' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> departure_afterDate.after(flight.getDeparture()));
        }
        if (arrival_before != null) {
            Date arrival_beforeDate = getDateFromString(arrival_before, date_format);
            if (arrival_beforeDate == null) return Response.serverError().entity("Query parameter 'arrival_before' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> arrival_beforeDate.before(flight.getArrival()));
        }
        if (arrival_after != null) {
            Date arrival_afterDate = getDateFromString(arrival_after, date_format);
            if (arrival_afterDate == null) return Response.serverError().entity("Query parameter 'arrival_after' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> arrival_afterDate.after(flight.getArrival()));
        }

        return Response.ok().entity(filteredFlights.collect(Collectors.toList())).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createFlight(Flight flight) {
        if(!flights.exists(flight.getFlight_number())){

            flights.add(flight);

            return Response.noContent().build();
        }else{
            return Response.serverError().entity("Flight with flight_number " + flight.getFlight_number() + " already exist").build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response createFlight(@FormParam("flight_number") Integer flight_number, @FormParam("origin") String origin, @FormParam("destination") String destination, @FormParam("departure") String departure, @FormParam("arrival") String arrival, @FormParam("airline") String airline) {
        if(!flights.exists(flight_number)){

            // Validate dates' format
            String date_format = "yyyy-MM-ddThh:mm";
            Date departureDate = null;
            if (departure != null) {
                departureDate = getDateFromString(departure, date_format);
                if (departureDate == null) return Response.serverError().entity("Query parameter 'departure' must be in this format: " + date_format).build();
            }
            Date arrivalDate = null;
            if (arrival != null) {
                arrivalDate = getDateFromString(arrival, date_format);
                if (arrivalDate == null) return Response.serverError().entity("Query parameter 'arrival' must be in this format: " + date_format).build();
            }

            flights.add(new Flight(flight_number, origin, destination, departureDate, arrivalDate, airline));

            return Response.noContent().build();
        }else{
            return Response.serverError().entity("Flight with flight_number " + flight_number + " already exist").build();
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateFlight(Flight flight) {
        if(flights.exists(flight.getFlight_number())){
            flights.add(flight);
            return Response.noContent().build();
        }else{
            return Response.serverError().entity("Flight with flight_number " + flight.getFlight_number() + " doesn't exist").build();
        }
    }

    @GET
    @Path("{flight_number}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFlightById(@PathParam("flight_number") int flight_number) {
        if (flights.exists(flight_number)) {
            return Response.ok().entity(flights.get(flight_number)).build();
        } else {
            return Response.serverError().entity("Flight with flight_number " + flight_number + " doesn't exist").build();
        }
    }

    @DELETE
    @Path("{flight_number}")
    public Response deleteFlight(@PathParam("flight_number") int flight_number) {
        flights.remove(flight_number);
        return Response.noContent().build();
    }



    /*  - - - - - - - - - -  Helper methods  - - - - - - - - - -  */

    private Date getDateFromString(String dateString, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format);
            Date date = df.parse(dateString);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

}
