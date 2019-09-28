package fontys.sot.rest.service.model;

import java.util.Objects;

public class Ticket {

    // Attributes
    private String ref;
    private int flight_number;
    private int price;
    private String seat;
    private boolean for_sale = true;


    // Constructors
    public Ticket() {}
    public Ticket(String ref, int flight_number, int price, String seat, boolean for_sale) {
        this.ref = ref;
        this.flight_number = flight_number;
        this.price = price;
        this.seat = seat;
        this.for_sale = for_sale;
    }


    // Default Getters and Setters
    public String getRef() { return ref; }
    public void setRef(String ref) { this.ref = ref; }
    public int getFlight_number() { return flight_number; }
    public void setFlight_number(int flight_number) { this.flight_number = flight_number; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getSeat() { return seat; }
    public void setSeat(String seat) { this.seat = seat; }
    public boolean isFor_sale() { return for_sale; }
    public void setFor_sale(boolean for_sale) { this.for_sale = for_sale; }

    // Methods
    public boolean buy(){
        if(for_sale) {
            for_sale = false;
            return true;
        }else{
            return false;
        }
    }


    // Extra methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return flight_number == ticket.flight_number &&
                price == ticket.price &&
                for_sale == ticket.for_sale &&
                ref.equals(ticket.ref) &&
                seat.equals(ticket.seat);
    }
    @Override
    public int hashCode() {
        return Objects.hash(ref, flight_number, price, seat, for_sale);
    }
}
