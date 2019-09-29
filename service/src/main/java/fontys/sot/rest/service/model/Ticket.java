package fontys.sot.rest.service.model;

import java.util.Objects;

public class Ticket {

    // Attributes
    private int ref;
    private int flight_number;
    private int price;
    private String seat;
    private Integer buyer_id = null;
    private Boolean for_sale = true;


    // Constructors
    public Ticket() {}
    public Ticket(int ref, int flight_number, int price, String seat, Integer buyer_id, boolean for_sale) {
        this.ref = ref;
        this.flight_number = flight_number;
        this.price = price;
        this.seat = seat;
        this.buyer_id = buyer_id;
        this.for_sale = for_sale;
    }

    // Default Getters and Setters
    public int getRef() { return ref; }
    public void setRef(int ref) { this.ref = ref; }
    public int getFlight_number() { return flight_number; }
    public void setFlight_number(int flight_number) { this.flight_number = flight_number; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getSeat() { return seat; }
    public void setSeat(String seat) { this.seat = seat; }
    public Integer getBuyer_id() { return buyer_id; }
    public void setBuyer_id(Integer buyer_id) { this.buyer_id = buyer_id; }
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
}
