
package shubham.com.featurringfooddelivery.YoutubeVideoPlay.casty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewcountModel {

    @SerializedName("view_count")
    @Expose
    private Integer viewCount;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
