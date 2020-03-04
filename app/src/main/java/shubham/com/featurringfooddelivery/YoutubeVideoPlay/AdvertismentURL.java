
package shubham.com.featurringfooddelivery.YoutubeVideoPlay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdvertismentURL {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("advertisement_name")
    @Expose
    private String advertisementName;
    @SerializedName("vimeo_url")
    @Expose
    private String vimeoUrl;
    @SerializedName("redirection_url")
    @Expose
    private String redirectionUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdvertisementName() {
        return advertisementName;
    }

    public void setAdvertisementName(String advertisementName) {
        this.advertisementName = advertisementName;
    }

    public String getVimeoUrl() {
        return vimeoUrl;
    }

    public void setVimeoUrl(String vimeoUrl) {
        this.vimeoUrl = vimeoUrl;
    }

    public String getRedirectionUrl() {
        return redirectionUrl;
    }

    public void setRedirectionUrl(String redirectionUrl) {
        this.redirectionUrl = redirectionUrl;
    }

}
