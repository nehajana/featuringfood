package shubham.com.featurringfooddelivery.HomeFragment.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLikeContModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("LikeCount")
    @Expose
    private GetLikeDataModel likeCount;

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

    public GetLikeDataModel getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(GetLikeDataModel likeCount) {
        this.likeCount = likeCount;
    }


}
