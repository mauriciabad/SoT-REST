package fontys.sot.rest.service.model;

import java.util.ArrayList;
import java.util.List;

public class AllTickets {
    private List<Ticket> tickets = new ArrayList<>();

    public List<Ticket> getAll() {
        return tickets;
    }

    public int total() {
        return tickets.size();
    }

    public Ticket get(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public boolean exists(int id){
        return this.get(id) != null;
    }

    public void remove(int id){
        tickets.remove(this.get(id));
    }

    public void add(Ticket ticket) {
        Ticket oldTicket = this.get(ticket.getId());
        if (oldTicket != null){
            oldTicket.setOrigin(ticket.getOrigin());
            oldTicket.setDestination(ticket.getDestination());
        }else{
            tickets.add(ticket);
        }
    }
}
