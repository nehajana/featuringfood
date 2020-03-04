
package shubham.com.featurringfooddelivery.YoutubeVideoPlay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeNewResult {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("icon_thumb")
    @Expose
    private String iconThumb;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("language_id")
    @Expose
    private String languageId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("isPaid")
    @Expose
    private String isPaid;
    @SerializedName("paidAmt")
    @Expose
    private String paidAmt;
    @SerializedName("isFavourite")
    @Expose
    private Integer isFavourite;
    @SerializedName("advertismentURL")
    @Expose
    private List<AdvertismentURL> advertismentURL = null;
    @SerializedName("isPurchasedByUser")
    @Expose
    private Integer isPurchasedByUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIconThumb() {
        return iconThumb;
    }

    public void setIconThumb(String iconThumb) {
        this.iconThumb = iconThumb;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public String getPaidAmt() {
        return paidAmt;
    }

    public void setPaidAmt(String paidAmt) {
        this.paidAmt = paidAmt;
    }

    public Integer getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Integer isFavourite) {
        this.isFavourite = isFavourite;
    }

    public List<AdvertismentURL> getAdvertismentURL() {
        return advertismentURL;
    }

    public void setAdvertismentURL(List<AdvertismentURL> advertismentURL) {
        this.advertismentURL = advertismentURL;
    }

    public Integer getIsPurchasedByUser() {
        return isPurchasedByUser;
    }

    public void setIsPurchasedByUser(Integer isPurchasedByUser) {
        this.isPurchasedByUser = isPurchasedByUser;
    }

}
