package shubham.com.featurringfooddelivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryNamemodel
{
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("countryList")
    @Expose
    private List<CountryDatamodel> countryList = null;

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

    public List<CountryDatamodel> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryDatamodel> countryList) {
        this.countryList = countryList;
    }
}