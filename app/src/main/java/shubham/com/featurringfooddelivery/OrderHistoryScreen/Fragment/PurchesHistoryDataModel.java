package shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchesHistoryDataModel {

    @SerializedName("item_number")
    @Expose
    private String itemNumber;
    @SerializedName("item_price")
    @Expose
    private String itemPrice;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("address")
    @Expose
    private String address;

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}