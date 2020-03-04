package shubham.com.featurringfooddelivery.OrderBooking.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCartListDataModel {

    @SerializedName("Categories_id")
    @Expose
    private String categoriesId;
    @SerializedName("ingredients_id")
    @Expose
    private String ingredientsId;
    @SerializedName("ingredients_price")
    @Expose
    private String ingredientsPrice;
    @SerializedName("Quantity")
    @Expose
    private String quantity;
    @SerializedName("ingredientsQuantity")
    @Expose
    private String ingredientsQuantity;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("ingredientsName")
    @Expose
    private String ingredientsName;
    @SerializedName("price")
    @Expose
    private String price;

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(String ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public String getIngredientsPrice() {
        return ingredientsPrice;
    }

    public void setIngredientsPrice(String ingredientsPrice) {
        this.ingredientsPrice = ingredientsPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIngredientsQuantity() {
        return ingredientsQuantity;
    }

    public void setIngredientsQuantity(String ingredientsQuantity) {
        this.ingredientsQuantity = ingredientsQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIngredientsName() {
        return ingredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        this.ingredientsName = ingredientsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
