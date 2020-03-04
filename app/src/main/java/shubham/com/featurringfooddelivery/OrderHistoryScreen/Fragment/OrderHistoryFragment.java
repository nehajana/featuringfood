package shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import me.yokeyword.swipebackfragment.SwipeBackFragment;
import shubham.com.featurringfooddelivery.CountryDatamodel;
import shubham.com.featurringfooddelivery.CountryNamemodel;
import shubham.com.featurringfooddelivery.CustomAdapter;
import shubham.com.featurringfooddelivery.OrderHistoryScreen.OrderHIstoryAbstractModel;
import shubham.com.featurringfooddelivery.OrderHistoryScreen.OrderHistoryRecycleViewAdapter;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class OrderHistoryFragment extends SwipeBackFragment implements IApiResponse {

    public OrderHistoryFragment() {
        // Required empty public constructor
    }

    View rootview;
    private RecyclerView recycler_History;
    private OrderHistoryRecycleViewAdapter mAdapter;

    private ArrayList<PurchesHistoryDataModel> modelList_purches = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootview=inflater.inflate( R.layout.frag_order_history, container, false);

        findViews();

        purchesHistoryMethod();

        return attachToSwipeBack(rootview);
    }

    private void findViews() {
        recycler_History = (RecyclerView) rootview.findViewById( R.id.recycler_History );
    }

    private void setAdapter(ArrayList<PurchesHistoryDataModel> modelList_purches) {

        mAdapter = new OrderHistoryRecycleViewAdapter( getActivity(), modelList_purches );
        recycler_History.setHasFixedSize( true );
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager( getActivity() );
        recycler_History.setLayoutManager( layoutManager );
        recycler_History.setAdapter( mAdapter );

        mAdapter.SetOnItemClickListener( new OrderHistoryRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, PurchesHistoryDataModel model) {

            }
        } );

    }

    public void purchesHistoryMethod(){
        String User_Id = Preference.get(getActivity(),Preference.KEY_USER_ID);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id_FK",User_Id);
        ApiRequest apiRequest = new ApiRequest(getActivity(),this);
        apiRequest.postRequest(Constants.BASE_URL + Constants.User_PurchaseHistoryt, Constants.User_PurchaseHistoryt,map, Request.Method.POST);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.User_PurchaseHistoryt.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                PurchesHistoryModel finalArray = new Gson().fromJson(response,new TypeToken<PurchesHistoryModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if(status.equalsIgnoreCase("success"))
                {
                    //Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    modelList_purches = (ArrayList<PurchesHistoryDataModel>) finalArray.getPurchaseHistory();

                    setAdapter(modelList_purches);

                }
                else
                {
                   // Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }


    }

    @Override
    public void onErrorResponse(VolleyError error) {



    }
}
