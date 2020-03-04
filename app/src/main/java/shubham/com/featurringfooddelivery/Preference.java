package shubham.com.featurringfooddelivery;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    public static final String APP_PREF = "KapsiePreferences";

    public static SharedPreferences sp;
    public static String KEY_USER_ID = "id";
    public static String GET_VIMEO = "/config";
    public static String KEY_Category_ID = "category_id";
    public static String KEY_SubCategory_ID = "subcategory_id";
    public static String KEY_ITEM_ID = "itemid";
    public static String KEY_USER_Video_ID = "Video_id";
    public static String KEY_Name= "name";
    public static String KEY_OrderiD= "name";
    public static String KEY_amount = "amount";
    public static String KEY_Address_api= "name";
    public static String KEY_isKeepMe = "isKeepMe";
    public static String KEY_Email = "email";
    public static String KEY_Address = "pic";
    public static String KEY_Address_Id = "pic1";
    public static String KEY_CategoryId = "category_id";

    public static String KEY_Main_CategoryId = "main_category_id";


    public static String KEY_Ordertype= "Ordertype";
    public static String KEY_OrderDay= "OrderDay";
    public static String KEY_OrderTime= "OrderTime";
    public static String KEY_ZipCode = "add";
    public static String key_FILEPATH = "filepath";
    public static String key_IMMAGEURL = "IMAGurL";
    public static String key_Video_URl = "Video_str";
    public static String key_Checked = "check";
    public static String key_Image_path = "check";
    public static String key_PlaceUser_name = "name_place";
    public static String key_PlaceUser_email = "email_place";
    public static String key_PlaceUser_address = "address_place";

    public static String key_Title = "key_Title";
    public static String key_Description = "description";
    public static String key_price = "price";
    public static String key_Currency = "Currency1";
    public static String key_isFromMyvideolist = "isFromMyvideolist";
    public static String key_Category = "Category";
    public static String Key_shipping_charge = "shipping";
    public static String key_stock = "stock";
    public static String ADD_VIEW_COUNT = "addViewCount";

    //------------- Payment gateway-----------------------
    public static String TYPE = "type";
    //public static String PAY_FOR_CHANNEL = "http://ixorainfotech.in/apicollection/featuring/Webservice/letsPayForChannel";
    public static String PAY_FOR_CHANNEL = "http://ixorainfotech.in/apicollection/featurringfood/Webservice/letsPayForproducts";




    //-----------------------------------




    public static String get(Context context , String key) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        String userId = sp.getString(key, "0");
        return userId;
    }
    public static void save(Context context, String key, String value) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static void saveInt(Context context, String key, int value) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }
    public static void saveFloat(Context context, String key, Float value) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putFloat(key, value);
        edit.commit();
    }

    public static int getInt(Context context , String key) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        int userId = sp.getInt(key,0);
        return userId;
    }

    public static Float getFloat(Context context , String key) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        Float userId = sp.getFloat(key,0);
        return userId;
    }


    public static boolean saveBool(Context context, String key, Boolean value) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();
        return false;
    }

    public static Boolean getBool(Context context , String key) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        return sp.getBoolean(key,false);
    }

    public static void clearPreference(Context context) {
        sp = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.apply();
    }
}
