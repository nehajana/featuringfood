package shubham.com.featurringfooddelivery.HomeScreen;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import shubham.com.featurringfooddelivery.AddAdress.AddAdressFragment;
import shubham.com.featurringfooddelivery.HomeFragment.HomeFragment;
import shubham.com.featurringfooddelivery.OrderBooking.OrderBookingFragment;
import shubham.com.featurringfooddelivery.OrderHistoryScreen.OrderHistoryFragmentOneMain;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.TrackOrderFragment.TrackOrderFragment;

public class HomeBottomActivity extends AppCompatActivity {

    RelativeLayout RR_home,RR_order,RR_special,RR_cooking;

    Fragment fragment;

    public static RelativeLayout RR_back;
    public static RelativeLayout RR_toolbar;
    public static RelativeLayout RR_profile;
    public static TextView txt_title;
    public static TextView txt_title_address;
    public static TextView txt_title_delivery;

    boolean doubleBackToExitPressedOnce = false;
    ImageView img_home;
    TextView txt_categories;
    TextView  txt_order;
    TextView txt_special;
    TextView txt_cooking;
   public static RelativeLayout RR_wrong_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_bottom);

        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.red));
        }

        findview();

        RR_back.setVisibility(View.INVISIBLE);

        RR_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   txt_categories.setTextColor(getResources().getColor(R.color.white));
                txt_order.setTextColor(getResources().getColor(R.color.darkgray));
                txt_special.setTextColor(getResources().getColor(R.color.darkgray));
                txt_cooking.setTextColor(getResources().getColor(R.color.darkgray));

                RR_home.setBackgroundResource(R.color.red);
                RR_order.setBackgroundResource(R.color.gray);
                RR_special.setBackgroundResource(R.color.gray);
                RR_cooking.setBackgroundResource(R.color.gray);*/

                fragment = new HomeFragment();

                loadFragment(fragment);

            }
        });

        RR_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   txt_categories.setTextColor(getResources().getColor(R.color.darkgray));
                txt_order.setTextColor(getResources().getColor(R.color.white));
                txt_special.setTextColor(getResources().getColor(R.color.darkgray));
                txt_cooking.setTextColor(getResources().getColor(R.color.darkgray));

                RR_home.setBackgroundResource(R.color.gray);
                RR_order.setBackgroundResource(R.color.red);
                RR_special.setBackgroundResource(R.color.gray);
                RR_cooking.setBackgroundResource(R.color.gray);*/

               /* fragment = new OrderHistoryFragmentOneMain();
                loadFragment(fragment);  */

                fragment = new TrackOrderFragment();
                loadFragment(fragment);

                //loadFragment(new OrderHistoryFragmentOneMain());

            }
        });

        RR_special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              /*  txt_categories.setTextColor(getResources().getColor(R.color.darkgray));
                txt_order.setTextColor(getResources().getColor(R.color.darkgray));
                txt_special.setTextColor(getResources().getColor(R.color.white));
                txt_cooking.setTextColor(getResources().getColor(R.color.darkgray));


                RR_home.setBackgroundResource(R.color.gray);
                RR_order.setBackgroundResource(R.color.gray);
                RR_special.setBackgroundResource(R.color.red);
                RR_cooking.setBackgroundResource(R.color.gray);*/

                fragment = new OrderHistoryFragmentOneMain();
                loadFragment(fragment);

            }
        });
        RR_cooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   txt_categories.setTextColor(getResources().getColor(R.color.darkgray));
                txt_order.setTextColor(getResources().getColor(R.color.darkgray));
                txt_special.setTextColor(getResources().getColor(R.color.darkgray));
                txt_cooking.setTextColor(getResources().getColor(R.color.white));

                RR_home.setBackgroundResource(R.color.gray);
                RR_order.setBackgroundResource(R.color.gray);
                RR_special.setBackgroundResource(R.color.gray);
                RR_cooking.setBackgroundResource(R.color.red);*/

                fragment = new OrderBookingFragment();
                loadFragment(fragment);

            }
        });

        RR_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new OrderHistoryFragmentOneMain();
                loadFragment(fragment);

            }
        });

        txt_title_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new AddAdressFragment();
                loadFragment(fragment);

            }
        });

        loadFragment(new HomeFragment());

    }


    private void findview() {

        RR_home=(RelativeLayout) findViewById(R.id.RR_home);
        RR_profile=(RelativeLayout) findViewById(R.id.RR_profile);
        RR_toolbar=(RelativeLayout) findViewById(R.id.RR_toolbar);
        RR_back=(RelativeLayout) findViewById(R.id.RR_back);
        RR_order=(RelativeLayout) findViewById(R.id.RR_order);
        RR_special=(RelativeLayout) findViewById(R.id.RR_special);
        RR_cooking=(RelativeLayout) findViewById(R.id.RR_cooking);
        txt_title=(TextView) findViewById(R.id.txt_title);
        txt_title_address=(TextView) findViewById(R.id.txt_title_address);
        txt_title_delivery=(TextView) findViewById(R.id.txt_title_delivery);
        txt_categories=(TextView) findViewById(R.id.txt_categories);
        txt_order=(TextView) findViewById(R.id.txt_order);
        txt_special=(TextView) findViewById(R.id.txt_special);
        txt_cooking=(TextView) findViewById(R.id.txt_cooking);

        img_home=(ImageView) findViewById(R.id.img_home);

        RR_wrong_address=(RelativeLayout) findViewById(R.id.RR_wrong_address);

    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack("home");
        transaction.commit();
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

/*
        txt_categories.setTextColor(getResources().getColor(R.color.white));
        txt_order.setTextColor(getResources().getColor(R.color.darkgray));
        txt_special.setTextColor(getResources().getColor(R.color.darkgray));
        txt_cooking.setTextColor(getResources().getColor(R.color.darkgray));

        RR_home.setBackgroundResource(R.color.red);
        RR_order.setBackgroundResource(R.color.gray);
        RR_special.setBackgroundResource(R.color.gray);
        RR_cooking.setBackgroundResource(R.color.gray);*/


        fragment = new HomeFragment();
        loadFragment(fragment);
        // img_home.setImageResource(R.drawable.home_icon);
        //   Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


}
