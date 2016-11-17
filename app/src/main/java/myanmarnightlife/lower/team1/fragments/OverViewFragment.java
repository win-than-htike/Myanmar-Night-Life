package myanmarnightlife.lower.team1.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.MenuRVAdapter;
import myanmarnightlife.lower.team1.data.Places;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverViewFragment extends Fragment {


    public static OverViewFragment INSTANCE;

    private static final String PLACES = "PLACES";

    @BindView(R.id.tv_review)
    TextView tvReview;

    @BindView(R.id.tv_phone) TextView tvPhone;

    @BindView(R.id.tv_time) TextView tvTime;

    @BindView(R.id.tv_address) TextView tvAddress;

    @BindView(R.id.iv_phone)
    ImageView ivPhone;

    @BindView(R.id.tv_rating)
    RatingBar ratingBar;

    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;

    private Places mPlaces;

    public OverViewFragment() {
        // Required empty public constructor
    }

    public static OverViewFragment newInstance(Places places){

        INSTANCE = new OverViewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PLACES, Parcels.wrap(places));
        INSTANCE.setArguments(bundle);
        return INSTANCE;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_over_view, container, false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        mPlaces = Parcels.unwrap(bundle.getParcelable(PLACES));

        tvReview.setText(mPlaces.getShopReview());
        tvPhone.setText(mPlaces.getShopPhoneNumber());

        tvTime.setText(mPlaces.getShopTime());
        tvAddress.setText(mPlaces.getShopAddress());
        ratingBar.setRating(Float.parseFloat(mPlaces.getRating()));

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                makeCall(mPlaces.getShopPhoneNumber());
            }
        });

        rvMenu.setHasFixedSize(true);
        rvMenu.setLayoutManager(new LinearLayoutManager(MyanmarNightLifeApp.getContext()));

        MenuRVAdapter mAdapter = new MenuRVAdapter(mPlaces.getRecommendMenus());
        rvMenu.setAdapter(mAdapter);

        return view;
    }

    protected void makeCall(String numberToCall) {
        numberToCall.replaceAll(" ", "");
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + numberToCall));
        startActivity(intent);
    }

}
