package shubham.com.featurringfooddelivery.HomeFragment.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeCategoryDataModelslider {
    @SerializedName("ItemId")
    @Expose
    private String ItemId;

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    @SerializedName("Categories_id")
    @Expose
    private String categoriesId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("deliveryTitle")
    @Expose
    private String deliveryTitle;
    @SerializedName("description")
    @Expose
    private String description;

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeliveryTitle() {
        return deliveryTitle;
    }

    public void setDeliveryTitle(String deliveryTitle) {
        this.deliveryTitle = deliveryTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}