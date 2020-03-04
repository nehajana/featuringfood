package shubham.com.featurringfooddelivery.PaymentDelever;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDataAddressModel {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("AddressDetails")
    @Expose
    private String addressDetails;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("apartment")
    @Expose
    private String apartment;
    @SerializedName("state")
    @Expose
    private String state;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}