package shubham.com.featurringfooddelivery.MainHomeFragment;

public class US_State {

   String Name;
    String Abbreviations;

    public String getName() {
        return Name;
    }

    public US_State( String abbreviations,String name) {
        Name = name;
        Abbreviations = abbreviations;
    }

    public void setName(String name) {
        Name = name;

    }

    public String getAbbreviations() {
        return Abbreviations;
    }

    public void setAbbreviations(String abbreviations) {
        Abbreviations = abbreviations;
    }
}



