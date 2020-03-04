package shubham.com.featurringfooddelivery.AddAdress.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewAddressModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Address")
    @Expose
    private List<NewAddressDataModel> address = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<NewAddressDataModel> getAddress() {
        return address;
    }

    public void setAddress(List<NewAddressDataModel> address) {
        this.address = address;
    }

}
