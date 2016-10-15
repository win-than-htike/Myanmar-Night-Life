package myanmarnightlife.lower.team1.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.data.Places;

/**
 * Created by winthanhtike on 10/12/16.
 */
public class DetailActivity extends AppCompatActivity {

    private static String PLACES = "PLACES";

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 100;

    private String numberToCall = null;

    @BindView(R.id.tv_review)
    TextView tvReview;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

    private Places mPlaces;

    public static Intent newInstance(Places places) {

        Intent intent = new Intent(MyanmarNightLifeApp.getContext(), DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PLACES, Parcels.wrap(places));
        intent.putExtras(bundle);
        return intent;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
        }

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            mPlaces = Parcels.unwrap(bundle.getParcelable(PLACES));
        }

        toolbar.setTitle(mPlaces.getShopName());

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

        tvReview.setText(mPlaces.getShopReview());
        tvPhone.setText(mPlaces.getShopPhoneNumber());
        tvWebsite.setText(mPlaces.getShopWebsite());
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

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void navigateInMap(String uriToOpen) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + uriToOpen));
        startActivity(intent);
    }

    protected void makeCall(String numberToCall) {
        numberToCall.replaceAll(" ", "");
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numberToCall));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            this.numberToCall = numberToCall;

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

            return;
        }
        startActivity(intent);
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
