package shubham.com.featurringfooddelivery.HomeFragment;

import java.util.ArrayList;

public class HomeAbstractModel {

    private String title;
    private int img;


    public HomeAbstractModel(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public HomeAbstractModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int message) {
        this.img = message;
    }
}
