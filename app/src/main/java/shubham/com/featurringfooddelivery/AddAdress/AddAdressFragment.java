package shubham.com.featurringfooddelivery.AddAdress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import me.yokeyword.swipebackfragment.SwipeBackFragment;
import shubham.com.featurringfooddelivery.AddAdress.ApiModel.NewAddressDataModel;
import shubham.com.featurringfooddelivery.AddAdress.ApiModel.NewAddressModel;
import shubham.com.featurringfooddelivery.HomeFragment.HomeFragmentCategory;
import shubham.com.featurringfooddelivery.HomeFragment.HomeRecyclerViewAdapter;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeCategoryDataModelslider;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeCategoryModel;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class AddAdressFragment extends SwipeBackFragment implements IApiResponse{

    View rootView;

    String Address="";
    Button addAdress_btn;

    public RecyclerView recyclerView;
    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;
    private RecyclerViewAdapter_SelectAddress mAdapter;
    ProgressBar progressbar;
    private ArrayList<NewAddressDataModel> modelList = new ArrayList<>();

    public static AddAdressFragment newInstance(String param1, String param2) {
        AddAdressFragment fragment = new AddAdressFragment();
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
        rootView = inflater.inflate(R.layout.activity_add_adress, container, false);

        findViews();

        addAdress_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),AddAdressActivity.class);
                startActivity(intent);
            }
        });

        Address = Preference.get(getActivity(),Preference.KEY_Address);

        // setAdapter();

        progressbar.setVisibility(View.VISIBLE);

        AddAddressMethod();

        return attachToSwipeBack(rootView);
    }

    private void findViews() {

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_new);
        addAdress_btn = (Button) rootView.findViewById(R.id.addAdress_btn);
        progressbar = (ProgressBar) rootView.findViewById(R.id.progressbar);

    }
    private void setAdapter(ArrayList<NewAddressDataModel> modelList) {

        for (int i = 0; i < this.modelList.size(); i++) {

            this.modelList.get(i).setSelected(false);
        }

        // this.modelList.add(new AbstractModel_add_address(Address, "Hello " + " Android"));

        mAdapter = new RecyclerViewAdapter_SelectAddress(getActivity(), this.modelList);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new RecyclerViewAdapter_SelectAddress.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, NewAddressDataModel model) {

               /* Preference.save(getActivity(),Preference.KEY_Address,model.getAddressDetails());
                HomeBottomActivity.txt_title_address.setText(model.getAddressDetails());
               Toast.makeText(getActivity(), ""+model.getAddressDetails(), Toast.LENGTH_SHORT).show();*/
            }
        });

    }


    public void AddAddressMethod(){

        String User_Id = Preference.get(getActivity(),Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",User_Id);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_GetAddress, Constants.USER_GetAddress,map, Request.Method.POST);
    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_GetAddress.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                NewAddressModel finalArray = new Gson().fromJson(response,new TypeToken<NewAddressModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    progressbar.setVisibility(View.GONE);

                 //   Toast.makeText(getActivity(), "llm", Toast.LENGTH_SHORT).show();

                    modelList= (ArrayList<NewAddressDataModel>) finalArray.getAddress();

                    setAdapter(modelList);

                }
                else {

                    progressbar.setVisibility(View.GONE);

                 //   Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
