package shubham.com.featurringfooddelivery.TrackOrderFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.featurringfooddelivery.LoginModel;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class StatusFragment extends Fragment implements IApiResponse {

    View rooview;
    TextView txt_address;
   ImageView img_prepared;
    ImageView img_out_delivery;
    ImageView img_delivery;

    public StatusFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rooview = inflater.inflate( R.layout.activity_track_order, container, false);

        txt_address=(TextView) rooview.findViewById(R.id.txt_address);
        img_prepared=(ImageView) rooview.findViewById(R.id.img_prepared);
        img_out_delivery=(ImageView) rooview.findViewById(R.id.img_out_delivery);
        img_delivery=(ImageView) rooview.findViewById(R.id.img_delivery);

        TrackOrderMethod();

        return rooview;

    }


    public void TrackOrderMethod(){

        String OrderID=Preference.get(getActivity(),Preference.KEY_OrderiD);

        String User_id= Preference.get(getActivity(),Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",User_id);
       // map.put("order_number","EB15795984934");
        map.put("order_number",OrderID);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.User_trackOrderStatus, Constants.User_trackOrderStatus,map, Request.Method.POST);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.User_trackOrderStatus.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                StatusModel finalArray = new Gson().fromJson(response,new TypeToken<StatusModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    String deliveryAddress = finalArray.getOrderStatus().getAddress().toString();

                    txt_address.setText(deliveryAddress);

                    if(finalArray.getStatus().equalsIgnoreCase("Received"))
                    {


                    }else if(finalArray.getStatus().equalsIgnoreCase("Prepared"))
                    {

                        img_prepared.setBackgroundResource(R.drawable.check_circle_icon);

                    }else if(finalArray.getStatus().equalsIgnoreCase("Outofdelivery"))
                    {
                        img_out_delivery.setBackgroundResource(R.drawable.check_circle_icon);

                    }else if(finalArray.getStatus().equalsIgnoreCase("Delivery"))
                    {
                        img_delivery.setBackgroundResource(R.drawable.check_circle_icon);
                    }
                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
    }

}
