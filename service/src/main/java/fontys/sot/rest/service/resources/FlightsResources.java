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

    private FlightsCollection flights = new FlightsCollection();

    public FlightsResources() {
        flights.add(new Flight(0, "BCN", "EIN", new GregorianCalendar(2019,9,28,8,20), new GregorianCalendar(2019,9,28,10,20), "Ryanair"));
        flights.add(new Flight(1, "LHR", "CDG", new GregorianCalendar(2019,9,29,10,10), new GregorianCalendar(2019,9,29,12,10), "Transavia"));
        flights.add(new Flight(2, "FRA", "MAD", new GregorianCalendar(2019,9,28,8,25), new GregorianCalendar(2019,9,28,13,10), "Ryanair"));
        flights.add(new Flight(3, "ATL", "BCN", new GregorianCalendar(2019,10,1,21,50), new GregorianCalendar(2019,10,1,22,50), "Vueling"));
        flights.add(new Flight(4, "HND", "PVG", new GregorianCalendar(2019,10,13,17,30), new GregorianCalendar(2019,10,13,18,0), "Vueling"));
        flights.add(new Flight(5, "BCN", "MAD", new GregorianCalendar(2019,10,25,12,15), new GregorianCalendar(2019,10,25,13,30), "Transavia"));
        flights.add(new Flight(6, "EIN", "BCN", new GregorianCalendar(2019,10,19,6,45), new GregorianCalendar(2019,10,19,15,30), "Ryanair"));
    }

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

        String date_format = "yyyy-MM-dd";


        Stream<Flight> filteredFlights = flights.getAll().stream();

        if (flight_number != null) filteredFlights = filteredFlights.filter(flight -> flight.getFlight_number() == flight_number);
        if (price != null)         filteredFlights = filteredFlights.filter(flight -> flight.getPrice().equals(price));
        if (max_price != null)     filteredFlights = filteredFlights.filter(flight -> flight.getPrice() <= max_price);
        if (origin != null)        filteredFlights = filteredFlights.filter(flight -> flight.getOrigin().equals(origin));
        if (destination != null)   filteredFlights = filteredFlights.filter(flight -> flight.getDestination().equals(destination));
        if (airline != null)       filteredFlights = filteredFlights.filter(flight -> flight.getAirline().equals(airline));
        if (departure != null) {
            Date departureDate = getDateFromString(departure, date_format);
            if (departureDate == null) return Response.serverError().entity("Query parameter 'departure' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> flight.getDeparture().equals(departureDate));
        }
        if (arrival != null) {
            Date arrivalDate = getDateFromString(arrival, date_format);
            if (arrivalDate == null) return Response.serverError().entity("Query parameter 'arrival' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> flight.getArrival().equals(arrivalDate));
        }
        if (departure_before != null) {
            Date departure_beforeDate = getDateFromString(departure_before, date_format);
            if (departure_beforeDate == null) return Response.serverError().entity("Query parameter 'departure_before' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> flight.getDeparture().before(departure_beforeDate));
        }
        if (departure_after != null) {
            Date departure_afterDate = getDateFromString(departure_after, date_format);
            if (departure_afterDate == null) return Response.serverError().entity("Query parameter 'departure_after' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> flight.getDeparture().after(departure_afterDate));
        }
        if (arrival_before != null) {
            Date arrival_beforeDate = getDateFromString(arrival_before, date_format);
            if (arrival_beforeDate == null) return Response.serverError().entity("Query parameter 'arrival_before' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> flight.getArrival().before(arrival_beforeDate));
        }
        if (arrival_after != null) {
            Date arrival_afterDate = getDateFromString(arrival_after, date_format);
            if (arrival_afterDate == null) return Response.serverError().entity("Query parameter 'arrival_after' must be in this format: " + date_format).build();
            filteredFlights = filteredFlights.filter(flight -> flight.getArrival().after(arrival_afterDate));
        }

        return Response.ok().entity(filteredFlights.collect(Collectors.toList())).build();
    }

    private Date getDateFromString(String dateString, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format);
            Date date = df.parse(dateString);
            return date;
        } catch (ParseException e) {
            return null;
        }
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
            String date_format = "yyyy-MM-ddThh:mm";

            // Check that departure format is right
            Date departureDate = null;
            if (departure != null) {
                departureDate = getDateFromString(departure, date_format);
                if (departureDate == null) return Response.serverError().entity("Query parameter 'departure' must be in this format: " + date_format).build();
            }
            // Check that arrival format is right
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

    @GET
    @Path("total")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getTotalFlights() {
        return Response.ok().entity(flights.total()).build();
    }
}
