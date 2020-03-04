package shubham.com.featurringfooddelivery.DeliveryOrderOne.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCardModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Quantity")
    @Expose
    private String quantity;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
