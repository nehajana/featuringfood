package shubham.com.featurringfooddelivery.PaymentDelever;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment.profilemodel.ProfileDatamodel;

public class GetAddressModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("GetSingleAddress")
    @Expose
    private GetDataAddressModel getSingleAddress;

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

    public GetDataAddressModel getGetSingleAddress() {
        return getSingleAddress;
    }

    public void setGetSingleAddress(GetDataAddressModel getSingleAddress) {
        this.getSingleAddress = getSingleAddress;
    }



}