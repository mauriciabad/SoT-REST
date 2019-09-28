package fontys.sot.rest.service.model;

import java.util.Objects;

public class Ticket {
    private int id;
    private String origin;
    private String destination;

    public Ticket() {
    }

    public Ticket(int id, String origin, String destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                origin.equals(ticket.origin) &&
                destination.equals(ticket.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origin, destination);
    }
}
