package shubham.com.featurringfooddelivery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import me.yokeyword.swipebackfragment.SwipeBackActivity;
import shubham.com.featurringfooddelivery.OrderBooking.AbstractModelPost;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class SignUpActivity extends SwipeBackActivity implements IApiResponse,AdapterView.OnItemSelectedListener{

    private RelativeLayout RRToolbar;
    private RelativeLayout RRBack;
    private TextView txtPersonal;
    private LinearLayout LLLayout;
    private EditText edtEmail;
    private EditText edtFirstName;
    private EditText edtShipingAddress;
    private EditText edtMobile;
    private EditText edtLastName;
    private RelativeLayout RRLayout;
    private TextView txtFloor;
    private EditText edtApartment;
    private LinearLayout LLLayout2;
    private EditText edtZipcode;
    private EditText edtPassword;
    private EditText edtCity;
    private EditText edtConfirmpassword;
    private Button btnSignup;
    private LinearLayout view;
    private TextView txtView;
    private RelativeLayout RRSocial;
    private RelativeLayout RRSocialOne;
    private ImageView imvLoginFacebook;
    private ImageView imvLoginGoogle;
    private RelativeLayout RRLogin;
    private View viewRegister;
    private TextView txtNotRegister;
    private TextView txtSignUp;
    private TextView spn_country;

    String edtEmail_str;
    String edtMobile_str;
    String edtFirstName_str;
    String edtLastName_str;
    String edtShipingAddress_str;
    String edtApartment_str;
    String edtZipcode_str;
    String edtCity_str;
    String edtPassword_str;
    String edtConfirmpassword_str;

    CustomAdapter customAdapter;

    String[] Country={"India","China","Australia","Portugle","America","New Zealand"};

    private ArrayList<CountryDatamodel> modelList_countryList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViews();

       // spn_country.setOnItemSelectedListener(this);


      txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               validation();
               /* Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);*/
            }
        });

        getCuntryListMethod();
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

        // Toast.makeText(getApplicationContext(), Country[position], Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    private void findViews() {

         spn_country = (TextView) findViewById(R.id.spn_country);

        RRToolbar = (RelativeLayout)findViewById( R.id.RR_toolbar );
        RRBack = (RelativeLayout)findViewById( R.id.RR_back );
        txtPersonal = (TextView)findViewById( R.id.txt_personal );
        LLLayout = (LinearLayout)findViewById( R.id.LL_layout );
        edtEmail = (EditText)findViewById( R.id.edt_email );
        edtFirstName = (EditText)findViewById( R.id.edt_firstName );
        edtShipingAddress = (EditText)findViewById( R.id.edt_shipingAddress );
        edtMobile = (EditText)findViewById( R.id.edt_mobile );
        edtLastName = (EditText)findViewById( R.id.edt_lastName );
        RRLayout = (RelativeLayout)findViewById( R.id.RR_layout );
        txtFloor = (TextView)findViewById( R.id.txt_floor );
        edtApartment = (EditText)findViewById( R.id.txt_apartment );
        LLLayout2 = (LinearLayout)findViewById( R.id.LL_layout2 );
        edtZipcode = (EditText)findViewById( R.id.edt_zipcode );
        edtPassword = (EditText)findViewById( R.id.edt_password );
        edtCity = (EditText)findViewById( R.id.edt_city );
        edtConfirmpassword = (EditText)findViewById( R.id.edt_Confirmpassword );
        btnSignup = (Button)findViewById( R.id.btn_signup );
        view = (LinearLayout)findViewById( R.id.view );
        txtView = (TextView)findViewById( R.id.txt_view );
        RRSocial = (RelativeLayout)findViewById( R.id.RR_social );
        RRSocialOne = (RelativeLayout)findViewById( R.id.RR_social_one );
        imvLoginFacebook = (ImageView)findViewById( R.id.imv_login_facebook );
        imvLoginGoogle = (ImageView)findViewById( R.id.imv_login_google );
        RRLogin = (RelativeLayout)findViewById( R.id.RR_login );
        viewRegister = (View)findViewById( R.id.view_register );
        txtNotRegister = (TextView)findViewById( R.id.txt_not_register );
        txtSignUp = (TextView)findViewById( R.id.txt_signUp );
    }

    private void validation(){

        edtEmail_str=edtEmail.getText().toString();
        edtMobile_str=edtMobile.getText().toString();
        edtFirstName_str=edtFirstName.getText().toString();
        edtLastName_str=edtLastName.getText().toString();
        edtShipingAddress_str=edtShipingAddress.getText().toString();
        edtApartment_str=edtApartment.getText().toString();
        edtZipcode_str=edtZipcode.getText().toString();
        edtCity_str=edtCity.getText().toString();
        edtPassword_str=edtPassword.getText().toString();
        edtConfirmpassword_str=edtConfirmpassword.getText().toString();

        if(!isValidEmail(edtEmail_str)){
            Toast.makeText(SignUpActivity.this, "Please enter correct email address.", Toast.LENGTH_SHORT).show();
        } else if(edtMobile_str.equalsIgnoreCase("")){
            Toast.makeText(SignUpActivity.this, "Please enter your mobile.", Toast.LENGTH_SHORT).show();
        }  else if(edtFirstName_str.equalsIgnoreCase("")){
            Toast.makeText(SignUpActivity.this, "Please enter your firstname.", Toast.LENGTH_SHORT).show();
        }   else if(edtLastName_str.equalsIgnoreCase("")){
            Toast.makeText(SignUpActivity.this, "Please enter your LastName.", Toast.LENGTH_SHORT).show();
        }   else if(edtShipingAddress_str.equalsIgnoreCase("")){
            Toast.makeText(SignUpActivity.this, "Please enter your Shipping Address.", Toast.LENGTH_SHORT).show();
        }   else if(edtApartment_str.equalsIgnoreCase("")){
            Toast.makeText(SignUpActivity.this, "Please enter your Appartment.", Toast.LENGTH_SHORT).show();
        }   else if(edtZipcode_str.equalsIgnoreCase("")){
            Toast.makeText(SignUpActivity.this, "Please enter your ZipCode.", Toast.LENGTH_SHORT).show();
        }   else if(edtCity_str.equalsIgnoreCase("")){
            Toast.makeText(SignUpActivity.this, "Please enter your City.", Toast.LENGTH_SHORT).show();
        }   else if(edtPassword_str.equalsIgnoreCase("")){
            Toast.makeText(SignUpActivity.this, "Please enter your Password.", Toast.LENGTH_SHORT).show();
        } else if(edtConfirmpassword_str.equalsIgnoreCase("")){
            Toast.makeText(SignUpActivity.this, "Please enter your ConfirmPassword.", Toast.LENGTH_SHORT).show();
        }
        else if(!edtPassword_str.equalsIgnoreCase(edtConfirmpassword_str)){
            Toast.makeText(SignUpActivity.this, "Don't Match Passord", Toast.LENGTH_SHORT).show();
        }
        else {

           SignUpMethod(edtFirstName_str,edtLastName_str,edtEmail_str,edtPassword_str,edtMobile_str
                   ,edtShipingAddress_str,edtCity_str,"USA",edtZipcode_str);
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    public void getCuntryListMethod(){

        HashMap<String, String> map = new HashMap<>();

        ApiRequest apiRequest = new ApiRequest(this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_GetCountryList, Constants.USER_GetCountryList,map, Request.Method.GET);

    }


    public void SignUpMethod(String firstname,String lastname,String email,String password,String phone,String shipping_address
            ,String city,String country,String zipcode){

        HashMap<String, String> map = new HashMap<>();

        map.put("firstname",firstname);
        map.put("lastname",lastname);
        map.put("email",email);
        map.put("password",password);
        map.put("phone",phone);
        map.put("shipping_address",shipping_address);
        map.put("city",city);
        map.put("country",country);
        map.put("zipcode",zipcode);

        ApiRequest apiRequest = new ApiRequest(SignUpActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_SINGUP, Constants.USER_SINGUP,map, Request.Method.POST);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_SINGUP.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                SignUpmodel finalArray = new Gson().fromJson(response,new TypeToken<SignUpmodel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if(status.equalsIgnoreCase("success"))
                {
                    Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }else if (Constants.USER_GetCountryList.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                CountryNamemodel finalArray = new Gson().fromJson(response,new TypeToken<CountryNamemodel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

               /*// if(status.equalsIgnoreCase("success"))
                {
                    //Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    modelList_countryList = (ArrayList<CountryDatamodel>) finalArray.getCountryList();

                    customAdapter=new CustomAdapter(getApplicationContext(),modelList_countryList);

                   // spn_country.setAdapter(customAdapter);

                }
               // else
                {
                    Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }*/
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this,"error"+error, Toast.LENGTH_SHORT).show();

    }
}
