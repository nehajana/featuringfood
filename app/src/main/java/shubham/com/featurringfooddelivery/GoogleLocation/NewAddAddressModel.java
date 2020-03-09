package shubham.com.featurringfooddelivery.GoogleLocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeCategoryDataModelslider;

public class NewAddAddressModel {
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Address_Id")
    @Expose
    private String addressId;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
