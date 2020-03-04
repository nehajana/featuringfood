package shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import me.yokeyword.swipebackfragment.SwipeBackFragment;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.LoginModel;
import shubham.com.featurringfooddelivery.LoginScreen.LoginActivity;
import shubham.com.featurringfooddelivery.MainActivity;
import shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment.profilemodel.ProfileModel;
import shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment.profilemodel.UpdatedProfilemodel;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.SignUpActivity;
import shubham.com.featurringfooddelivery.SignUpmodel;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class ProfileFragment extends SwipeBackFragment implements IApiResponse {

    public ProfileFragment() {
        // Required empty public constructor
    }

    Button SaveProfile_btn;
   // private ScrollView scrolView;
    private TextView txtTitle;
    private LinearLayout RRLayout;
    private EditText edt_firstname;
    private EditText edt_lastName;
    private EditText edtEmail;
    private EditText edtMobile;
    private EditText edtAddress;
    private EditText edtCuntry;
    private LinearLayout LLLayout;
    private EditText edtZipcode;
    private EditText edtCity;
    private Button updateBtn;
    private Button logout_btn;

    View rootview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate( R.layout.frag_profile, container, false);

        findViews();

        edt_firstname.setEnabled(false);
        edt_lastName.setEnabled(false);
        edtEmail.setEnabled(false);
        edtMobile.setEnabled(false);
        edtAddress.setEnabled(false);
       // edtCuntry.setEnabled(false);
        edtZipcode.setEnabled(false);
        edtCity.setEnabled(false);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edt_firstname.setEnabled(true);
                edt_lastName.setEnabled(true);
                edtMobile.setEnabled(true);
                edtAddress.setEnabled(true);
             //   edtCuntry.setEnabled(true);
                edtZipcode.setEnabled(true);
                edtCity.setEnabled(true);

                updateBtn.setVisibility(View.GONE);
                SaveProfile_btn.setVisibility(View.VISIBLE);
            }
        });

        SaveProfile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edt_firstname.setEnabled(false);
                edt_lastName.setEnabled(false);
                edtMobile.setEnabled(false);
                edtAddress.setEnabled(false);
               // edtCuntry.setEnabled(false);
                edtZipcode.setEnabled(false);
                edtCity.setEnabled(false);
                updateBtn.setVisibility(View.VISIBLE);
                SaveProfile_btn.setVisibility(View.GONE);

                String FirstName=edt_firstname.getText().toString();
                String LastName=edt_lastName.getText().toString();
                String mobile=edtMobile.getText().toString();
                String Address=edtAddress.getText().toString();
              //  String country=edtCuntry.getText().toString();
                String zipcode=edtZipcode.getText().toString();
                String city=edtCity.getText().toString();

                UpdateMethod(FirstName,LastName,mobile,zipcode,city,Address,"USA");

            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Preference.clearPreference(getActivity());

                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                  getActivity().finish();
            }
        });

        ProfileMethod();

        return attachToSwipeBack(rootview);
    }

    private void findViews() {
      //  scrolView = (ScrollView) rootview.findViewById( R.id.scrolView );
        txtTitle = (TextView) rootview.findViewById( R.id.txt_title );
        RRLayout = (LinearLayout) rootview.findViewById( R.id.RR_layout );
        edt_firstname = (EditText) rootview.findViewById( R.id.edt_firstname );
        edt_lastName = (EditText) rootview.findViewById( R.id.edt_lastName );
        edtEmail = (EditText) rootview.findViewById( R.id.edt_email );
        edtMobile = (EditText) rootview.findViewById( R.id.edt_mobile );
        edtAddress = (EditText) rootview.findViewById( R.id.edt_address );
//        edtCuntry = (EditText) rootview.findViewById( R.id.edt_cuntry );
        LLLayout = (LinearLayout) rootview.findViewById( R.id.LL_layout );
        edtZipcode = (EditText) rootview.findViewById( R.id.edt_zipcode );
        edtCity = (EditText) rootview.findViewById( R.id.edt_city );
        updateBtn = (Button) rootview.findViewById( R.id.update_btn );
        SaveProfile_btn = (Button) rootview.findViewById( R.id.SaveProfile_btn );
        logout_btn = (Button) rootview.findViewById( R.id.logout_btn );

    }


    public void ProfileMethod(){
        String User_Id = Preference.get(getActivity(),Preference.KEY_USER_ID);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id",User_Id);
        ApiRequest apiRequest = new ApiRequest(getActivity(),this);
        apiRequest.postRequest(Constants.BASE_URL + Constants.User_Profile, Constants.User_Profile,map, Request.Method.POST);
    }

    public void UpdateMethod(String firstname,String LastName,String phone,String zipcode,String city
            ,String shipping_address,String country){

        String User_Id = Preference.get(getActivity(),Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",User_Id);
        map.put("firstname",firstname);
        map.put("lastname",LastName);
        map.put("phone",phone);
        map.put("zipcode",zipcode);
        map.put("city",city);
        map.put("shipping_address",shipping_address);
        map.put("country",country);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.User_update_profile, Constants.User_update_profile,map, Request.Method.POST);
    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.User_Profile.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                ProfileModel finalArray = new Gson().fromJson(response,new TypeToken<ProfileModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                  //  Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                    edt_firstname.setText(finalArray.getProfile().getFirstname());
                    edt_lastName.setText(finalArray.getProfile().getLastname());
                    edtEmail.setText(finalArray.getProfile().getEmail());
                    edtMobile.setText(finalArray.getProfile().getPhone());
                    edtAddress.setText(finalArray.getProfile().getShippingAddress());
                //    edtCuntry.setText(finalArray.getProfile().getCountry());
                    edtZipcode.setText(finalArray.getProfile().getZipcode());
                    edtCity.setText(finalArray.getProfile().getCity());

                }
                else {

                  //  Toast.makeText(getActivity(), finalArray.getStatus(), Toast.LENGTH_SHORT).show();
                }
            }
        }else  if (Constants.User_update_profile.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                UpdatedProfilemodel finalArray = new Gson().fromJson(response,new TypeToken<UpdatedProfilemodel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if(status.equalsIgnoreCase("success"))
                {
                  //  Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                   /* getActivity().finish();
                    startActivity(getActivity().getIntent());
                    */

                   // Preference.save(getActivity(),Preference.KEY_USER_ID,user_id);

                    String Address=finalArray.getShippingAddress().toString();
                    String Zipcode=finalArray.getZipcode().toString();

                    String finalAddress= Address+","+Zipcode;

                    Preference.save(getActivity(),Preference.KEY_Address,finalAddress);

                    FragmentTransaction ft = getFragmentManager().beginTransaction();

                    if (Build.VERSION.SDK_INT >= 26) {
                        ft.setReorderingAllowed(false);
                    }
                    ft.detach(this).attach(this).commit();
                }
                else
                {

                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

}
