package shubham.com.featurringfooddelivery.DeliveryOrderOne;

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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.yokeyword.swipebackfragment.SwipeBackFragment;
import shubham.com.featurringfooddelivery.DeliveryOrderOne.ApiModel.AddCardModel;
import shubham.com.featurringfooddelivery.DeliveryOrderOne.ApiModel.SubHomeCategoryList;
import shubham.com.featurringfooddelivery.DeliveryOrderOne.ApiModel.SubHomeModerImage;
import shubham.com.featurringfooddelivery.HomeFragment.HomeSlider_Adapter;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeModelslider;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.LoginModel;
import shubham.com.featurringfooddelivery.MainActivity;
import shubham.com.featurringfooddelivery.OrderBooking.ApiModel.GetCartListDataModel;
import shubham.com.featurringfooddelivery.OrderBooking.OrderBookingFragment;
import shubham.com.featurringfooddelivery.OrderBooking.RecyclerViewAdapterPost;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;
import shubham.com.featurringfooddelivery.YoutubeVideoPlay.VideoPlayerActivityYoutube;

public class DeliceryOrderFragmentOne extends SwipeBackFragment implements IApiResponse {

    View rootView;
    RelativeLayout RR_one1;
    ImageView play;
    ImageView thumbnail;
    ImageView img_free_delivery;
    String VideoUrl="";
    TextView txt_name;
    RecyclerView recycler_ingredients;
    TextView txt_review;
    TextView txt_free_dervery;
    TextView txt_time;
    Button addmore_btn;
    RecyclerViewAdapterIngredients adapter_ingredents;
    TextView txt_price;

    ProgressBar progressbar;
    TextView txtNoRecord;
    ImageView img_back;

    public ArrayList<SubHomeModerImage> ingredients = new ArrayList<>();

    public static DeliceryOrderFragmentOne newInstance(String param1, String param2) {
        DeliceryOrderFragmentOne fragment = new DeliceryOrderFragmentOne();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    String subCategoryID,itemId;
    String user_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_delevery_food, container, false);

        subCategoryID = Preference.get(getActivity(),Preference.KEY_SubCategory_ID);
        itemId = Preference.get(getActivity(),Preference.KEY_ITEM_ID);

        user_id = Preference.get(getActivity(),Preference.KEY_USER_ID);



        findViews();

        HomeBottomActivity.txt_title.setText("HOME");

        progressbar.setVisibility(View.VISIBLE);

        recycler_ingredients.setNestedScrollingEnabled(false);
        // HomeBottomActivity.TexttitleName(getActivity(),"MY ACCOUNT");

        //   HomeBottomActivity.RR_toolbar.setVisibility(View.GONE);



        RR_one1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ID = "",ingredients_ID = "";

                for (int i = 0; i < ingredients.size(); i++) {
                    if(ingredients.get(i).getChecked()){
                        ID = ID + ingredients.get(i).getIngredientsId()+",";
                    }
                }

                if (ID.endsWith(",")) {
                    ingredients_ID = ID.substring(0, ID.length() - 1);
                }


               // addCardMethod(CategoryID,"1",user_id,ingredients_ID);
                addCardMethod(subCategoryID,"1",user_id,"1");

                Fragment someFragment = new OrderBookingFragment();
                FragmentTransaction transaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, someFragment );
                transaction.addToBackStack("DetailScreen");  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String adUrl = null;
                String moreInfo = null;
                String adName = null;

                Intent i = new Intent(getActivity(), VideoPlayerActivityYoutube.class);
                i.putExtra("vimeo","URL");
                i.putExtra("webview", "");
                startActivity(i);

            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        subhomeMethod(subCategoryID);

        return attachToSwipeBack(rootView);
    }

    private void findViews() {
        RR_one1=(RelativeLayout) rootView.findViewById(R.id.RR_one1);
        txt_review=(TextView) rootView.findViewById(R.id.txt_review);
        txt_time=(TextView) rootView.findViewById(R.id.txt_time);
        txt_free_dervery=(TextView) rootView.findViewById(R.id.txt_free_dervery);
        recycler_ingredients=(RecyclerView) rootView.findViewById(R.id.recycler_ingredients);
        play=(ImageView) rootView.findViewById(R.id.play);
        thumbnail=(ImageView) rootView.findViewById(R.id.thumbnail);
        img_free_delivery=(ImageView) rootView.findViewById(R.id.img_free_delivery);
        txt_name=(TextView) rootView.findViewById(R.id.txt_name);
        txt_price=(TextView) rootView.findViewById(R.id.txt_price);

        progressbar=(ProgressBar) rootView.findViewById(R.id.progressbar);
        txtNoRecord=(TextView) rootView.findViewById(R.id.txtNoRecord);
        img_back=(ImageView) rootView.findViewById(R.id.img_back);
    }

    private void setAdapterIngedents(final ArrayList<SubHomeModerImage> ingredients) {

        adapter_ingredents = new RecyclerViewAdapterIngredients(getActivity(), this.ingredients);
        recycler_ingredients.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_ingredients.setLayoutManager(layoutManager);
        recycler_ingredients.setAdapter(adapter_ingredents);

        adapter_ingredents.SetOnItemClickListener(new RecyclerViewAdapterIngredients.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, SubHomeModerImage model, CheckBox moreitem_check_name) {

               if(moreitem_check_name.isChecked()){
                   ingredients.get(position).setChecked(false);
                   moreitem_check_name.setChecked(false);
               }else{
                   ingredients.get(position).setChecked(true);
                   moreitem_check_name.setChecked(true);

               }
            }
        });
    }


    public void subhomeMethod(String CategoryID){

        HashMap<String, String> map = new HashMap<>();

        map.put("SubCategoryId",CategoryID);
        map.put("ItemId",itemId);
        ApiRequest apiRequest = new ApiRequest(getActivity(),this);
        apiRequest.postRequest(Constants.BASE_URL + Constants.Ingredients, Constants.Ingredients,map, Request.Method.POST);
    }

    public void addCardMethod(String ProductId,String Quantity,String user_id,String ingredients_id){

        HashMap<String, String> map = new HashMap<>();

        map.put("ItemId",ProductId);
        map.put("Quantity",Quantity);
        map.put("user_id",user_id);
        map.put("ingredients_id",ingredients_id);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.add_to_cart, Constants.add_to_cart,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {
        if (Constants.Ingredients.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                progressbar.setVisibility(View.GONE);

                SubHomeCategoryList finalArray = new Gson().fromJson(response,new TypeToken<SubHomeCategoryList>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    Picasso.with(getActivity()).load(finalArray.getCategoryList().getProductImage())
                            .placeholder(R.drawable.no_image_icon)
                            .into(thumbnail);

                    Picasso.with(getActivity()).load(finalArray.getCategoryList().getImage())
                            .placeholder(R.drawable.walpaper_icon)
                            .into(img_free_delivery);

                    txt_price.setText("Cook it & Bring it to Me $"+finalArray.getCategoryList().getPrice());

                    txt_review.setText(finalArray.getCategoryList().getReviews()+"Review");

                    txt_time.setText(finalArray.getCategoryList().getTime());

                    txt_free_dervery.setText(finalArray.getCategoryList().getDeliveryTitle());

                    ingredients= (ArrayList<SubHomeModerImage>) finalArray.getIngredients();

                    setAdapterIngedents(ingredients);

                    VideoUrl=finalArray.getCategoryList().getVideourl();

                    txt_name.setText(finalArray.getCategoryList().getProductName());

                }
                else {

                    Toast.makeText(getActivity(), finalArray.getStatus(), Toast.LENGTH_SHORT).show();

                }
            }
        }else  if (Constants.add_to_cart.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                AddCardModel finalArray = new Gson().fromJson(response,new TypeToken<AddCardModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();

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
