
package shubham.com.featurringfooddelivery.MainHomeFragment;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainHomeCategoryModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("CategoryList")
    @Expose
    private ArrayList<CategoryList> categoryList = null;

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

    public ArrayList<CategoryList> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<CategoryList> categoryList) {
        this.categoryList = categoryList;
    }

}
