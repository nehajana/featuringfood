package shubham.com.featurringfooddelivery.HomeFragment.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeModelslider {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("CategoryList")
    @Expose
    private List<HomeDataModelslider> categoryList = null;

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

    public List<HomeDataModelslider> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<HomeDataModelslider> categoryList) {
        this.categoryList = categoryList;
    }
}
