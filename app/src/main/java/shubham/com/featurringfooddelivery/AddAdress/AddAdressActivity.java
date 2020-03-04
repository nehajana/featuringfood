package shubham.com.featurringfooddelivery.AddAdress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.featurringfooddelivery.AddAdress.ApiModel.AddAddressModel;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeCategoryModel;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class AddAdressActivity extends AppCompatActivity  implements IApiResponse {

    RelativeLayout RR_back;
    Button addAdress_btn;
    EditText edt_address;
    EditText edt_zipcode;
    EditText edt_city;
    EditText edt_apartment;
    EditText edt_state;

    String AddressType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress2);

        RR_back=(RelativeLayout) findViewById(R.id.RR_back);
        addAdress_btn=(Button) findViewById(R.id.addAdress_btn);
        edt_address=(EditText) findViewById(R.id.edt_address);
        edt_apartment=(EditText) findViewById(R.id.edt_apartment);
        edt_state=(EditText) findViewById(R.id.edt_state);
        edt_zipcode=(EditText) findViewById(R.id.edt_zipcode);
        edt_city=(EditText) findViewById(R.id.edt_city);


        RR_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                onBackPressed();

            }
        });

        addAdress_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String  Adddress =  edt_address.getText().toString();
              String  ZipCode = edt_zipcode.getText().toString();
              String  city = edt_city.getText().toString();
              String  apartment = edt_apartment.getText().toString();
              String  state = edt_state.getText().toString();

              if(Adddress.equalsIgnoreCase(""))
              {
                  Toast.makeText(AddAdressActivity.this, "Please Enter Address", Toast.LENGTH_SHORT).show();

              }else if(ZipCode.equalsIgnoreCase(""))
              {
                  Toast.makeText(AddAdressActivity.this, "Please Enter ZipCode", Toast.LENGTH_SHORT).show();

              }else if(city.equalsIgnoreCase(""))
              {
                  Toast.makeText(AddAdressActivity.this, "Please Enter City", Toast.LENGTH_SHORT).show();

              }else if(apartment.equalsIgnoreCase(""))
              {
                  Toast.makeText(AddAdressActivity.this, "Please Enter apartment", Toast.LENGTH_SHORT).show();

              }else if(state.equalsIgnoreCase(""))
              {
                  Toast.makeText(AddAdressActivity.this, "Please Enter state", Toast.LENGTH_SHORT).show();

              }else
              {
                  AddAddressMethod(Adddress,city,ZipCode,apartment,state);
              }
            }
        });

    }

    public void AddAddressMethod(String AddressDetails,String city,String Pincode,String apartment,String state){

        String user_id = Preference.get(AddAdressActivity.this,Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",user_id);
        map.put("country","USA");
        map.put("AddressDetails",AddressDetails);
        map.put("city",city);
        map.put("zipcode",Pincode);
        map.put("apartment",apartment);
        map.put("state",state);

        ApiRequest apiRequest = new ApiRequest(AddAdressActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_AddAddress, Constants.USER_AddAddress,map, Request.Method.POST);

    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_AddAddress.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                AddAddressModel finalArray = new Gson().fromJson(response,new TypeToken<AddAddressModel>(){}.getType());

                String status= finalArray.getStatus();

                if (status.equalsIgnoreCase("success")){

                    // progressbar.setVisibility(View.GONE);

                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(AddAdressActivity.this, HomeBottomActivity.class);
                    startActivity(intent);

                }
                else {

                    // progressbar.setVisibility(View.GONE);

                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
