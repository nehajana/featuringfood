package shubham.com.featurringfooddelivery.HomeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import shubham.com.featurringfooddelivery.DeliveryOrder.DeliceryOrderFragment;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeCategoryDataModelslider;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeCategoryModel;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeModelslider;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.MainActivity;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class


HomeFragment extends Fragment implements IApiResponse {

    View rootView;
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter mAdapter;
    private ArrayList<HomeCategoryDataModelslider> modelList = new ArrayList<>();
    ProgressBar progressbar;

    String[] zipCodeArray = new String[] { "90001","90002","90003","90004","90005","90006","90007","90008","90009","90010","90011","90012","90013","90014","90015","90016","90017","90018","90019","90020","90021","90022","90023","90024","90025","90026","90027","90028","90029","90030","90031","90032","90033","90034","90035","90036","90037","90038","90039","90040","90041","90042","90043","90044","90045","90046","90047","90048","90049","90050","90051","90052","90053","90054","90055","90056","90057","90058","90059","90060","90061","90062","90063","90064","90065","90066","90067","90068","90069","90070","90071","90072","90073","90075","90076","90077","90078","90079","90080","90081","90082","90083","90086","90087","90089","90091","90093","90094","90095","90099","90201","90202","90209","90210","90211","90212","90213","90220","90221","90222","90223","90224","90230","90231","90232","90233","90239","90240","90241","90242","90245","90247","90248","90249","90250","90251","90254","90255","90260","90261","90262","90263","90264","90265","90266","90267","90270","90272","90274","90275","90277","90278","90280","90290","90291","90292","90293","90294","90295","90296","90301","90302","90303","90304","90305","90306","90307","90308","90309","90310","90311","90312","90401","90402","90403","90404","90405","90406","90407","90408","90409","90410","90411","90501","90502","90503","90504","90505","90506","90507","90508","90509","90510","90601","90602","90603","90604","90605","90606","90607","90608","90609","90610","90623","90630","90631","90637","90638","90639","90640","90650","90651","90651","90652","90660","90661","90662","90670","90671","90701","90702","90703","90704","90706","90707","90710","90711","90712","90713","90714","90715","90716","90717","90723","90731","90732","90733","90734","90744","90745","90746","90747","90748","90749","90755","90801","90802","90803","90804","90805","90806","90807","90808","90809","90810","90813","90814","90815","90822","90831","90832","90833","90834","90835","90840","90846","90853","91001","91003","91006","91007","91008","91009","91010","91011","91012","91016","91017","91020","91021","91023","91024","91025","91030","91031","91040","91041","91042","91043","91046","91066","91077","91101","91102","91103","91104","91105","91106","91107","91108","91109","91114","91115","91116","91117","91118","91125","91125","91126","91126","91201","91202","91203","91204","91205","91206","91207","91208","91209","91210","91214","91221","91222","91224","91225","91226","91301","91302","91303","91304","91305","91306","91307","91308","91309","91310","91311","91313","91316","91321","91322","91324","91325","91326","91327","91328","91330","91330","91331","91331","91333","91334","91335","91337","91340","91341","91342","91343","91344","91345","91346","91350","91351","91352","91353","91354","91355","91356","91357","91361","91362","91364","91365","91367","91372","91376","91380","91381","91382","91383","91384","91385","91386","91387","91390","91392","91393","91394","91395","91396","91401","91402","91403","91404","91405","91406","91407","91408","91409","91410","91411","91412","91413","91416","91423","91426","91436","91501","91502","91503","91504","91505","91506","91507","91508","91510","91521","91522","91523","91601","91602","91603","91604","91605","91606","91607","91608","91609","91610","91614","91615","91616","91617","91618","91702","91706","91709","91710","91711","91714","91715","91716","91722","91723","91724","91731","91732","91733","91734","91740","91741","91744","91745","91746","91747","91748","91749","91750","91754","91755","91759","91765","91766","91767","91768","91769","91770","91773","91775","91776","91778","91780","91788","91789","91790","91791","91792","91793","91801","91802","91803","91804","91896","91899","92397","92821","92823","93243","93510","93523","93532","93534","93535","93536","93539","93543","93544","93550","93551","93552","93553","93560","93563","93584","93586","93590","93591"};
    List<String> zipCodelist = Arrays.asList(zipCodeArray);

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);

        findViews();

        progressbar.setVisibility(View.VISIBLE);

        String address=Preference.get(getActivity(),Preference.KEY_Address);
        String Zipcode=Preference.get(getActivity(),Preference.KEY_ZipCode);

         HomeBottomActivity.RR_toolbar.setVisibility(View.VISIBLE);
         HomeBottomActivity.txt_title.setVisibility(View.GONE);
         HomeBottomActivity.txt_title_delivery.setVisibility(View.VISIBLE);

         HomeBottomActivity.txt_title_address.setVisibility(View.VISIBLE);


      //  HomeBottomActivity.txt_title_address.setText(Zipcode+","+address);

        if(address.equalsIgnoreCase("0"))
        {
            HomeBottomActivity.txt_title_address.setText("No Data");

        }else
        {
            HomeBottomActivity.txt_title_address.setText(address);
        }


        //RR_wrong_address=(RelativeLayout) findViewById(R.id.RR_wrong_address);

        try {

            int ZipCode = Integer.parseInt(Preference.get(getActivity(),Preference.KEY_ZipCode));

            if (zipCodelist.contains(String.valueOf(ZipCode))) {
                // correct zipcode
                HomeBottomActivity.RR_wrong_address.setVisibility(View.GONE);

            } else {

                HomeBottomActivity.RR_wrong_address.setVisibility(View.VISIBLE);

            }
        }catch (Exception ex){

            ex.printStackTrace();
        }

        String Categoryid = Preference.get(getActivity(),Preference.KEY_Main_CategoryId);

        homeMethod(Categoryid);

         return  rootView;
    }

    private void findViews() {

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        progressbar = (ProgressBar) rootView.findViewById(R.id.progressbar);

    }

    private void setAdapter(ArrayList<HomeCategoryDataModelslider> modelList) {

        mAdapter = new HomeRecyclerViewAdapter(getActivity(), this.modelList);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new HomeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, HomeCategoryDataModelslider model) {
                //handle item click events here
                //Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
                Preference.save(getActivity(),Preference.KEY_ITEM_ID,model.getItemId());

                Fragment someFragment = new HomeFragmentCategory();
                FragmentTransaction transaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, someFragment );
                transaction.addToBackStack("DetailScreen");  // if written, this transaction will be added to backstack
                Bundle bundle = new Bundle();
                bundle.putString("checkType", "3");
                someFragment.setArguments(bundle);
                transaction.commit();
            }
        });
    }

    public void homeMethod(String Categoryid){

        HashMap<String, String> map = new HashMap<>();

        map.put("Categories_id","4");

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.GetHomeCategory, Constants.GetHomeCategory,map, Request.Method.POST);

    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.GetHomeCategory.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                HomeCategoryModel finalArray = new Gson().fromJson(response,new TypeToken<HomeCategoryModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    progressbar.setVisibility(View.GONE);

                    modelList= (ArrayList<HomeCategoryDataModelslider>) finalArray.getMainCategoryList();

                     setAdapter(modelList);

                }
                else {

                    progressbar.setVisibility(View.GONE);

                    Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
