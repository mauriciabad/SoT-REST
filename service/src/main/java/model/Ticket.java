package model;

public class Ticket {

    // Attributes
    private int id;
    private int price;
    private String seat;
    private Integer buyerId = null;
    private Boolean forSale = true;


    // Constructors
    public Ticket() {}
    public Ticket(int id, int price, String seat, Integer buyerId, boolean forSale) {
        this.id = id;
        this.price = price;
        this.seat = seat;
        this.buyerId = buyerId;
        this.forSale = forSale;
    }
    public Ticket(int id, int price, String seat) {
        this.id = id;
        this.price = price;
        this.seat = seat;
    }

    // Default Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
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
