package shubham.com.featurringfooddelivery.OrderBooking.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardModelList {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("getCartList")
    @Expose
    private List<GetCartListDataModel> getCartList = null;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("FinalAmount")
    @Expose
    private String finalAmount;

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

    public List<GetCartListDataModel> getGetCartList() {
        return getCartList;
    }

    public void setGetCartList(List<GetCartListDataModel> getCartList) {
        this.getCartList = getCartList;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        this.finalAmount = finalAmount;
    }
}