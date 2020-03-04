package shubham.com.featurringfooddelivery.HomeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.yokeyword.swipebackfragment.SwipeBackFragment;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeDataModelslider;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeModelslider;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class HomeFragmentCategory extends SwipeBackFragment implements IApiResponse {


    View rootView;
    ViewPager viewPager;

    private HomeRecyclerViewAdapter mAdapter;
    List<HomeDataModelslider> image_list;
    ProgressBar progressbar;
    View view;
    TextView txt_review;
    String  checkType;
    //  private HomeRecyclerViewAdapter mAdapter;
   private ArrayList<HomeModelslider> modelList = new ArrayList<>();

    public static HomeFragmentCategory newInstance(String param1, String param2) {
        HomeFragmentCategory fragment = new HomeFragmentCategory();
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
        rootView = inflater.inflate(R.layout.activity_home_fragment_category, container, false);

        if (getArguments() != null) {

             checkType = getArguments().getString("checkType");

        }

        HomeBottomActivity.txt_title.setText("HOME");

        findViews();

       // viewPager.setRotation(90);

        progressbar.setVisibility(View.VISIBLE);

        String itemId =  Preference.get(getActivity(),Preference.KEY_ITEM_ID);

        homeMethod(itemId);

        return attachToSwipeBack(rootView);
    }

    private void findViews() {
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        progressbar = (ProgressBar) rootView.findViewById(R.id.progressbar);
    }


    public void homeMethod(String SubCategoryId){

        HashMap<String, String> map = new HashMap<>();

        map.put("SubCategoryId",SubCategoryId);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.GetHome, Constants.GetHome,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.GetHome.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                HomeModelslider finalArray = new Gson().fromJson(response,new TypeToken<HomeModelslider>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    progressbar.setVisibility(View.GONE);

                   image_list=finalArray.getCategoryList();

                    viewPager.setAdapter(new HomeSlider_Adapter(getActivity(), image_list, checkType));

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
