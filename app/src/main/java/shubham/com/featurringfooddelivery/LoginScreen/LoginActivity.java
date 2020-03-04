package shubham.com.featurringfooddelivery.LoginScreen;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import shubham.com.featurringfooddelivery.OTPScreen.OtpActvity;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class LoginActivity extends AppCompatActivity implements IApiResponse {

    private static final int PERMISSION_REQUEST_CODE = 200;
    Button btn_continue;
    ImageView img_next;
    EditText edt_mobn;
    EditText edt_phoneno;
    TextView txt_signup;
    TextView textview;
    ImageView img_flag;
    Spinner sp;
    String Mobilenm;
    TextView txt_two;
    TextView txt_privacy_policy;
    CheckBox checkbox;

    int code[] ={+1};
    int flags[]= {R.drawable.usa_flag};

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login);

        findview();

        MyLoginAdapter customAdapter=new MyLoginAdapter(LoginActivity.this,flags,code);
        sp.setAdapter(customAdapter);
        sp.setEnabled(false);

        checkLocationPermission();

        img_next.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {
                Mobilenm = edt_phoneno.getText().toString();
                if(Mobilenm.equalsIgnoreCase(""))
                {
                    Toast.makeText(LoginActivity.this, "please enter mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(Mobilenm.length()<9)
                {
                    Toast.makeText(LoginActivity.this, "please enter  valid mobile number", Toast.LENGTH_SHORT).show();
                }
                else  if(!checkbox.isChecked())
                {
                    Toast.makeText(LoginActivity.this, "Please Selected Terms Condition", Toast.LENGTH_SHORT).show();

                }
                else {
                    progressbar.setVisibility(View.VISIBLE);

                    LoginMobile(Mobilenm);

                }
                //  Mobilenm = edt_mobn.getText().toString();

            }
        });


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup)view;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }


    private void findview()
    {  progressbar=(ProgressBar) findViewById(R.id.progressbar);

        img_next = (ImageView) findViewById(R.id.img_next);
     /*   textview=(TextView) findViewById(R.id.textview);
        img_flag=(ImageView) findViewById(R.id.img_flag);*/
        sp=(Spinner)findViewById(R.id.spinner);
        edt_phoneno=(EditText) findViewById(R.id.edt_phoneno);
        txt_two=(TextView) findViewById(R.id.txt_terms_condition);
        txt_privacy_policy=(TextView) findViewById(R.id.txt_privacy_policy);
        checkbox=(CheckBox) findViewById(R.id.checkbox);
    }
    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setTitle("Location")
                        .setMessage("Permission allow")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(LoginActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                    }

                } else {
                }
                return;
            }

        }
    }


    public void LoginMobile(String mobile){

        HashMap<String, String> map = new HashMap<>();

        map.put("mobile",mobile);

        ApiRequest apiRequest = new ApiRequest(LoginActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_LOGIN_MOBILE, Constants.USER_LOGIN_MOBILE,map, Request.Method.POST);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_LOGIN_MOBILE.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                progressbar.setVisibility(View.GONE);

                MobileLoginModel finalArray = new Gson().fromJson(response,new TypeToken<MobileLoginModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){


                    Intent myIntent = new Intent( LoginActivity.this, OtpActvity.class );
                    myIntent.putExtra("phonenumber",Mobilenm);
                    startActivity( myIntent );

                }
                else {

                    Toast.makeText(this, "Login UnSuccess", Toast.LENGTH_SHORT).show();

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

