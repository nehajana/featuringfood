package shubham.com.featurringfooddelivery.AddAdress.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeCategoryDataModelslider;

public class AddAddressModel {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("MainCategoryList")
    @Expose
    private List<HomeCategoryDataModelslider> mainCategoryList = null;

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

    public List<HomeCategoryDataModelslider> getMainCategoryList() {
        return mainCategoryList;
    }

    public void setMainCategoryList(List<HomeCategoryDataModelslider> mainCategoryList) {
        this.mainCategoryList = mainCategoryList;
    }
}
