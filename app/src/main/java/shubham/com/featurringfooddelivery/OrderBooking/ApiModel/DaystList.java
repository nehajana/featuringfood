package shubham.com.featurringfooddelivery.OrderBooking.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DaystList {
    @SerializedName("dayid")
    @Expose
    private String dayid;
    @SerializedName("dayName")
    @Expose
    private String dayName;

    public String getDayid() {
        return dayid;
    }

    public void setDayid(String dayid) {
        this.dayid = dayid;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
}
