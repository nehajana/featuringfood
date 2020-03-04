package shubham.com.featurringfooddelivery.AddAdress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import shubham.com.featurringfooddelivery.R;

import android.widget.Toast;
import android.os.Handler;


public class AddAdress extends AppCompatActivity {

    private RecyclerView recyclerView;
    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;
    private RecyclerViewAdapter_SelectAddress mAdapter;
    private ArrayList<AbstractModel_add_address> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);

        // ButterKnife.bind(this);
        findViews();

        setAdapter();


    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }


    private void setAdapter() {

        for (int i = 0; i < this.modelList.size(); i++) {
            this.modelList.get(i).setSelected(false);
        }

      //  modelList.add(new AbstractModel_add_address("Android", "Hello " + " Android"));

      //  mAdapter = new RecyclerViewAdapter_SelectAddress(AddAdress.this, modelList);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

      /*  mAdapter.SetOnItemClickListener(new RecyclerViewAdapter_SelectAddress.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, AbstractModel_add_address model) {

                //handle item click events here
                Toast.makeText(AddAdress.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });*/


    }


}
