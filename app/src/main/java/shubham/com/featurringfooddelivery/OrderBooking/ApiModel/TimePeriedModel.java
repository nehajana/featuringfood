package shubham.com.featurringfooddelivery.OrderBooking.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TimePeriedModel {


    @SerializedName("TimeId")
    @Expose
    private String timeId;
    @SerializedName("timePeriod")
    @Expose
    private String timePeriod;

    Boolean isChecked = false;

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

}