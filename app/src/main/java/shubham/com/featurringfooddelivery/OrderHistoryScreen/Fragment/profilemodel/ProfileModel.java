package shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment.profilemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("profile")
    @Expose
    private ProfileDatamodel profile;

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

    public ProfileDatamodel getProfile() {
        return profile;
    }

    public void setProfile(ProfileDatamodel profile) {
        this.profile = profile;
    }
}