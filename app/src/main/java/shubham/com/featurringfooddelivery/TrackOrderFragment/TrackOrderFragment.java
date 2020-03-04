package shubham.com.featurringfooddelivery.TrackOrderFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import java.util.List;

import me.yokeyword.swipebackfragment.SwipeBackFragment;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.LoginModel;
import shubham.com.featurringfooddelivery.MainActivity;
import shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment.OrderHistoryFragment;
import shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment.ProfileFragment;
import shubham.com.featurringfooddelivery.Preference;
import shubham.com.featurringfooddelivery.R;
import shubham.com.featurringfooddelivery.StripePayment.PaymentActivityStripe;
import shubham.com.featurringfooddelivery.Volley.ApiRequest;
import shubham.com.featurringfooddelivery.Volley.Constants;
import shubham.com.featurringfooddelivery.Volley.IApiResponse;

public class TrackOrderFragment extends SwipeBackFragment{

    View rootView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static TrackOrderFragment newInstance(String param1, String param2) {
        TrackOrderFragment fragment = new TrackOrderFragment();
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
        rootView = inflater.inflate(R.layout.activity_trackorderfragment, container, false);

        HomeBottomActivity.RR_toolbar.setVisibility(View.VISIBLE);

        HomeBottomActivity.txt_title.setText("MY ACCOUNT");

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return attachToSwipeBack(rootView);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new StatusFragment(), "Status");
        adapter.addFrag(new ReceiptFragment(), "Receipt");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
