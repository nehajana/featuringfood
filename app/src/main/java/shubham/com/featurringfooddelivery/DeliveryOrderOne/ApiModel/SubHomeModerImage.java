package shubham.com.featurringfooddelivery.DeliveryOrderOne.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubHomeModerImage {

    @SerializedName("ingredients_id")
    @Expose
    private String ingredientsId;
    @SerializedName("ingredientsName")
    @Expose
    private String ingredientsName;
    @SerializedName("ingredients_price")
    @Expose
    private String ingredientsPrice;

    private Boolean isChecked = false;

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(String ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public String getIngredientsName() {
        return ingredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        this.ingredientsName = ingredientsName;
    }

    public String getIngredientsPrice() {
        return ingredientsPrice;
    }

    public void setIngredientsPrice(String ingredientsPrice) {
        this.ingredientsPrice = ingredientsPrice;
    }
}
