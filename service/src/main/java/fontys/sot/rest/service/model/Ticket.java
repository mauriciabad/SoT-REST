package fontys.sot.rest.service.model;

import java.util.Objects;

public class Ticket {

    // Attributes
    private int ref;
    private int flightNumber;
    private int price;
    private String seat;
    private Integer buyerId = null;
    private Boolean forSale = true;


    // Constructors
    public Ticket() {}
    public Ticket(int ref, int flightNumber, int price, String seat, Integer buyerId, boolean forSale) {
        this.ref = ref;
        this.flightNumber = flightNumber;
        this.price = price;
        this.seat = seat;
        this.buyerId = buyerId;
        this.forSale = forSale;
    }
    public Ticket(int ref, int flightNumber, int price, String seat) {
        this.ref = ref;
        this.flightNumber = flightNumber;
        this.price = price;
        this.seat = seat;
    }

    // Default Getters and Setters
    public int getRef() { return ref; }
    public void setRef(int ref) { this.ref = ref; }
    public int getFlightNumber() { return flightNumber; }
    public void setFlightNumber(int flightNumber) { this.flightNumber = flightNumber; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getSeat() { return seat; }
    public void setSeat(String seat) { this.seat = seat; }
    public Integer getBuyerId() { return buyerId; }
    public void setBuyerId(Integer buyerId) { this.buyerId = buyerId; }
    public boolean isForSale() { return forSale; }
    public void setForSale(boolean forSale) { this.forSale = forSale; }

    // Methods
    public boolean buy(int buyerId){
        if(forSale) {
            forSale = false;
            this.buyerId = buyerId;
            return true;
        }else{
            return false;
        }
    }
}
