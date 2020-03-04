package shubham.com.featurringfooddelivery.OrderBooking.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TimeModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("DaystList")
    @Expose
    private List<DaystList> daystList = null;
    @SerializedName("TimePeriod")
    @Expose
    private List<TimePeriedModel> timePeriod = null;

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

    public List<DaystList> getDaystList() {
        return daystList;
    }

    public void setDaystList(List<DaystList> daystList) {
        this.daystList = daystList;
    }

    public List<TimePeriedModel> getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(List<TimePeriedModel> timePeriod) {
        this.timePeriod = timePeriod;
    }

}