package shubham.com.featurringfooddelivery.SpashScreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.MainActivity;
import shubham.com.featurringfooddelivery.R;

public class SpashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                    Intent intent = new Intent(SpashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();

            }
        }, SPLASH_TIME_OUT);
    }
}
