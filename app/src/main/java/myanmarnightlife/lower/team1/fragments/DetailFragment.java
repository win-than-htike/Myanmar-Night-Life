package myanmarnightlife.lower.team1.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.activities.DetailPagerActivity;
import myanmarnightlife.lower.team1.data.Places;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 100;
    private static final String PLACES = "PLACES";

    private String numberToCall = null;

    @BindView(R.id.tv_review)
    TextView tvReview;

    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @BindView(R.id.tv_website)
    TextView tvWebsite;

    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.fab_map)
    FloatingActionButton fabMap;

    @BindView(R.id.iv_phone)
    ImageView ivPhone;

    @BindView(R.id.iv_col_shop)
    ImageView ivShopImage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_rating)
    RatingBar ratingBar;

    @BindView(R.id.toolbar_title)
    TextView tvToolbarTitle;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private Places mPlaces;

    public static DetailFragment INSTANCE;

    public static final String BUNDLE_EXTRA = "place";

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Places places) {
        INSTANCE = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PLACES, Parcels.wrap(places));
        INSTANCE.setArguments(bundle);
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        mPlaces = Parcels.unwrap(bundle.getParcelable(PLACES));

        ((DetailPagerActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivShopImage.setTransitionName(getString(R.string.share_image_transition));
        }

        collapsingToolbarLayout.setTitleEnabled(false);


        fabMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateInMap(mPlaces.getShopRoute());
            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall(mPlaces.getShopPhoneNumber());
            }
        });

        tvToolbarTitle.setText(mPlaces.getShopName());
        ratingBar.setRating(Float.parseFloat(mPlaces.getRating()));
        tvReview.setText(mPlaces.getShopReview());
        tvPhone.setText(mPlaces.getShopPhoneNumber());
        tvTime.setText(mPlaces.getShopTime());
        tvAddress.setText(mPlaces.getShopAddress());

        Glide.with(MyanmarNightLifeApp.getContext())
                .load(mPlaces.getShopImage())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.night)
                .error(R.drawable.night)
                .into(ivShopImage);

        return view;


    }

    protected void navigateInMap(String uriToOpen) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + uriToOpen));
        startActivity(intent);
    }

    protected void makeCall(String numberToCall) {
        numberToCall.replaceAll(" ", "");
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numberToCall));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            this.numberToCall = numberToCall;

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

            return;
        }
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!
                    makeCall(numberToCall);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }




}
