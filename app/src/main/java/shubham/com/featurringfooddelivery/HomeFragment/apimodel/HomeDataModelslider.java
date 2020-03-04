package shubham.com.featurringfooddelivery.HomeFragment.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeDataModelslider {


    @SerializedName("SubCategoryId")
    @Expose
    private String SubCategoryId;

    public String getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        SubCategoryId = subCategoryId;
    }

    @SerializedName("ItemId")
    @Expose

    private String itemId;
    @SerializedName("Categories_id")
    @Expose
    private String categoriesId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("SubCategoryName")
    @Expose
    private String subCategoryName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("videourl")
    @Expose
    private String videourl;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("deliveryTitle")
    @Expose
    private String deliveryTitle;
    @SerializedName("reviews")
    @Expose
    private String reviews;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_vimeo")
    @Expose
    private String isVimeo;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

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

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeliveryTitle() {
        return deliveryTitle;
    }

    public void setDeliveryTitle(String deliveryTitle) {
        this.deliveryTitle = deliveryTitle;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsVimeo() {
        return isVimeo;
    }

    public void setIsVimeo(String isVimeo) {
        this.isVimeo = isVimeo;
    }
}