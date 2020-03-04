package shubham.com.featurringfooddelivery.StripePayment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import java.util.HashMap;

import me.yokeyword.swipebackfragment.SwipeBackActivity;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.PaymentDelever.PaymentActivity;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.ThankuScreen.ConfirmActivity;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class PaymentActivityStripe extends SwipeBackActivity implements IApiResponse {

    TextView text_toolbar;
    private CardInputWidget mCardInputWidget;
    private ErrorDialogHandler mErrorDialogHandler;
    private ProgressDialogController mProgressDialogController;
    Button pay_btn;
    Card card;
    ProgressDialog pDialog;
    ImageView img_back;
    String media_id;

    String paidAmt;
    Float amt;
    EditText fullname_etxt;
    EditText email_etxt;
    String fullName;

    String email;
    String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_stripe);
        //  text_toolbar = (TextView) findViewById(R.id.text_toolbar);
//        text_toolbar.setText("Payment");
        mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);
        pay_btn = (Button) findViewById(R.id.pay_btn);
        fullname_etxt = (EditText) findViewById(R.id.fullname_etxt1);
        email_etxt = (EditText) findViewById(R.id.email_etxt);
     /*   img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/


        final Card cardToSave = mCardInputWidget.getCard();
        mErrorDialogHandler = new ErrorDialogHandler(getSupportFragmentManager());
        mProgressDialogController =
                new ProgressDialogController(getSupportFragmentManager());
        pDialog = new ProgressDialog(PaymentActivityStripe.this);
        pDialog.setMessage(PaymentActivityStripe.this.getResources().getString(R.string.loading));

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*card = new Card("4242424242424242", 05, 25, "123");
                CreateToken(card);
                pDialog.show();
                pDialog.setCancelable(false);*/
                fullName = fullname_etxt.getText().toString();
                email = email_etxt.getText().toString();

                if (mCardInputWidget.getCard() == null) {
                    mErrorDialogHandler.showError("Invalid Card Data");
                    return;
                }
                if (!mCardInputWidget.getCard().validateCard()) {
                    mErrorDialogHandler.showError("Invalid Card Data");
                    return;
                }
                else if (fullName == null || fullName.equalsIgnoreCase("")) {
                    Toast.makeText(PaymentActivityStripe.this, "Please enter name.", Toast.LENGTH_SHORT).show();

                } else if (!isValidEmail(email)) {
                    Toast.makeText(PaymentActivityStripe.this, "Please enter correct email address.", Toast.LENGTH_SHORT).show();

                } else {



                    String cvv = mCardInputWidget.getCard().getCVC();
                    int exp = mCardInputWidget.getCard().getExpMonth();
                    int exp_year = mCardInputWidget.getCard().getExpYear();
                    String card_num = mCardInputWidget.getCard().getNumber();
                    card = new Card(card_num, exp, exp_year, cvv);
                    CreateToken(card);
                    pDialog.show();
                    pDialog.setCancelable(false);


                }
            }
        });

        // usrName=Preference.get(PaymentActivityStripe.this,Preference.key_PlaceUser_name);
        // String email= Preference.get(PaymentActivityStripe.this,Preference.key_PlaceUser_email);
        //   address=Preference.get(PaymentActivityStripe.this,Preference.key_PlaceUser_address);

      /* if (getIntent().getExtras() != null) {
            paidAmt = getIntent().getExtras().getString("paidAmt");
            media_id = getIntent().getExtras().getString("media_id");

        } else {
            Toast.makeText(this, "please try after some time", Toast.LENGTH_SHORT).show();
        }*/


    }

    private void CreateToken(Card card) {
        //   Stripe stripe = new Stripe(PaymentActivity.this, "pk_test_Uoon6IQ6rBk6zXnX3NMaWpp5");
       // Stripe stripe = new Stripe(PaymentActivityStripe.this, "pk_test_YPlyXgFvAegWYwdWPkm5vYYg00qgW4ePHd");
        Stripe stripe = new Stripe(PaymentActivityStripe.this, "pk_test_MeWfB2021F2RCCdI9AnZ4fzp00R5yvfgZf");
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                      //  Toast.makeText(PaymentActivityStripe.this, "Success "+token.getId(), Toast.LENGTH_SHORT).show();
                        // Toast.makeText(PaymentActivity.this, "Please wait..", Toast.LENGTH_SHORT).show();
                        // Show localized error message
                        Log.e("token_Stripe",token.getId());

                        pDialog.cancel();

                        Login(token);

                    }

                    public void onError(Exception error) {
                        // Show localized error message
                        pDialog.cancel();
                        Toast.makeText(PaymentActivityStripe.this, error.getLocalizedMessage(), Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );
    }

  /*  private void Login(Token token) {
        String userid = Preference.get(PaymentActivityStripe.this, Preference.KEY_USER_ID);
        paidAmt =Preference.get(PaymentActivityStripe.this,Preference.KEY_amount);
        address=Preference.get(PaymentActivityStripe.this,Preference.key_PlaceUser_address);

        int type = Preference.getInt(PaymentActivityStripe.this, Preference.TYPE);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", "157");
        map.put("stripeToken",String.valueOf(token.getId()));
        map.put("media_id", "22");
        map.put("type", "type");
        map.put("paymentAmt", "4000");
        map.put("name", "Harshit");
        map.put("address", "Indore");
        map.put("email", "harshit89@gmail.com");
        map.put("description", "Indore");
        ApiRequest apiRequest = new ApiRequest(PaymentActivityStripe.this, this);
        apiRequest.postRequest(Preference.PAY_FOR_CHANNEL, Preference.PAY_FOR_CHANNEL, map, Request.Method.POST);
    }*/


    private void Login(Token token) {
        String userid = Preference.get(PaymentActivityStripe.this, Preference.KEY_USER_ID);

        amt=  Preference.getFloat(PaymentActivityStripe.this,Preference.KEY_amount);

        /*paidAmt =Preference.get(PaymentActivityStripe.this,Preference.KEY_amount);*/
        address=Preference.get(PaymentActivityStripe.this,Preference.key_PlaceUser_address);
        int type = Preference.getInt(PaymentActivityStripe.this, Preference.TYPE);
        String Ordertype = Preference.get(PaymentActivityStripe.this, Preference.KEY_Ordertype);
        String OrderDay = Preference.get(PaymentActivityStripe.this, Preference.KEY_OrderDay);
        String OrderTime = Preference.get(PaymentActivityStripe.this, Preference.KEY_OrderTime);

        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", userid);
        map.put("stripeToken", String.valueOf(token.getId()));
        map.put("media_id", "22");
        map.put("type", "type");
       // map.put("paymentAmt","4000" );
        map.put("paymentAmt", String.valueOf(amt));
        map.put("name", fullName);
        map.put("address", address);
        map.put("email", email);
        map.put("description", "Indore");
        map.put("repeatorder", Ordertype);
        map.put("servetime", OrderTime);
        map.put("day", OrderDay);

        ApiRequest apiRequest = new ApiRequest(PaymentActivityStripe.this, this);
        apiRequest.postRequest(Preference.PAY_FOR_CHANNEL, Preference.PAY_FOR_CHANNEL, map, Request.Method.POST);
    }

    private void Validation() {

        fullName = fullname_etxt.getText().toString();
        email = email_etxt.getText().toString();

        if (mCardInputWidget.getCard() == null) {
            mErrorDialogHandler.showError("Invalid Card Data");
            return;
        } else if (mCardInputWidget.getCard() != null || mCardInputWidget.getCard().validateCard()) {
            String cvv = mCardInputWidget.getCard().getCVC();
            int exp = mCardInputWidget.getCard().getExpMonth();
            int exp_year = mCardInputWidget.getCard().getExpYear();
            String card_num = mCardInputWidget.getCard().getNumber();
            card = new Card(card_num, exp, exp_year, cvv);

        } else if (fullName.equalsIgnoreCase("")) {
            Toast.makeText(PaymentActivityStripe.this, "Please enter name.", Toast.LENGTH_SHORT).show();

        } else if (!isValidEmail(email)) {
            Toast.makeText(PaymentActivityStripe.this, "Please enter correct email address.", Toast.LENGTH_SHORT).show();

        } else {
            CreateToken(card);
            pDialog.show();
            pDialog.setCancelable(false);
        }


    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (tag_json_obj.equalsIgnoreCase(Preference.PAY_FOR_CHANNEL)) {

            if (response != null) {

                PayModel finalArray = new Gson().fromJson(response, new TypeToken<PayModel>() {
                }.getType());

                Boolean status = finalArray.getStatus();

                if (status == true) {

                    Toast.makeText(this, "Payment Successfull", Toast.LENGTH_SHORT).show();

                    Preference.save(PaymentActivityStripe.this,Preference.KEY_OrderiD,finalArray.getOrderid());

                    Intent in=new Intent(PaymentActivityStripe.this,ConfirmActivity.class);
                    in.putExtra("orderid",finalArray.getOrderid());
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                    finish();

                }else {

                    Toast.makeText(this,"Payment failed 1", Toast.LENGTH_SHORT).show();

                }

            }else {

                Toast.makeText(this, "Payment failed 2", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onErrorResponse (VolleyError error){

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }




/*    private void Login(Token token) {
        String userid = Preference.get(PaymentActivityStripe.this, Preference.KEY_USER_ID);
        paidAmt =Preference.get(PaymentActivityStripe.this,Preference.KEY_amount);
        address=Preference.get(PaymentActivityStripe.this,Preference.key_PlaceUser_address);

        int type = Preference.getInt(PaymentActivityStripe.this, Preference.TYPE);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", userid);
        map.put("stripeToken", String.valueOf(token.getId()));
        // map.put("media_id", media_id);
        map.put("type", String.valueOf(type));
        map.put("paymentAmt", paidAmt);
        map.put("name", fullName);
        map.put("address", address);
        map.put("email", email);
        ApiRequest apiRequest = new ApiRequest(PaymentActivityStripe.this, this);
        apiRequest.postRequest(Preference.PAY_FOR_CHANNEL, Preference.PAY_FOR_CHANNEL, map, Request.Method.POST);
    }*/

}
