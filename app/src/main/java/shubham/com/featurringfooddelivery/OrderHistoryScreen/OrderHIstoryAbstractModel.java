package shubham.com.featurringfooddelivery.OrderHistoryScreen;

public class OrderHIstoryAbstractModel {

    private String title;

    private String message;


    public OrderHIstoryAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public OrderHIstoryAbstractModel() {

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
