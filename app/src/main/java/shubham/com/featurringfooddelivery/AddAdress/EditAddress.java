package shubham.com.featurringfooddelivery.AddAdress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shubham.com.featurringfooddelivery.AddAdress.ApiModel.DeleteAddressModel;
import shubham.com.featurringfooddelivery.CustomAdapter;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.MainHomeFragment.US_State;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class EditAddress extends AppCompatActivity implements IApiResponse {

    RelativeLayout RR_back;
    String AddressDetails="",ZipCode = "",City="";
    String Address_id;
    String state;
    String apartment;
    EditText edt_address;
    EditText edt_zipcode;
    EditText edt_city;
   // EditText edt_state;
    EditText edt_apartment;
    Button addAdress_btn;
    public Spinner spinner_state;
    EditAddressCustomAdapter customAdapter;
    ArrayList<US_State> states;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        RR_back=(RelativeLayout) findViewById(R.id.RR_back);
        spinner_state = (Spinner)findViewById( R.id.spinner_state );
        edt_apartment=(EditText) findViewById(R.id.edt_apartment);
        edt_address=(EditText) findViewById(R.id.edt_address);
        edt_zipcode=(EditText) findViewById(R.id.edt_zipcode);
        edt_city=(EditText) findViewById(R.id.edt_city);
       // edt_state=(EditText) findViewById(R.id.edt_state);
        addAdress_btn=(Button) findViewById(R.id.addAdress_btn);

       states = new ArrayList<>();
        states.add(new US_State("CA", "California"));
        states.add(new US_State("CO", "Colorado"));
        states.add(new US_State("CT", "Connecticut"));
        states.add(new US_State("DE", "Delaware"));
        states.add(new US_State("DC", "District Of Columbia"));
        states.add(new US_State("FL", "Florida"));
        states.add(new US_State("GA", "Georgia"));
        states.add(new US_State("HI", "Hawaii"));
        states.add(new US_State("ID", "Idaho"));
        states.add(new US_State("IL", "Illinois"));
        states.add(new US_State("IN", "Indiana"));
        states.add(new US_State("IA", "Iowa"));
        states.add(new US_State("KS", "Kansas"));
        states.add(new US_State("KY", "Kentucky"));
        states.add(new US_State("LA", "Louisiana"));
        states.add(new US_State("ME", "Maine"));
        states.add(new US_State("MD", "Maryland"));
        states.add(new US_State("MA", "Massachusetts"));
        states.add(new US_State("MI", "Michigan"));
        states.add(new US_State("MN", "Minnesota"));
        states.add(new US_State("MS", "Mississippi"));
        states.add(new US_State("MO", "Missouri"));
        states.add(new US_State("MT", "Montana"));
        states.add(new US_State("NE", "Nebraska"));
        states.add(new US_State("NV", "Nevada"));
        states.add(new US_State("NH", "New Hampshire"));
        states.add(new US_State("NJ", "New Jersey"));
        states.add(new US_State("NM", "New Mexico"));
        states.add(new US_State("NY", "New York"));
        states.add(new US_State("NC", "North Carolina"));
        states.add(new US_State("ND", "North Dakota"));
        states.add(new US_State("OH", "Ohio"));
        states.add(new US_State("OK", "Oklahoma"));
        states.add(new US_State("OR", "Oregon"));
        states.add(new US_State("PA", "Pennsylvania"));
        states.add(new US_State("RI", "Rhode Island"));
        states.add(new US_State("SC", "South Carolina"));
        states.add(new US_State("SD", "South Dakota"));
        states.add(new US_State("TN", "Tennessee"));
        states.add(new US_State("TX", "Texas"));
        states.add(new US_State("UT", "Utah"));
        states.add(new US_State("VT", "Vermont"));
        states.add(new US_State("VA", "Virginia"));
        states.add(new US_State("WA", "Washington"));
        states.add(new US_State("WV", "West Virginia"));
        states.add(new US_State("WI", "Wisconsin"));
        states.add(new US_State("WY", "Wyoming"));


        FuncSpinner();

        Intent intent=getIntent();

        if(intent !=null)
        {
            AddressDetails=intent.getStringExtra("AddressDretails");
            ZipCode=intent.getStringExtra("ZipCode");
            City=intent.getStringExtra("City");
            Address_id=intent.getStringExtra("Address_id");
            state=intent.getStringExtra("state");
            apartment=intent.getStringExtra("Apartment");

            edt_address.setText(AddressDetails);
            edt_zipcode.setText(ZipCode);
            edt_city.setText(City);
           // edt_state.setText(state);

            for (int i = 0; i < states.size(); i++) {

                if (states.get(i).getName().equalsIgnoreCase(state)) {
                    spinner_state.setSelection(i);
                }
            }

            edt_apartment.setText(apartment);


        }


        RR_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        addAdress_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String addressDetails = edt_address.getText().toString();
                String zipcode = edt_zipcode.getText().toString();
                String city = edt_city.getText().toString();
                String apartment = edt_apartment.getText().toString();

                updatedAddress(Address_id,addressDetails,city,zipcode,apartment,state);

            }
        });


    }


    public void updatedAddress(String Address_id,String AddressDetails,String city,String zipcode,String apartment1,String state1){

        String User_Id = Preference.get(EditAddress.this,Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",User_Id);
        map.put("Address_id",Address_id);
        map.put("city",city);
        map.put("AddressDetails",AddressDetails);
        map.put("zipcode",zipcode);
        map.put("apartment",apartment1);
        map.put("state",state1);

        ApiRequest apiRequest = new ApiRequest(EditAddress.this,this);

        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_UpdateAddress, Constants.USER_UpdateAddress,map, Request.Method.POST);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_UpdateAddress.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                DeleteAddressModel finalArray = new Gson().fromJson(response,new TypeToken<DeleteAddressModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    Toast.makeText(EditAddress.this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(EditAddress.this,HomeBottomActivity.class);
                                  startActivity(intent);


                }else
                {

                    Toast.makeText(EditAddress.this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(EditAddress.this, "Please Enter Check..", Toast.LENGTH_SHORT).show();
    }


    private void FuncSpinner() {

        //  CreateNormalAdsCustomAdapter customAdapter=new CreateNormalAdsCustomAdapter(getApplicationContext(),CategoryArray);
        // SpinnerNew.setAdapter(customAdapter);

          customAdapter=new EditAddressCustomAdapter(EditAddress.this,states);
        spinner_state.setAdapter(customAdapter);
        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {


                  state=states.get(position).getName();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });


    }
}
