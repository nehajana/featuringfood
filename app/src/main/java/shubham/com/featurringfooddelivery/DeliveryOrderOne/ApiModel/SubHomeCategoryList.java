package shubham.com.featurringfooddelivery.DeliveryOrderOne.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubHomeCategoryList {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("CategoryList")
    @Expose
    private SubHomeDataCategory categoryList;
    @SerializedName("Ingredients")
    @Expose
    private List<SubHomeModerImage> ingredients = null;

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

    public SubHomeDataCategory getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(SubHomeDataCategory categoryList) {
        this.categoryList = categoryList;
    }

    public List<SubHomeModerImage> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<SubHomeModerImage> ingredients) {
        this.ingredients = ingredients;
    }
}
