package myanmarnightlife.lower.team1.activities;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.TaxiSeriveCardFragmentPagerAdapter;
import myanmarnightlife.lower.team1.utils.ShadowTranformer;

public class TaxiServiceActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private TaxiSeriveCardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTranformer mFragmentCardShadowTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi_service);
        ButterKnife.bind(this,this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragmentCardAdapter = new TaxiSeriveCardFragmentPagerAdapter(getSupportFragmentManager(),
                dpToPixels(2, this));

        mFragmentCardShadowTransformer = new ShadowTranformer(mViewPager, mFragmentCardAdapter);
        mViewPager.setAdapter(mFragmentCardAdapter);
        mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);


    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
