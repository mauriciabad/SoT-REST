package fontys.sot.rest.service.resources;

import fontys.sot.rest.service.model.AllTickets;
import fontys.sot.rest.service.model.Ticket;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("tickets")
public class TicketsResources {

    private AllTickets tickets = new AllTickets();

    public TicketsResources() {
        tickets.add(new Ticket(0, "BCN", "EIN"));
        tickets.add(new Ticket(1, "LHR", "CDG"));
        tickets.add(new Ticket(2, "FRA", "MAD"));
        tickets.add(new Ticket(3, "ATL", "BCN"));
        tickets.add(new Ticket(4, "HND", "PVG"));
        tickets.add(new Ticket(5, "BCN", "MAD"));
        tickets.add(new Ticket(6, "EIN", "BCN"));
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Ticket> getTicketByQuery(@QueryParam("id") Integer id, @QueryParam("origin") String origin, @QueryParam("destination") String destination) {
        List<Ticket> filteredTicketList = tickets.getAll();

        // Remove tickets with different id
        if (id != null){
            for (Ticket ticket: filteredTicketList) {
                if (ticket.getId() != id) filteredTicketList.remove(ticket);
            }
        }

        // Remove tickets with different origin
        if (origin != null){
            for (Ticket ticket: filteredTicketList) {
                if (!ticket.getOrigin().equals(origin)) filteredTicketList.remove(ticket);
            }
        }

        // Remove tickets with different destination
        if (destination != null){
            for (Ticket ticket: filteredTicketList) {
                if (!ticket.getDestination().equals(destination)) filteredTicketList.remove(ticket);
            }
        }

        return filteredTicketList;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void createTicket(Ticket ticket) {
        if(!tickets.exists(ticket.getId())){
            tickets.add(ticket);
        }else{
            throw new RuntimeException("Ticket with id " + ticket.getId() + " already exist");
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void createTicket(@FormParam("id") int id, @FormParam("origin") String origin, @FormParam("destination") String destination) {
        if(!tickets.exists(id)){
            tickets.add(new Ticket(id, origin, destination));
        }else{
            throw new RuntimeException("Ticket with id " + id + " already exist");
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateTicket(Ticket ticket) {
        if(tickets.exists(ticket.getId())){
            tickets.add(ticket);
            return Response.noContent().build();
        }else{
            return Response.serverError().entity("Ticket with id " + ticket.getId() + " doesn't exist").build();
        }
    }

    @GET
    @Path("{ticket_id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getTicketByQuery(@PathParam("ticket_id") int id) {
        if (tickets.exists(id)) {
            return Response.ok().entity(tickets.get(id)).build();
        } else {
            return Response.serverError().entity("Ticket with id " + id + " doesn't exist").build();
        }
    }

    @DELETE
    @Path("{ticket_id}")
    public void deleteTicket(@PathParam("ticket_id") int id) {
         tickets.remove(id);
    }


}
