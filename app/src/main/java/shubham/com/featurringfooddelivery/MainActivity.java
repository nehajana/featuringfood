package shubham.com.featurringfooddelivery;

import android.content.Intent;
import shubham.com.featurringfooddelivery.Preference;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class MainActivity extends AppCompatActivity implements IApiResponse,GoogleApiClient.OnConnectionFailedListener{

    private TextView txtEmail;
    private TextView txtEmailStar;
    private TextView txt_signUp;
    private EditText etEmail;
    private TextView txtPassword;
    private TextView txtPasswordStar;
    private EditText etPassword;
    private RelativeLayout LLKeepMeLogin;
    private CheckBox checkMeLogin;
    private Button login;
    private LinearLayout view;
    Boolean isFromGoogle = false;
    String etEmail_str;
    String etPassword_str;

    //GoogleSign
    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;

    String KEY_isKeepMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.red));
        }

        mAuth = FirebaseAuth.getInstance();

      /*  String User_id=Preference.get(MainActivity.this,Preference.KEY_USER_ID);

        if(User_id != null && !User_id.trim().equalsIgnoreCase("0")){

            Intent intent=new Intent(MainActivity.this,HomeBottomActivity.class);
            startActivity(intent);

        }

*/
        findViews();

        KEY_isKeepMe = Preference.get(MainActivity.this,Preference.KEY_isKeepMe);

        if(KEY_isKeepMe == null || KEY_isKeepMe.equalsIgnoreCase("0"))
        {

        }
        else {

            Intent intent = new Intent(MainActivity.this, HomeBottomActivity.class);
            startActivity(intent);
            finish();
        }


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isFromGoogle = false;
                validation();
            }
        });

        txt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void itemClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){

            Preference.save(MainActivity.this,Preference.KEY_isKeepMe,"1");

        }else
        {
            Preference.save(MainActivity.this,Preference.KEY_isKeepMe,"0");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            //idToken = account.getIdToken();
            //  Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

            String gogleName = account.getDisplayName();
            String email = account.getEmail();
            String googleId = account.getId();
            String token=account.getIdToken();

            isFromGoogle = true;

            String[] listofnames = gogleName.split(" ");

            String firstName = listofnames[0];
            String lastName = listofnames[1];

            loginMethod(email,"",firstName,lastName,googleId);

          /*  Intent intent=new Intent(MainActivity.this,HomeBottomActivity.class);
                  startActivity(intent);*/
        }
        else {

            Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }

    }



    private void findViews() {
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        txtEmail = (TextView)findViewById( R.id.txt_email );
        txt_signUp = (TextView)findViewById( R.id.txt_signUp );
        txtEmailStar = (TextView)findViewById( R.id.txt_email_star );
        etEmail = (EditText)findViewById( R.id.et_email );
        txtPassword = (TextView)findViewById( R.id.txt_password );
        txtPasswordStar = (TextView)findViewById( R.id.txt_password_star );
        etPassword = (EditText)findViewById( R.id.et_password );
        LLKeepMeLogin = (RelativeLayout)findViewById( R.id.LL_Keep_Me_login );
        checkMeLogin = (CheckBox)findViewById( R.id.check_Me_login );
        login = (Button)findViewById( R.id.login );
        view = (LinearLayout)findViewById( R.id.view );
    }
    private void validation(){

        etEmail_str=etEmail.getText().toString();
        etPassword_str=etPassword.getText().toString();

        if(!isValidEmail(etEmail_str)){

            Toast.makeText(MainActivity.this, "Please enter correct email address.", Toast.LENGTH_SHORT).show();

        } else if(etPassword_str.equalsIgnoreCase("")){
            Toast.makeText(MainActivity.this, "Please enter your mobile.", Toast.LENGTH_SHORT).show();
        }
        else {

            loginMethod(etEmail_str,etPassword_str,"","","");
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    public void loginMethod(String email,String password,String firstname,String lastName,String gplus_id){

        HashMap<String, String> map = new HashMap<>();

        map.put("email",email);
        map.put("password",password);
        map.put("firstname",firstname);
        map.put("lastname",lastName);
        map.put("gplus_id",gplus_id);

        ApiRequest apiRequest = new ApiRequest(MainActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_LOGIN, Constants.USER_LOGIN,map, Request.Method.POST);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_LOGIN.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                LoginModel finalArray = new Gson().fromJson(response,new TypeToken<LoginModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){
                    String Zipcode = "",Address= "",finalAddress= "";

                    if(!isFromGoogle){

                        Address=finalArray.getShippingAddress().toString();
                        Zipcode=finalArray.getZipcode().toString();


                    }else
                    {
                        Address="No Address";
                        Zipcode="No Address";
                    }

                    String user_id=finalArray.getUserId().toString();


                    Preference.save(MainActivity.this,Preference.KEY_USER_ID,user_id);


                    Preference.save(MainActivity.this,Preference.KEY_Address,Address);
                    Preference.save(MainActivity.this,Preference.KEY_ZipCode,Zipcode);

                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();

                    Intent intent= new Intent(MainActivity.this,HomeBottomActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {

                    Toast.makeText(this, "Login UnSuccess", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Your Network", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
