package myanmarnightlife.lower.team1.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.DetailViewPagerAdapter;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.databinding.ActivityDetailBinding;
import myanmarnightlife.lower.team1.fragments.OverViewFragment;
import myanmarnightlife.lower.team1.fragments.ReviewFragment;
import org.parceler.Parcels;

/**
 * Created by winthanhtike on 10/12/16.
 */
public class DetailActivity extends AppCompatActivity {

    private static String PLACES = "PLACES";

    private static String IE_TYPE = "IE_TYPE";

    private static String TYPE;


    @BindView(R.id.iv_col_shop)
    ImageView ivShopImage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.detail_viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.fab_map)
    FloatingActionButton fabMap;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;

    private Places mPlaces;


    public static Intent newInstance(Places places, String type) {

        Intent intent = new Intent(MyanmarNightLifeApp.getContext(), DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PLACES, Parcels.wrap(places));
        bundle.putString(IE_TYPE, type);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        ActivityDetailBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            mPlaces = Parcels.unwrap(bundle.getParcelable(PLACES));
            TYPE = bundle.getString(IE_TYPE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivShopImage.setTransitionName(getString(R.string.share_image_transition));
        }

        setupViewPager(viewPager);

        fabMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateInMap(mPlaces.getShopRoute());
            }
        });

        mToolbarTitle.setText(mPlaces.getShopName());
        collapsingToolbar.setTitleEnabled(false);
        Glide.with(MyanmarNightLifeApp.getContext())
                .load(mPlaces.getShopImage())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.night)
                .error(R.drawable.night)
                .into(ivShopImage);

        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {

        DetailViewPagerAdapter mAdapter = new DetailViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(OverViewFragment.newInstance(mPlaces), "Overview");
        mAdapter.addFragment(ReviewFragment.newInstance(mPlaces), "Review");
        viewPager.setAdapter(mAdapter);

    }

    protected void navigateInMap(String uriToOpen) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=" + uriToOpen));
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mPlaces.getShopName() + "\n\n" + mPlaces.getShopReview() + "\n\n" + mPlaces.getShopPhoneNumber() + "\n\n" + mPlaces.getShopTime() + "\n\n" + mPlaces.getShopAddress());
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }


}
