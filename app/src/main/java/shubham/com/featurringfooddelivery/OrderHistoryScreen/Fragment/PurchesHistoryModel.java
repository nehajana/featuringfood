package shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PurchesHistoryModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("PurchaseHistory")
    @Expose
    private List<PurchesHistoryDataModel> purchaseHistory = null;

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

    public List<PurchesHistoryDataModel> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(List<PurchesHistoryDataModel> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }
}
