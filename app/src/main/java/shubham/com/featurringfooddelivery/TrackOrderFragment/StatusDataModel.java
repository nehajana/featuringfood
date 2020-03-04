package shubham.com.featurringfooddelivery.TrackOrderFragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusDataModel {
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("address")
    @Expose
    private String address;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}