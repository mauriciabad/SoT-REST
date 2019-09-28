package fontys.sot.rest.service.resources;

import fontys.sot.rest.service.model.FlightsCollection;
import fontys.sot.rest.service.model.Flight;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
    public List<Flight> getFlightByQuery(@QueryParam("flight_number") Integer flight_number, @QueryParam("origin") String origin, @QueryParam("destination") String destination, @QueryParam("departure") Date departure, @QueryParam("arrival") Date arrival, @QueryParam("airline") String airline) {
        return flights.filter(flight_number, origin, destination, departure, arrival, airline);
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
    public Response createFlight(@FormParam("flight_number") Integer flight_number, @FormParam("origin") String origin, @FormParam("destination") String destination, @FormParam("departure") Date departure, @FormParam("arrival") Date arrival, @FormParam("airline") String airline) {
        if(!flights.exists(flight_number)){
            flights.add(new Flight(flight_number, origin, destination, departure, arrival, airline));
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
