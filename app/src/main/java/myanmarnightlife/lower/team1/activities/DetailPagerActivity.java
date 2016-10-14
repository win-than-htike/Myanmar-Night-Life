package myanmarnightlife.lower.team1.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.DetailPagerAdapter;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.fragments.DetailFragment;
import myanmarnightlife.lower.team1.fragments.FavouriteFragment;
import myanmarnightlife.lower.team1.helper.PlacesRealmHelper;

/**
 * Created by winthanhtike on 10/12/16.
 */
public class DetailPagerActivity extends AppCompatActivity {

    private static String PLACES = "PLACES";

    private static String IE_TYPE = "IE_TYPE";

    private static String TYPE;

    List<Integer> fragmentMap = new ArrayList<>();

    private Places mPlaces;


    public static Intent newInstance(Places places,String type){

        Intent intent = new Intent(MyanmarNightLifeApp.getContext(),DetailPagerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PLACES, Parcels.wrap(places));
        bundle.putString(IE_TYPE,type);
        intent.putExtras(bundle);
        return intent;

    }

    @BindView(R.id.viewPager_detail)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_pager);
        ButterKnife.bind(this,this);

        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            mPlaces = Parcels.unwrap(bundle.getParcelable(PLACES));
            TYPE = bundle.getString(IE_TYPE);
        }


        DetailPagerAdapter mAdapter = new DetailPagerAdapter(getSupportFragmentManager());

        PlacesRealmHelper helper = PlacesRealmHelper.getInstance();
        viewPager.setAdapter(mAdapter);
        for (Places places : helper.getBarList(TYPE)){
            fragmentMap.add(places.get_id());
            mAdapter.add(DetailFragment.newInstance(places));
        }

        mAdapter.notifyDataSetChanged();

        viewPager.setPageMargin((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));

        viewPager.setPageMarginDrawable(new ColorDrawable(0x22000000));

        viewPager.setCurrentItem(fragmentMap.indexOf(mPlaces.get_id()), false);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

}
