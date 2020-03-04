package shubham.com.featurringfooddelivery.OrderHistoryScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.swipebackfragment.SwipeBackFragment;
import shubham.com.featurringfooddelivery.AddAdress.AddAdressFragment;
import shubham.com.featurringfooddelivery.AddAdress.PaymentFragment;
import shubham.com.featurringfooddelivery.HomeScreen.HomeBottomActivity;
import shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment.OrderHistoryFragment;
import shubham.com.featurringfooddelivery.OrderHistoryScreen.Fragment.ProfileFragment;
import shubham.com.featurringfooddelivery.R;

public class OrderHistoryFragmentOneMain extends SwipeBackFragment {

    View rootView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static OrderHistoryFragmentOneMain newInstance(String param1, String param2) {
        OrderHistoryFragmentOneMain fragment = new OrderHistoryFragmentOneMain();
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
        rootView = inflater.inflate(R.layout.activity_custom_view_icon_text_tabs, container, false);

        HomeBottomActivity.RR_toolbar.setVisibility(View.VISIBLE);

        HomeBottomActivity.txt_title.setText("MY ACCOUNT");

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());


        adapter.addFrag(new ProfileFragment(), "PROFILE");
        adapter.addFrag(new OrderHistoryFragment(), "HISTORY");
        adapter.addFrag(new AddAdressFragment(), "Address");
        adapter.addFrag(new PaymentFragment(), "Payment");

        viewPager.setAdapter(adapter);

        return attachToSwipeBack(rootView);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
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
