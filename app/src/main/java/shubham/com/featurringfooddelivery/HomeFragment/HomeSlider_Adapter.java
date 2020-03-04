package shubham.com.featurringfooddelivery.HomeFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import shubham.com.featurringfooddelivery.DeliveryOrder.DeliceryOrderFragment;
import shubham.com.featurringfooddelivery.DeliveryOrder.OnSwipeTouchListener;
import shubham.com.featurringfooddelivery.DeliveryOrderOne.DeliceryOrderFragmentOne;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.GetLikeContModel;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.GetLikeCountByIdModel;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.GetVideioContModel;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeCategoryDataModelslider;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeCategoryModel;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeDataModelslider;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.HomeModelslider;
import shubham.com.featurringfooddelivery.HomeFragment.apimodel.addVideoContModel;
import shubham.com.featurringfooddelivery.MainActivity;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;
import shubham.com.featurringfooddelivery.YoutubeVideoPlay.VideoPlayerActivityYoutube;


public class HomeSlider_Adapter extends PagerAdapter implements IApiResponse {

    List<HomeDataModelslider> image;
    private LayoutInflater inflater;
    private Context context;
    boolean isLike=false;
    boolean isFav=false;
    TextView txt_video_count;
    TextView txt_like_count;
    public ImageView img_like;
    String checkType;

    public HomeSlider_Adapter(Context context, List<HomeDataModelslider> IMAGES,String checkType) {
        this.context = context;
        this.checkType = checkType;
        this.image=IMAGES;
        inflater = LayoutInflater.from(context);
        getLikeCountById();
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public int getCount() {
        return image.size();
    }
    @Override
    public Object instantiateItem(ViewGroup view, final int position) {

        View imageLayout = inflater.inflate(R.layout.activity_home, view, false);

        assert imageLayout != null;

        getVideoCountMethod();

        getLikeCountMethod();

        final ImageView play = (ImageView) imageLayout
                .findViewById(R.id.play);

        final ImageView img_free_delivery = (ImageView) imageLayout
                .findViewById(R.id.img_free_delivery);

        final RelativeLayout RR_share = (RelativeLayout) imageLayout
                .findViewById(R.id.RR_share);

        final RelativeLayout RR_saveFor = (RelativeLayout) imageLayout
                .findViewById(R.id.RR_saveFor);

        final ImageView thumbnail = (ImageView) imageLayout
                .findViewById(R.id.thumbnail);
        final TextView txt_review = (TextView) imageLayout
                .findViewById(R.id.txt_review);

        txt_video_count = (TextView) imageLayout
                .findViewById(R.id.txt_video_count);

        txt_like_count = (TextView) imageLayout
                .findViewById(R.id.txt_like_count);

        TextView txt_price_one_final=(TextView) imageLayout.findViewById(R.id.txt_price_one_final);
        TextView txt_free_dervery=(TextView) imageLayout.findViewById(R.id.txt_free_dervery);
        TextView txt_time=(TextView) imageLayout.findViewById(R.id.txt_time);
        TextView txt_Name=(TextView) imageLayout.findViewById(R.id.txt_Name);


        TextView txt_one=(TextView) imageLayout.findViewById(R.id.txt_one);
        TextView txt_Two=(TextView) imageLayout.findViewById(R.id.txt_Two);
        TextView txt_Three=(TextView) imageLayout.findViewById(R.id.txt_Three);
      ;

        ImageView imgOne=(ImageView) imageLayout.findViewById(R.id.imgOne);
        ImageView imgTwo=(ImageView) imageLayout.findViewById(R.id.imgTwo);
        ImageView imgThree=(ImageView) imageLayout.findViewById(R.id.imgThree);


        TextView txt_like_count=(TextView) imageLayout.findViewById(R.id.txt_like_count);
        img_like=(ImageView) imageLayout.findViewById(R.id.img_like);
        final ImageView   img_save=(ImageView) imageLayout.findViewById(R.id.img_save);
        RelativeLayout RR_img_like=(RelativeLayout) imageLayout.findViewById(R.id.RR_img_like);


        RR_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "You can get multiple services at your door step. Install featurring app now.");
                context.startActivity(Intent.createChooser(sharingIntent, "Featurring App"));

            }
        });

        RR_saveFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isFav)
                {
                  //  img_save.setImageResource(R.drawable.favorites_icon);
                    img_save.setColorFilter(ContextCompat.getColor(context, R.color.Gray));
                    isFav=false;

                }else {
                    img_save.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary));
                   // img_save.setImageResource(R.drawable.fav_red);

                    isFav=true;
                }


            }
        });

        RR_img_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isLike)
                {
                 //   img_like.setImageResource(R.drawable.like_img_one);

                    img_like.setColorFilter(ContextCompat.getColor(context, R.color.Gray));

                    isLike=false;
                    removeLikeCountById();

                }else {

                    img_like.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary));

                   // img_like.setImageResource(R.drawable.img_like_red);

                    AddLikeCountMethod();

                    isLike=true;
                }

            }
        });






        RelativeLayout RR_Details=(RelativeLayout) imageLayout.findViewById(R.id.RR_Details);


        RR_Details.setOnTouchListener(new OnSwipeTouchListener(context) {
            public void onSwipeTop() {
                Toast.makeText(context, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(context, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(context, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(context, "bottom", Toast.LENGTH_SHORT).show();
            }

        });

        txt_price_one_final.setText("$"+image.get(position).getPrice());
        txt_review.setText(image.get(position).getReviews()+" Review");
        txt_free_dervery.setText(image.get(position).getDeliveryTitle());
        txt_time.setText(image.get(position).getTime());
        txt_Name.setText(image.get(position).getProductName());

        final RelativeLayout RR_one = (RelativeLayout) imageLayout
                .findViewById(R.id.RR_one);

        final ImageView img_review = (ImageView) imageLayout
                .findViewById(R.id.img_free_dervery_one);

        final RelativeLayout RR_two = (RelativeLayout) imageLayout
                .findViewById(R.id.RR_two);

        final RelativeLayout RR_three = (RelativeLayout) imageLayout
                .findViewById(R.id.RR_three);

        RR_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Preference.save(context,Preference.KEY_SubCategory_ID,image.get(position).getSubCategoryId());
                Preference.save(context,Preference.KEY_ITEM_ID,image.get(position).getItemId());

                Fragment someFragment = new DeliceryOrderFragmentOne();
                FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, someFragment );
                transaction.addToBackStack("DetailScreen");  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        RR_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Preference.save(context,Preference.KEY_SubCategory_ID,image.get(position).getSubCategoryId());
                Preference.save(context,Preference.KEY_ITEM_ID,image.get(position).getItemId());

                Fragment someFragment = new DeliceryOrderFragmentOne();
                FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, someFragment );
                transaction.addToBackStack("DetailScreen");  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });


        RR_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Preference.save(context,Preference.KEY_SubCategory_ID,image.get(position).getSubCategoryId());
                Preference.save(context,Preference.KEY_ITEM_ID,image.get(position).getItemId());

                Fragment someFragment = new DeliceryOrderFragmentOne();
                FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, someFragment );
                transaction.addToBackStack("DetailScreen");  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });


        if(checkType.equalsIgnoreCase("0")){

           // Toast.makeText(context, "Maid", Toast.LENGTH_SHORT).show();
            imgOne.setImageResource(R.drawable.maid_one);
            imgTwo.setImageResource(R.drawable.maid_two);
            imgThree.setImageResource(R.drawable.maid_three);
            txt_one.setText("1 Hour Cleaning");
            txt_Two.setText("2 Hours Cleaning");
            txt_Three.setText("3 Hours Cleaning");

        }else if(checkType.equalsIgnoreCase("1")){
           // Toast.makeText(context, "car", Toast.LENGTH_SHORT).show();
            imgOne.setImageResource(R.drawable.carwash_one);
            imgTwo.setImageResource(R.drawable.carwash_two);
            imgThree.setImageResource(R.drawable.carwash_three);
            txt_one.setText("Sparkling Clean Wash");
            txt_Two.setText("Sparkling w/Wax");
            txt_Three.setText("Sparkling w/Wax & Vacuum");

        }else if(checkType.equalsIgnoreCase("2")){
          //  Toast.makeText(context, "handyman", Toast.LENGTH_SHORT).show();
            txt_one.setText("1 Hour");
            txt_Two.setText("4 Hours");
            txt_Three.setText("8 Hours");
            imgOne.setImageResource(R.drawable.handyman_one);
            imgTwo.setImageResource(R.drawable.handyman_two);
            imgThree.setImageResource(R.drawable.handyman_three);

        }else if(checkType.equalsIgnoreCase("3")){
           // Toast.makeText(context, "food", Toast.LENGTH_SHORT).show();

            RR_three.setVisibility(View.GONE);

        }else if(checkType.equalsIgnoreCase("4")){

           // Toast.makeText(context, "massage", Toast.LENGTH_SHORT).show();
            txt_one.setText("30 Minute Massage");
            txt_Two.setText("60 Minute Massage");
            txt_Three.setText("90 Minute Massage");

            imgOne.setImageResource(R.drawable.massage_one);
            imgTwo.setImageResource(R.drawable.massage_two);
            imgThree.setImageResource(R.drawable.massage_three);

        }




        img_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button bttn_save,bttn_cancel;
                final RatingBar ratingbar;
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();
                dialog.setContentView(R.layout.review_dialog);
                bttn_save=dialog.findViewById(R.id.bttn_save);
                ratingbar=(RatingBar)dialog.findViewById(R.id.ratingbar);


                /*ratingbar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        RatingBar bar = (RatingBar) view;
                      String value = String.valueOf(bar.getRating());
                     //   addRatingApi(value);
                        Toast.makeText(context, ""+value, Toast.LENGTH_SHORT).show();
                    }
                });*/

                bttn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        float rating = ratingbar.getRating();
                        addRatingApi(String.valueOf(rating));
                      //  Toast.makeText(context, ""+rating, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });



                bttn_cancel=dialog.findViewById(R.id.bttn_cancel);
                bttn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });


            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                videoCountMethod();

                String adUrl = null;
                String moreInfo = null;
                String adName = null;

                Intent i = new Intent(context, VideoPlayerActivityYoutube.class);

                String isVimeo =  image.get(position).getIsVimeo();

                if(isVimeo.equalsIgnoreCase("1")) {

                    i.putExtra("vimeo", image.get(position).getVideourl());

                    i.putExtra("webview", "");

                }else {
                    i.putExtra("vimeo", "");
                    i.putExtra("webview", image.get(position).getVideourl());
                }

                context.startActivity(i);



            }
        });


   /* Picasso.with(context).load(image.get(position).getImage())
                    .placeholder(R.drawable.walpaper_icon)
                    .into(img_free_delivery);*/

        Picasso.with(context).load(image.get(position).getProductImage())
                .placeholder(R.drawable.no_image_icon)
                .into(thumbnail);

        // thumbnail.setImageResource(image.get(position).get());

        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


    private String getYouTubeId (String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }

    public void getVideoCountMethod(){

        String Categories_id =  Preference.get(context,Preference.KEY_CategoryId);

        HashMap<String, String> map = new HashMap<>();

        map.put("Categories_id",Categories_id);

        ApiRequest apiRequest = new ApiRequest(context,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_getvideoCount, Constants.USER_getvideoCount,map, Request.Method.POST);
    }


    public void videoCountMethod(){

        String user_id = Preference.get(context,Preference.KEY_USER_ID);

        String Categories_id =  Preference.get(context,Preference.KEY_CategoryId);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",user_id);

        map.put("Categories_id",Categories_id);

        ApiRequest apiRequest = new ApiRequest(context,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_AddVideoCount, Constants.USER_AddVideoCount,map, Request.Method.POST);
    }

    public void getLikeCountMethod(){

        String Categories_id =  Preference.get(context,Preference.KEY_CategoryId);

        HashMap<String, String> map = new HashMap<>();

        map.put("Categories_id",Categories_id);

        ApiRequest apiRequest = new ApiRequest(context,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_getLikeCount, Constants.USER_getLikeCount,map, Request.Method.POST);
    }

    public void AddLikeCountMethod(){

        String user_id = Preference.get(context,Preference.KEY_USER_ID);

        String Categories_id =  Preference.get(context,Preference.KEY_CategoryId);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",user_id);

        map.put("Categories_id",Categories_id);

        ApiRequest apiRequest = new ApiRequest(context,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_AddLike, Constants.USER_AddLike,map, Request.Method.POST);
    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_AddVideoCount.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                addVideoContModel finalArray = new Gson().fromJson(response,new TypeToken<addVideoContModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    //   Toast.makeText(context, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    getVideoCountMethod();

                }
                else {

                    Toast.makeText(context, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        } if (Constants.USER_AddLike.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                addVideoContModel finalArray = new Gson().fromJson(response,new TypeToken<addVideoContModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    //  Toast.makeText(context, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    getLikeCountMethod();

                }
                else {

                    Toast.makeText(context, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }else if (Constants.USER_removeLikeById.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                addVideoContModel finalArray = new Gson().fromJson(response,new TypeToken<addVideoContModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    //  Toast.makeText(context, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    getLikeCountMethod();

                }
                else {

                    Toast.makeText(context, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }


        else if (Constants.USER_getvideoCount.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                GetVideioContModel finalArray = new Gson().fromJson(response,new TypeToken<GetVideioContModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    String videoCount= finalArray.getVideoCount().getVideoCount();

                    txt_video_count.setText(videoCount);
                }
                else {

                    Toast.makeText(context, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }else if (Constants.USER_getLikeCount.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                GetLikeContModel finalArray = new Gson().fromJson(response,new TypeToken<GetLikeContModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    String videoCount= finalArray.getLikeCount().getLikeCount();

                    txt_like_count.setText(videoCount);

                }
                else {

                    Toast.makeText(context, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }else if (Constants.USER_getLikeCountById.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                GetLikeCountByIdModel finalArray = new Gson().fromJson(response,new TypeToken<GetLikeCountByIdModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    String isLiked= finalArray.getIsLiked().getIsLike();


                    if (isLiked.equalsIgnoreCase("0"))
                    {
                        img_like.setImageResource(R.drawable.like_img_one);

                        isLike=false;

                    }else {

                        img_like.setImageResource(R.drawable.img_like_red);

                        isLike=true;
                    }

                }
                else {

                    Toast.makeText(context, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }else if (Constants.USER_addReview.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                GetLikeCountByIdModel finalArray = new Gson().fromJson(response,new TypeToken<GetLikeCountByIdModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){

                    Toast.makeText(context, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(context, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(context, "Please Network Check", Toast.LENGTH_SHORT).show();
    }



    public void getLikeCountById(){

        String Categories_id =  Preference.get(context,Preference.KEY_CategoryId);
        String user_id =  Preference.get(context,Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("Categories_id",Categories_id);
        map.put("user_id",user_id);

        ApiRequest apiRequest = new ApiRequest(context,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_getLikeCountById, Constants.USER_getLikeCountById,map, Request.Method.POST);
    }

    public void removeLikeCountById(){

        String Categories_id =  Preference.get(context,Preference.KEY_CategoryId);
        String user_id =  Preference.get(context,Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("Categories_id",Categories_id);
        map.put("user_id",user_id);

        ApiRequest apiRequest = new ApiRequest(context,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_removeLikeById, Constants.USER_removeLikeById,map, Request.Method.POST);
    }



    public void addRatingApi(String reviews){

        String Categories_id =  Preference.get(context,Preference.KEY_CategoryId);
        String user_id =  Preference.get(context,Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("Categories_id",Categories_id);
        map.put("user_id",user_id);
        map.put("reviews",reviews);

        ApiRequest apiRequest = new ApiRequest(context,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_addReview, Constants.USER_addReview,map, Request.Method.POST);
    }

}
