package shubham.com.featurringfooddelivery.AddAdress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AbstractModel_add_address {

    private String title;

    private String message;

    private Boolean isSelected;

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public AbstractModel_add_address(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public AbstractModel_add_address() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
