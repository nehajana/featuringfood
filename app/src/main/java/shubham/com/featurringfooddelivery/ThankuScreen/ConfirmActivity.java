package shubham.com.featurringfooddelivery.ThankuScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.R;

public class ConfirmActivity extends AppCompatActivity {

    TextView txt_order_id;
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        txt_order_id=(TextView) findViewById(R.id.txt_order_id);
        btn_confirm=(Button) findViewById(R.id.btn_confirm);

        Intent intent = getIntent();
        String orderid = intent.getStringExtra("orderid").toString();
        txt_order_id.setText(orderid);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(ConfirmActivity.this, HomeBottomActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1=new Intent(ConfirmActivity.this, HomeBottomActivity.class);
        startActivity(intent1);
        finish();
    }
}
