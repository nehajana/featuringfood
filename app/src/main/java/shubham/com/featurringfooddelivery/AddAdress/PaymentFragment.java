package shubham.com.featurringfooddelivery.AddAdress;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.yokeyword.swipebackfragment.SwipeBackFragment;
import shubham.com.featurringfooddelivery.R;

public class PaymentFragment extends SwipeBackFragment {

    View rootView;


    public static PaymentFragment newInstance(String param1, String param2) {
        PaymentFragment fragment = new PaymentFragment();
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
        rootView = inflater.inflate(R.layout.activity_add_payment, container, false);

        findViews();

        return attachToSwipeBack(rootView);

    }

    private void findViews() {

    }


}
