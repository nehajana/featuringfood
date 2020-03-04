package shubham.com.featurringfooddelivery.OTPScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.featurringfooddelivery.GoogleLocation.GoogleMapActivity;
import shubham.com.featurringfooddelivery.LoginScreen.OtpLoginModel;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;


public class OtpActvity extends AppCompatActivity implements IApiResponse {

    String phoneno;
    Button bttn_save;
    RelativeLayout rll_arrow;
    TextView txt_phonenumber;
    EditText edt_otp1,edt_otp2,edt_otp3,edt_otp4;
    RelativeLayout rll_next;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_login);

        findview();

        Intent intent = getIntent();
        if(intent !=null) {
            phoneno = intent.getStringExtra("phonenumber");
        }

        txt_phonenumber.setText(phoneno);
        bttn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressbar.setVisibility(View.VISIBLE);

                otp_signin(phoneno,"1234");

            }
        });

        rll_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        edt_otp1.requestFocus();
        edt_otp1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (s.length() ==1) {
                    edt_otp2.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (s.length() ==1) {
                    edt_otp1.requestFocus();
                }
            }

            public void onTextChanged(CharSequence s, int start, int before,int count) {
            }
        });

        edt_otp2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edt_otp3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
                if (s.length() ==1) {
                    edt_otp1.requestFocus();
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        edt_otp3.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edt_otp4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() ==1) {
                    edt_otp2.requestFocus();
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        edt_otp4.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
               /* if (s.length() == 1) {
                    edt_otp5.requestFocus();
                }*/

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                if (s.length() ==1) {
                    edt_otp3.requestFocus();
                }

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

    }

    private void findview()
    {
        bttn_save=(Button) findViewById(R.id.bttn_save);
        rll_arrow=(RelativeLayout) findViewById(R.id.rll_arrow);
        txt_phonenumber=(TextView) findViewById(R.id.txt_phonenumber);
        edt_otp1=(EditText) findViewById(R.id.edt_otp1);
        edt_otp2=(EditText) findViewById(R.id.edt_otp2);
        edt_otp3=(EditText) findViewById(R.id.edt_otp3);
        edt_otp4=(EditText) findViewById(R.id.edt_otp4);
        rll_next=(RelativeLayout) findViewById(R.id.rll_next);
        progressbar=(ProgressBar) findViewById(R.id.progressbar);

    }
    public void otp_signin(String mobile,String otp_code){

        HashMap<String, String> map = new HashMap<>();

        map.put("mobile",mobile);
        map.put("otp_code",otp_code);

        ApiRequest apiRequest = new ApiRequest(OtpActvity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_MATCHOTP, Constants.USER_MATCHOTP,map, Request.Method.POST);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_MATCHOTP.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                progressbar.setVisibility(View.GONE);

                OtpLoginModel finalArray = new Gson().fromJson(response,new TypeToken<OtpLoginModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    Preference.save(this,Preference.KEY_USER_ID,finalArray.getUserid().toString());

                 Intent myIntent = new Intent( OtpActvity.this, GoogleMapActivity.class );
                    startActivity( myIntent );
                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        progressbar.setVisibility(View.GONE);

        Toast.makeText(this, "Please Check Your Network", Toast.LENGTH_SHORT).show();
    }
}
