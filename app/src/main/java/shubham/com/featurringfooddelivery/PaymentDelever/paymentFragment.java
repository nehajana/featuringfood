package shubham.com.featurringfooddelivery.PaymentDelever;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import me.yokeyword.swipebackfragment.SwipeBackFragment;
import shubham.com.featurringfooddelivery.MainActivity;
import shubham.com.featurringfooddelivery.OrderBooking.OrderBookingFragment;
import shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment.profilemodel.ProfileModel;
import shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment.profilemodel.UpdatedProfilemodel;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.StripePayment.PaymentActivityStripe;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class paymentFragment extends SwipeBackFragment implements IApiResponse{

    View rootView;
    Button login_place;

    EditText txt_shippingAddress;
    EditText ed_apartment;
    EditText txt_city;

    EditText ed_email,ed_mobile,ed_fname,ed_Lname,ed_zipCode,edt_city;

    public static paymentFragment newInstance(String param1, String param2) {
        paymentFragment fragment = new paymentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_payment, container, false);

        findViews();

        login_place=(Button) rootView.findViewById(R.id.login_place);

        Float amt=  Preference.getFloat(getActivity(),Preference.KEY_amount);

        login_place.setText("Place Order: $"+amt);

        login_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String address = txt_shippingAddress.getText().toString();
                String city = edt_city.getText().toString();
                String email = ed_email.getText().toString();
                String mobile = ed_mobile.getText().toString();
                String zipcode = ed_zipCode.getText().toString();

                if(address.equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Please Enter Shipping Address", Toast.LENGTH_SHORT).show();

                }else if(city.equalsIgnoreCase(""))
                {

                    Toast.makeText(getActivity(), "Please Enter city", Toast.LENGTH_SHORT).show();

                }else if(email.equalsIgnoreCase(""))
                {

                    Toast.makeText(getActivity(), "Please Enter email", Toast.LENGTH_SHORT).show();

                }else if(mobile.equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Please Enter mobile", Toast.LENGTH_SHORT).show();

                }else if(zipcode.equalsIgnoreCase(""))
                {

                    Toast.makeText(getActivity(), "Please Enter ZipCode", Toast.LENGTH_SHORT).show();

                }else
                {
                    Preference.save(getActivity(),Preference.key_PlaceUser_address,address+" "+city);

                    Intent intent=new Intent(getActivity(),PaymentActivityStripe.class);
                    startActivity(intent);
                }
            }
        });

        ProfileMethod();

        GetAddressMethod();

        return attachToSwipeBack(rootView);

    }

    private void findViews() {

        txt_shippingAddress=(EditText) rootView.findViewById(R.id.txt_shippingAddress);
        ed_apartment=(EditText) rootView.findViewById(R.id.ed_apartment);
        ed_email=(EditText) rootView.findViewById(R.id.ed_email);
        ed_mobile=(EditText) rootView.findViewById(R.id.ed_mobile);
        ed_fname=(EditText) rootView.findViewById(R.id.ed_fname);
        ed_Lname=(EditText) rootView.findViewById(R.id.ed_Lname);
        ed_zipCode=(EditText) rootView.findViewById(R.id.ed_zipCode);
        edt_city=(EditText) rootView.findViewById(R.id.edt_city);
    }


    public void ProfileMethod(){
        String User_Id = Preference.get(getActivity(),Preference.KEY_USER_ID);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id",User_Id);
        ApiRequest apiRequest = new ApiRequest(getActivity(),this);
        apiRequest.postRequest(Constants.BASE_URL + Constants.User_Profile, Constants.User_Profile,map, Request.Method.POST);
    }

    public void GetAddressMethod(){
        String User_Id = Preference.get(getActivity(),Preference.KEY_USER_ID);
        String Address_id = Preference.get(getActivity(),Preference.KEY_Address_Id);
        HashMap<String, String> map = new HashMap<>();
        map.put("Address_id",Address_id);
        map.put("user_id",User_Id);
        ApiRequest apiRequest = new ApiRequest(getActivity(),this);
        apiRequest.postRequest(Constants.BASE_URL + Constants.User_GetSingleAddress, Constants.User_GetSingleAddress,map, Request.Method.POST);
    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.User_Profile.equalsIgnoreCase(tag_json_obj)) {

            if (!response.equalsIgnoreCase(null)) {

                ProfileModel finalArray = new Gson().fromJson(response, new TypeToken<ProfileModel>() {
                }.getType());

                String status = String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")) {

                    ed_email.setText(finalArray.getProfile().getEmail());
                    ed_mobile.setText(finalArray.getProfile().getPhone());
                    ed_fname.setText(finalArray.getProfile().getFirstname());
                    ed_Lname.setText(finalArray.getProfile().getLastname());

                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getActivity(), finalArray.getStatus(), Toast.LENGTH_SHORT).show();
                }
            }
        }else  if (Constants.User_GetSingleAddress.equalsIgnoreCase(tag_json_obj)) {

            if (!response.equalsIgnoreCase(null)) {

                GetAddressModel finalArray1 = new Gson().fromJson(response, new TypeToken<GetAddressModel>() {
                }.getType());

                String status = String.valueOf(finalArray1.getStatus());

                if (status.equalsIgnoreCase("success")) {

                    ed_zipCode.setText(finalArray1.getGetSingleAddress().getZipcode());
                    edt_city.setText(finalArray1.getGetSingleAddress().getCity());
                    txt_shippingAddress.setText(finalArray1.getGetSingleAddress().getAddressDetails());
                    ed_apartment.setText(finalArray1.getGetSingleAddress().getApartment());

                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getActivity(), finalArray1.getStatus(), Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

}
