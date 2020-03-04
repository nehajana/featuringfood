
package shubham.com.featurringfooddelivery.MainHomeFragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryList {

    @SerializedName("home_id")
    @Expose
    private String homeId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_video")
    @Expose
    private String categoryVideo;

    @SerializedName("product_image")
    @Expose
    private String product_image;

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryVideo() {
        return categoryVideo;
    }

    public void setCategoryVideo(String categoryVideo) {
        this.categoryVideo = categoryVideo;
    }

}
