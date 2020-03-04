package shubham.com.featurringfooddelivery.HomeFragment.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLikeCountByIdModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("IsLiked")
    @Expose
    private GetLikeCountByIdDataModel isLiked;

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

    public GetLikeCountByIdDataModel getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(GetLikeCountByIdDataModel isLiked) {
        this.isLiked = isLiked;
    }


}
