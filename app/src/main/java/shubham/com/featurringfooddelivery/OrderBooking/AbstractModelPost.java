package shubham.com.featurringfooddelivery.OrderBooking;

public class AbstractModelPost {

    private String Name;
    private String Price;
    private String quantity;

    public String getName() {
        return Name;
    }

    public AbstractModelPost(String name, String price, String quantity) {
        Name = name;
        Price = price;
        this.quantity = quantity;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
