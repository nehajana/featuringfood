package shubham.com.featurringfooddelivery.OrderBooking;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import me.yokeyword.swipebackfragment.SwipeBackFragment;
import shubham.com.featurringfooddelivery.HomeFragment.HomeFragment;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.OrderBooking.ApiModel.CardModelList;
import shubham.com.featurringfooddelivery.OrderBooking.ApiModel.GetCartListDataModel;
import shubham.com.featurringfooddelivery.OrderBooking.ApiModel.TimeModel;
import shubham.com.featurringfooddelivery.OrderBooking.ApiModel.TimePeriedModel;
import shubham.com.featurringfooddelivery.PaymentDelever.paymentFragment;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class OrderBookingFragment extends SwipeBackFragment implements IApiResponse {

    String Ordertype = "0";
    View rootView;
    private RecyclerViewAdapterPost recyclerView_post;
    private RecyclerViewAdapter_ingredents recyclerView_ingredents;
    RecyclerView recycler_order;
    RecyclerView recycler_ingredients;
    Button Booking_payment;
    Button addmore_btn;
    Button order_RepeatOrder;
    TextView txt_time_change;
    TextView txt_custom;
    TextView txtfinance_amount;
    TextView txtemi_start_date;
    TextView txt_final_amt;
    TextView txt_cash_tip,txt_ten_percentage,txt_fiveteen_percentage,txt_twenty_percentage;

    Double TotalPrice=0.0;
    Double totolfinalPrice;
    Double finalPrice;
    TextView txt_empty;

    RelativeLayout RR_allItem;
    RelativeLayout RR_moreTime;
    RelativeLayout RR_delivery;

    TextView txt_emty;

    private ArrayList<GetCartListDataModel> modelList_post = new ArrayList<>();

    private RecyclerViewAdapterCooseTime adapter_chooseTime;
    RecyclerView recycler_Coosetime;
    private ArrayList<TimePeriedModel> modelList__chooseTime = new ArrayList<>();
    private ArrayList<TimePeriedModel> modelList__chooseTime_fourTime = new ArrayList<>();

    //Selected Day
    TextView txt_s,txt_m,txt_t,txt_w,txt_th,txt_fr,txt_st;

    boolean isFinalArray = false;

    ImageView img_repeat,img_oneTime;

    RelativeLayout RR_Repeat;
    RelativeLayout RR_oneTime;


    ProgressBar progressbar;

    public static OrderBookingFragment newInstance(String param1, String param2) {
        OrderBookingFragment fragment = new OrderBookingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //rootView = inflater.inflate(R.layout.activity_delevery_food, container, false);
        rootView = inflater.inflate(R.layout.activity_order_booking, container, false);

        HomeBottomActivity.txt_title.setText("YOUR ORDER");

        findViews();
       /* LocalTime localTime = LocalTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        System.out.println(localTime.format(dateTimeFormatter));
        Toast.makeText(getActivity(), ""+localTime.format(dateTimeFormatter), Toast.LENGTH_SHORT).show();*/
        recycler_order.setNestedScrollingEnabled(false);
        recycler_ingredients.setNestedScrollingEnabled(false);
        recycler_Coosetime.setNestedScrollingEnabled(false);

        txt_cash_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                totolfinalPrice=0.0;

                totolfinalPrice=TotalPrice;

                //    Booking_payment.setText("Continue to checkout : $"+totolfinalPrice);

                String value = String.format("%1$,.2f",totolfinalPrice);

                Booking_payment.setText("Continue to checkout : $"+value);


                txt_cash_tip.setBackgroundResource(R.drawable.rounded_red);
                txt_ten_percentage.setBackgroundResource(R.drawable.rounded);
                txt_fiveteen_percentage.setBackgroundResource(R.drawable.rounded);
                txt_twenty_percentage.setBackgroundResource(R.drawable.rounded);

                txt_cash_tip.setTextColor(Color.WHITE);
                txt_ten_percentage.setTextColor(Color.BLACK);
                txt_fiveteen_percentage.setTextColor(Color.BLACK);
                txt_twenty_percentage.setTextColor(Color.BLACK);


                Preference.saveFloat(getActivity(),Preference.KEY_amount, Float.valueOf(value));
            }
        });

        txt_ten_percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                totolfinalPrice=0.0;

                totolfinalPrice=(TotalPrice*10)/100;

                finalPrice= TotalPrice+totolfinalPrice;

                String value = String.format("%1$,.2f",finalPrice);

                Booking_payment.setText("Continue to checkout : $"+value);

                txt_cash_tip.setBackgroundResource(R.drawable.rounded);
                txt_ten_percentage.setBackgroundResource(R.drawable.rounded_red);
                txt_fiveteen_percentage.setBackgroundResource(R.drawable.rounded);
                txt_twenty_percentage.setBackgroundResource(R.drawable.rounded);

                txt_cash_tip.setTextColor(Color.BLACK);
                txt_ten_percentage.setTextColor(Color.WHITE);
                txt_fiveteen_percentage.setTextColor(Color.BLACK);
                txt_twenty_percentage.setTextColor(Color.BLACK);

                Preference.saveFloat(getActivity(),Preference.KEY_amount, Float.valueOf(value));

            }
        });
        txt_fiveteen_percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                totolfinalPrice=0.0;

                totolfinalPrice=(TotalPrice*15)/100;

                finalPrice= TotalPrice+totolfinalPrice;

                String value = String.format("%1$,.2f",finalPrice);

                Booking_payment.setText("Continue to checkout : $"+value);

                txt_cash_tip.setBackgroundResource(R.drawable.rounded);
                txt_ten_percentage.setBackgroundResource(R.drawable.rounded);
                txt_fiveteen_percentage.setBackgroundResource(R.drawable.rounded_red);
                txt_twenty_percentage.setBackgroundResource(R.drawable.rounded);

                txt_cash_tip.setTextColor(Color.BLACK);
                txt_ten_percentage.setTextColor(Color.BLACK);
                txt_fiveteen_percentage.setTextColor(Color.WHITE);
                txt_twenty_percentage.setTextColor(Color.BLACK);

                Preference.saveFloat(getActivity(),Preference.KEY_amount, Float.valueOf(value));

            }
        });

        txt_twenty_percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                totolfinalPrice=0.0;

                totolfinalPrice=(TotalPrice*20)/100;

                finalPrice= TotalPrice+totolfinalPrice;

                String value = String.format("%1$,.2f",finalPrice);

                Booking_payment.setText("Continue to checkout : $"+value);

                txt_cash_tip.setBackgroundResource(R.drawable.rounded);
                txt_ten_percentage.setBackgroundResource(R.drawable.rounded);
                txt_fiveteen_percentage.setBackgroundResource(R.drawable.rounded);
                txt_twenty_percentage.setBackgroundResource(R.drawable.rounded_red);

                txt_cash_tip.setTextColor(Color.BLACK);
                txt_ten_percentage.setTextColor(Color.BLACK);
                txt_fiveteen_percentage.setTextColor(Color.BLACK);
                txt_twenty_percentage.setTextColor(Color.WHITE);

                Preference.saveFloat(getActivity(),Preference.KEY_amount, Float.valueOf(value));

            }
        });

        //HomeBottomActivity.txt_title.setText("MY ACCOUNT");

        Booking_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //RR_wrong_address=(RelativeLayout) findViewById(R.id.RR_wrong_address);

                try {

                    int ZipCode = Integer.parseInt(Preference.get(getActivity(), Preference.KEY_ZipCode));

                    String[] zipCodeArray = new String[] { "90001","90002","90003","90004","90005","90006","90007","90008","90009","90010","90011","90012","90013","90014","90015","90016","90017","90018","90019","90020","90021","90022","90023","90024","90025","90026","90027","90028","90029","90030","90031","90032","90033","90034","90035","90036","90037","90038","90039","90040","90041","90042","90043","90044","90045","90046","90047","90048","90049","90050","90051","90052","90053","90054","90055","90056","90057","90058","90059","90060","90061","90062","90063","90064","90065","90066","90067","90068","90069","90070","90071","90072","90073","90075","90076","90077","90078","90079","90080","90081","90082","90083","90086","90087","90089","90091","90093","90094","90095","90099","90201","90202","90209","90210","90211","90212","90213","90220","90221","90222","90223","90224","90230","90231","90232","90233","90239","90240","90241","90242","90245","90247","90248","90249","90250","90251","90254","90255","90260","90261","90262","90263","90264","90265","90266","90267","90270","90272","90274","90275","90277","90278","90280","90290","90291","90292","90293","90294","90295","90296","90301","90302","90303","90304","90305","90306","90307","90308","90309","90310","90311","90312","90401","90402","90403","90404","90405","90406","90407","90408","90409","90410","90411","90501","90502","90503","90504","90505","90506","90507","90508","90509","90510","90601","90602","90603","90604","90605","90606","90607","90608","90609","90610","90623","90630","90631","90637","90638","90639","90640","90650","90651","90651","90652","90660","90661","90662","90670","90671","90701","90702","90703","90704","90706","90707","90710","90711","90712","90713","90714","90715","90716","90717","90723","90731","90732","90733","90734","90744","90745","90746","90747","90748","90749","90755","90801","90802","90803","90804","90805","90806","90807","90808","90809","90810","90813","90814","90815","90822","90831","90832","90833","90834","90835","90840","90846","90853","91001","91003","91006","91007","91008","91009","91010","91011","91012","91016","91017","91020","91021","91023","91024","91025","91030","91031","91040","91041","91042","91043","91046","91066","91077","91101","91102","91103","91104","91105","91106","91107","91108","91109","91114","91115","91116","91117","91118","91125","91125","91126","91126","91201","91202","91203","91204","91205","91206","91207","91208","91209","91210","91214","91221","91222","91224","91225","91226","91301","91302","91303","91304","91305","91306","91307","91308","91309","91310","91311","91313","91316","91321","91322","91324","91325","91326","91327","91328","91330","91330","91331","91331","91333","91334","91335","91337","91340","91341","91342","91343","91344","91345","91346","91350","91351","91352","91353","91354","91355","91356","91357","91361","91362","91364","91365","91367","91372","91376","91380","91381","91382","91383","91384","91385","91386","91387","91390","91392","91393","91394","91395","91396","91401","91402","91403","91404","91405","91406","91407","91408","91409","91410","91411","91412","91413","91416","91423","91426","91436","91501","91502","91503","91504","91505","91506","91507","91508","91510","91521","91522","91523","91601","91602","91603","91604","91605","91606","91607","91608","91609","91610","91614","91615","91616","91617","91618","91702","91706","91709","91710","91711","91714","91715","91716","91722","91723","91724","91731","91732","91733","91734","91740","91741","91744","91745","91746","91747","91748","91749","91750","91754","91755","91759","91765","91766","91767","91768","91769","91770","91773","91775","91776","91778","91780","91788","91789","91790","91791","91792","91793","91801","91802","91803","91804","91896","91899","92397","92821","92823","93243","93510","93523","93532","93534","93535","93536","93539","93543","93544","93550","93551","93552","93553","93560","93563","93584","93586","93590","93591"};
                    List<String> zipCodelist = Arrays.asList(zipCodeArray);

                    if (zipCodelist.contains(String.valueOf(ZipCode))) {
                    // correct zipcode
                        HomeBottomActivity.RR_wrong_address.setVisibility(View.GONE);

                        Fragment someFragment = new paymentFragment();
                        FragmentTransaction transaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_container, someFragment );
                        transaction.addToBackStack("DetailScreen");  // if written, this transaction will be added to backstack
                        transaction.commit();


                    } else {

                        Toast.makeText(_mActivity, "We do not offer our featurring services to your area at this time. We will contact you when we launch the services in your area.", Toast.LENGTH_SHORT).show();

                        HomeBottomActivity.RR_wrong_address.setVisibility(View.VISIBLE);

                    }
                }catch (Exception ex){

                    Toast.makeText(_mActivity, "We do not offer our featurring services to your area at this time. We will contact you when we launch the services in your area.", Toast.LENGTH_SHORT).show();

                    ex.printStackTrace();
                }

/*
                Fragment someFragment = new paymentFragment();
                FragmentTransaction transaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, someFragment );
                transaction.addToBackStack("DetailScreen");  // if written, this transaction will be added to backstack
                transaction.commit();*/


            }
        });

        addmore_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ordertype = "0";
                Preference.save(getActivity(),Preference.KEY_Ordertype,Ordertype);

                txt_s.setText("Sunday");
                txt_m.setText("Monday");
                txt_t.setText("Tuesday");
                txt_w.setText("Wednesday");
                txt_th.setText("Thursday");
                txt_fr.setText("Friday");
                txt_st.setText("Saturday");

                img_oneTime.setImageResource(R.drawable.radio_button_red);
                img_repeat.setImageResource(R.drawable.circle_grey);


            }
        });

        order_RepeatOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Ordertype = "1";
                Preference.save(getActivity(),Preference.KEY_Ordertype,Ordertype);

                img_oneTime.setImageResource(R.drawable.circle_grey);
                img_repeat.setImageResource(R.drawable.radio_button_red);

                txt_s.setText("Every \n Sunday");
                txt_m.setText("Every \n Monday");
                txt_t.setText("Every \n Tuesday");
                txt_w.setText("Every \n Wednesday");
                txt_th.setText("Every \n Thursday");
                txt_fr.setText("Every \n Friday");
                txt_st.setText("Every \n Saturday");

            }
        });

        img_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ordertype = "1";
                Preference.save(getActivity(),Preference.KEY_Ordertype,Ordertype);

                img_oneTime.setImageResource(R.drawable.circle_grey);
                img_repeat.setImageResource(R.drawable.radio_button_red);

                txt_s.setText("Every \n Sunday");
                txt_m.setText("Every \n Monday");
                txt_t.setText("Every \n Tuesday");
                txt_w.setText("Every \n Wednesday");
                txt_th.setText("Every \n Thursday");
                txt_fr.setText("Every \n Friday");
                txt_st.setText("Every \n Saturday");


            }
        });

        img_oneTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Ordertype = "0";
                Preference.save(getActivity(),Preference.KEY_Ordertype,Ordertype);

                txt_s.setText("Sunday");
                txt_m.setText("Monday");
                txt_t.setText("Tuesday");
                txt_w.setText("Wednesday");
                txt_th.setText("Thursday");
                txt_fr.setText("Friday");
                txt_st.setText("Saturday");

                img_oneTime.setImageResource(R.drawable.radio_button_red);
                img_repeat.setImageResource(R.drawable.circle_grey);

            }
        });

        txt_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button cancel_btn,logout_btn;
                final EditText edt_payment;
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();
                dialog.setContentView(R.layout.custome_dialog_payment);
                cancel_btn=dialog.findViewById(R.id.dialog_cancel_button);
                logout_btn=dialog.findViewById(R.id.exit_button);
                edt_payment=dialog.findViewById(R.id.edt_payment);
                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });
                logout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String paymentTip= edt_payment.getText().toString();

                        float price= Float.parseFloat(paymentTip);

                        finalPrice= TotalPrice+price;

                        Booking_payment.setText("Continue to checkout : $"+finalPrice);

                        dialog.dismiss();
                    }
                });


            }
        });

        txt_time_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   String[] country = { "9Am-10Am", "10Am-11Am", "11Am-12Pm", "12Pm-1Pm", "1Pm-2Pm"};
                Spinner spin;

                Button cancel_btn,logout_btn;
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();
                dialog.setContentView(R.layout.change_time_dialog);
                spin = (Spinner)dialog.findViewById(R.id.spinner);
                cancel_btn=dialog.findViewById(R.id.dialog_cancel_button);
                logout_btn=dialog.findViewById(R.id.exit_button);

                ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,country);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                spin.setAdapter(aa);

                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });
                logout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });*/
            }
        });

        txt_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //RR_remove_item.setVisibility(View.VISIBLE);

                emptyCartApi();
            }
        });


        txt_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.save(getActivity(),Preference.KEY_OrderDay,"1");
                txt_s.setBackgroundResource(R.drawable.rounded_red_one);
                txt_s.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

                txt_m.setBackgroundResource(R.drawable.rounded);
                txt_m.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_t.setBackgroundResource(R.drawable.rounded);
                txt_t.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_w.setBackgroundResource(R.drawable.rounded);
                txt_w.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_th.setBackgroundResource(R.drawable.rounded);
                txt_th.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_fr.setBackgroundResource(R.drawable.rounded);
                txt_fr.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_st.setBackgroundResource(R.drawable.rounded);
                txt_st.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
            }
        });
        txt_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.save(getActivity(),Preference.KEY_OrderDay,"2");
                txt_m.setBackgroundResource(R.drawable.rounded_red_one);
                txt_m.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

                txt_s.setBackgroundResource(R.drawable.rounded);
                txt_s.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_t.setBackgroundResource(R.drawable.rounded);
                txt_t.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_w.setBackgroundResource(R.drawable.rounded);
                txt_w.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_th.setBackgroundResource(R.drawable.rounded);
                txt_th.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_fr.setBackgroundResource(R.drawable.rounded);
                txt_fr.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_st.setBackgroundResource(R.drawable.rounded);
                txt_st.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));

            }
        });
        txt_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.save(getActivity(),Preference.KEY_OrderDay,"3");
                txt_t.setBackgroundResource(R.drawable.rounded_red_one);
                txt_t.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));


                txt_s.setBackgroundResource(R.drawable.rounded);
                txt_s.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_m.setBackgroundResource(R.drawable.rounded);
                txt_m.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_w.setBackgroundResource(R.drawable.rounded);
                txt_w.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_th.setBackgroundResource(R.drawable.rounded);
                txt_th.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_fr.setBackgroundResource(R.drawable.rounded);
                txt_fr.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_st.setBackgroundResource(R.drawable.rounded);
                txt_st.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));

            }
        });
        txt_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.save(getActivity(),Preference.KEY_OrderDay,"4");
                txt_w.setBackgroundResource(R.drawable.rounded_red_one);
                txt_w.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));


                txt_s.setBackgroundResource(R.drawable.rounded);
                txt_s.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_m.setBackgroundResource(R.drawable.rounded);
                txt_m.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_t.setBackgroundResource(R.drawable.rounded);
                txt_t.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_th.setBackgroundResource(R.drawable.rounded);
                txt_th.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_fr.setBackgroundResource(R.drawable.rounded);
                txt_fr.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_st.setBackgroundResource(R.drawable.rounded);
                txt_st.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));

            }
        });
        txt_th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.save(getActivity(),Preference.KEY_OrderDay,"5");
                txt_th.setBackgroundResource(R.drawable.rounded_red_one);
                txt_th.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

                txt_s.setBackgroundResource(R.drawable.rounded);
                txt_s.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_m.setBackgroundResource(R.drawable.rounded);
                txt_m.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_t.setBackgroundResource(R.drawable.rounded);
                txt_t.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_w.setBackgroundResource(R.drawable.rounded);
                txt_w.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_fr.setBackgroundResource(R.drawable.rounded);
                txt_fr.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_st.setBackgroundResource(R.drawable.rounded);
                txt_st.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));

            }
        });
        txt_fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.save(getActivity(),Preference.KEY_OrderDay,"6");
                txt_fr.setBackgroundResource(R.drawable.rounded_red_one);
                txt_fr.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

                txt_s.setBackgroundResource(R.drawable.rounded);
                txt_s.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_m.setBackgroundResource(R.drawable.rounded);
                txt_m.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_t.setBackgroundResource(R.drawable.rounded);
                txt_t.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_w.setBackgroundResource(R.drawable.rounded);
                txt_w.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_th.setBackgroundResource(R.drawable.rounded);
                txt_th.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_st.setBackgroundResource(R.drawable.rounded);
                txt_st.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));

            }
        });
        txt_st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.save(getActivity(),Preference.KEY_OrderDay,"7");
                txt_st.setBackgroundResource(R.drawable.rounded_red_one);
                txt_st.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));


                txt_s.setBackgroundResource(R.drawable.rounded);
                txt_s.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_m.setBackgroundResource(R.drawable.rounded);
                txt_m.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_t.setBackgroundResource(R.drawable.rounded);
                txt_t.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_w.setBackgroundResource(R.drawable.rounded);
                txt_w.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
                txt_fr.setBackgroundResource(R.drawable.rounded);
                txt_fr.setTextColor(ContextCompat.getColor(getActivity(), R.color.Black));
            }
        });


        RR_moreTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isFinalArray =true;

                setAdapter_chooseTime(modelList__chooseTime);

                RR_moreTime.setVisibility(View.INVISIBLE);

            }
        });

        RR_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment someFragment = new HomeFragment();
                FragmentTransaction transaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, someFragment );
                transaction.addToBackStack("home");  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });


        getCardMethod();

        getDayTimeMethod();

        return attachToSwipeBack(rootView);
    }



    private void findViews() {
        RR_delivery=(RelativeLayout) rootView.findViewById(R.id.RR_delivery);
        RR_Repeat=(RelativeLayout) rootView.findViewById(R.id.RR_Repeat);
        RR_oneTime=(RelativeLayout) rootView.findViewById(R.id.RR_oneTime);
        txt_time_change=(TextView) rootView.findViewById(R.id.txt_time_change);
        img_repeat=(ImageView) rootView.findViewById(R.id.img_repeat);
        img_oneTime=(ImageView) rootView.findViewById(R.id.img_oneTime);
        txt_custom=(TextView) rootView.findViewById(R.id.txt_custom);
        txt_cash_tip=(TextView) rootView.findViewById(R.id.txt_cash_tip);
        txt_ten_percentage=(TextView) rootView.findViewById(R.id.txt_ten_percentage);
        txt_fiveteen_percentage=(TextView) rootView.findViewById(R.id.txt_fiveteen_percentage);
        txt_twenty_percentage=(TextView) rootView.findViewById(R.id.txt_twenty_percentage);

        recycler_order=(RecyclerView) rootView.findViewById(R.id.recycler_order);
        recycler_ingredients=(RecyclerView) rootView.findViewById(R.id.recycler_ingredients);
        Booking_payment=(Button) rootView.findViewById(R.id.Booking_payment);
        addmore_btn=(Button) rootView.findViewById(R.id.addmore_btn);
        order_RepeatOrder=(Button) rootView.findViewById(R.id.order_RepeatOrder);
        txtfinance_amount=(TextView) rootView.findViewById(R.id.txtfinance_amount);
        txtemi_start_date=(TextView) rootView.findViewById(R.id.txtemi_start_date);
        txt_final_amt=(TextView) rootView.findViewById(R.id.txt_final_amt);
        txt_empty=(TextView) rootView.findViewById(R.id.txt_empty);
        recycler_Coosetime=(RecyclerView) rootView.findViewById(R.id.recycler_Coosetime);
        RR_allItem=(RelativeLayout) rootView.findViewById(R.id.RR_allItem);
        txt_emty=(TextView) rootView.findViewById(R.id.txt_emty);
        RR_moreTime=(RelativeLayout) rootView.findViewById(R.id.RR_moreTime);
        progressbar=(ProgressBar) rootView.findViewById(R.id.progressbar);


        txt_s=(TextView) rootView.findViewById(R.id.txt_s);
        txt_m=(TextView) rootView.findViewById(R.id.txt_m);
        txt_t=(TextView) rootView.findViewById(R.id.txt_t);
        txt_w=(TextView) rootView.findViewById(R.id.txt_w);
        txt_th=(TextView) rootView.findViewById(R.id.txt_th);
        txt_fr=(TextView) rootView.findViewById(R.id.txt_fr);
        txt_st=(TextView) rootView.findViewById(R.id.txt_st);

    }

    private void setAdapterOrder(ArrayList<GetCartListDataModel> modelList_post) {


        recyclerView_post = new RecyclerViewAdapterPost(getActivity(), modelList_post);
        recycler_order.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_order.setLayoutManager(layoutManager);
        recycler_order.setAdapter(recyclerView_post);


        recyclerView_post.SetOnItemClickListener(new RecyclerViewAdapterPost.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, GetCartListDataModel model) {
                //handle item click events here
                //  Toast.makeText(getContext(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setAdapterIngredents(ArrayList<GetCartListDataModel> modelList_post) {


        recyclerView_ingredents = new RecyclerViewAdapter_ingredents(getActivity(), modelList_post);
        recycler_ingredients.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_ingredients.setLayoutManager(layoutManager);
        recycler_ingredients.setAdapter(recyclerView_ingredents);


        recyclerView_ingredents.SetOnItemClickListener(new RecyclerViewAdapter_ingredents.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, GetCartListDataModel model) {
                //handle item click events here
                //  Toast.makeText(getContext(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter_chooseTime(ArrayList<TimePeriedModel> modelList__chooseTime) {

        if(isFinalArray)
        {
            modelList__chooseTime_fourTime.clear();

            adapter_chooseTime = new RecyclerViewAdapterCooseTime(getActivity(), this.modelList__chooseTime);

        }else
        {
            RR_moreTime.setVisibility(View.VISIBLE);

            modelList__chooseTime_fourTime.clear();
            this.modelList__chooseTime_fourTime.add(modelList__chooseTime.get(0));
            this.modelList__chooseTime_fourTime.add(modelList__chooseTime.get(1));
            this.modelList__chooseTime_fourTime.add(modelList__chooseTime.get(2));
            this.modelList__chooseTime_fourTime.add(modelList__chooseTime.get(3));
            adapter_chooseTime = new RecyclerViewAdapterCooseTime(getActivity(), this.modelList__chooseTime_fourTime);

        }

        recycler_Coosetime.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_Coosetime.setLayoutManager(layoutManager);
        recycler_Coosetime.setAdapter(adapter_chooseTime);

        adapter_chooseTime.SetOnItemClickListener(new RecyclerViewAdapterCooseTime.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ArrayList<TimePeriedModel> modelList, RadioButton radio_time) {
                //handle item click events here
                //  Toast.makeText(getContext(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();

                for (int i = 0; i <modelList.size() ; i++) {
                    if(position == i){
                        radio_time.setChecked(true);
                        modelList.get(i).setChecked(true);
                    }else{
                        radio_time.setChecked(false);
                        modelList.get(i).setChecked(false);
                    }
                }


                adapter_chooseTime.notifyDataSetChanged();

                Preference.save(getActivity(),Preference.KEY_OrderTime, modelList.get(position).getTimePeriod());

            }
        });
    }



    public void getCardMethod(){

        String user_id = Preference.get(getActivity(),Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",user_id);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_getCartList, Constants.USER_getCartList,map, Request.Method.POST);
    }

    public void getDayTimeMethod(){

        String user_id = Preference.get(getActivity(),Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_getDaysTime, Constants.USER_getDaysTime,map, Request.Method.GET);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_getCartList.equalsIgnoreCase(tag_json_obj)){

            progressbar.setVisibility(View.GONE);

            if (response != null) {

                progressbar.setVisibility(View.GONE);

                CardModelList finalArray = new Gson().fromJson(response,new TypeToken<CardModelList>(){}.getType());

                String status= String.valueOf(finalArray.getMessage());

                if (status.equalsIgnoreCase("success")){

                    Booking_payment.setVisibility(View.VISIBLE);
                    RR_allItem.setVisibility(View.VISIBLE);
                    txt_emty.setVisibility(View.GONE);

                    txtfinance_amount.setText("$"+finalArray.getAmount());
                    txtemi_start_date.setText("$"+"0");

                    TotalPrice= Double.parseDouble(finalArray.getFinalAmount());

                    Double test  = 1.0;

                    String value = String.format("%1$,.2f",TotalPrice);

                   Preference.saveFloat(getActivity(),Preference.KEY_amount, Float.valueOf(value));

                    Booking_payment.setText("Continue to checkout : $"+value );

                    txt_final_amt.setText("$"+finalArray.getFinalAmount());

                    modelList_post = (ArrayList<GetCartListDataModel>) finalArray.getGetCartList();

                    if(modelList_post.size() > 0){
                        txt_empty.setVisibility(View.VISIBLE);
                    }else{
                        txt_empty.setVisibility(View.GONE);
                    }


                    setAdapterOrder(modelList_post);

                    //   setAdapterIngredents(modelList_post);

                }
                else {

                    Booking_payment.setVisibility(View.GONE);
                    RR_allItem.setVisibility(View.GONE);
                    txt_emty.setVisibility(View.VISIBLE);

               //     Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else if (Constants.USER_getDaysTime.equalsIgnoreCase(tag_json_obj)){

            progressbar.setVisibility(View.GONE);

            if (!response.equalsIgnoreCase(null)) {

                TimeModel finalArray = new Gson().fromJson(response,new TypeToken<TimeModel>(){}.getType());

                String status= String.valueOf(finalArray.getMessage());

                if (status.equalsIgnoreCase("success")){


                    modelList__chooseTime = (ArrayList<TimePeriedModel>) finalArray.getTimePeriod();

                    setAdapter_chooseTime(modelList__chooseTime);

                }
                else {

                //    Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        } else if (Constants.User_category_removeCart.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                TimeModel finalArray = new Gson().fromJson(response,new TypeToken<TimeModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());

                if (status.equalsIgnoreCase("success")){
                    Booking_payment.setVisibility(View.GONE);
                    RR_allItem.setVisibility(View.GONE);
                    txt_emty.setVisibility(View.VISIBLE);


                }
                else {
                //    Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        progressbar.setVisibility(View.GONE);

    }


    public void emptyCartApi(){

        String user_id = Preference.get(getActivity(),Preference.KEY_USER_ID);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id",user_id);
        ApiRequest apiRequest = new ApiRequest(getActivity(),this);
        apiRequest.postRequest(Constants.BASE_URL + Constants.User_category_removeCart, Constants.User_category_removeCart,map, Request.Method.POST);
    }



}
