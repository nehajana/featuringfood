package shubham.com.featurringfooddelivery.DeliveryOrder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import shubham.com.featurringfooddelivery.DeliveryOrderOne.DeliceryOrderFragmentOne;
import shubham.com.featurringfooddelivery.HomeFragment.HomeAbstractModel;
import shubham.com.featurringfooddelivery.HomeFragment.HomeRecyclerViewAdapter;
import shubham.com.featurringfooddelivery.HomeFragment.HomeSlider_Adapter;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeModelslider;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class DeliceryOrderFragment extends Fragment implements IApiResponse {


    View rootView;
    RelativeLayout RR_one,RR_three;

    public static DeliceryOrderFragment newInstance(String param1, String param2) {

        DeliceryOrderFragment fragment = new DeliceryOrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //rootView = inflater.inflate(R.layout.activity_delevery_food, container, false);
        rootView = inflater.inflate(R.layout.activity_home, container, false);

        findViews();

        HomeBottomActivity.txt_title.setText("Details");

        HomeBottomActivity.RR_toolbar.setVisibility(View.VISIBLE);


        //  HomeBottomActivity.TexttitleName(getActivity(),"MY ACCOUNT");

     //   HomeBottomActivity.RR_toolbar.setVisibility(View.GONE);

        RR_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment someFragment = new DeliceryOrderFragmentOne();
                FragmentTransaction transaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, someFragment );
                transaction.addToBackStack("DetailScreen");  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        RR_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment someFragment = new DeliceryOrderFragmentOne();
                FragmentTransaction transaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, someFragment );
                transaction.addToBackStack("DetailScreen");  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        homeDetailsMethod("1");

        return  rootView;
    }

    private void findViews() {

        RR_one=(RelativeLayout) rootView.findViewById(R.id.RR_one);
        RR_three=(RelativeLayout) rootView.findViewById(R.id.RR_three);
    }

    public void homeDetailsMethod(String SubCategoryId){

        HashMap<String, String> map = new HashMap<>();

        map.put("SubCategoryId",SubCategoryId);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.GetDetails, Constants.GetDetails,map, Request.Method.GET);

    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.GetDetails.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                HomeModelslider finalArray = new Gson().fromJson(response,new TypeToken<HomeModelslider>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){


                // String ProductName = finalArray.getCategoryListDetails().getDeliveryTitle();

                // String ProductName = finalArray.getCategoryListDetails().getDeliveryTitle();

                }
                else {

                    Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }


}
