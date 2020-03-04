package shubham.com.featurringfooddelivery.TrackOrderFragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("OrderStatus")
    @Expose
    private StatusDataModel orderStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusDataModel getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(StatusDataModel orderStatus) {
        this.orderStatus = orderStatus;
    }

}