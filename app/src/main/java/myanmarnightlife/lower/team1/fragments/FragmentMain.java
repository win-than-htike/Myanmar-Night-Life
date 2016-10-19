package myanmarnightlife.lower.team1.fragments;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.activities.ShopActivity;
import myanmarnightlife.lower.team1.activities.MainActivity;
import myanmarnightlife.lower.team1.adapters.ImagePagerAdapter;
import myanmarnightlife.lower.team1.views.PageIndicatorView;

/**
 * Created by winthanhtike on 10/11/16.
 */
public class FragmentMain extends Fragment implements View.OnClickListener {

    @BindView(R.id.pager_shop_images)
    ViewPager viewPager;

    @BindView(R.id.pi_shop_image_slider)
    PageIndicatorView imageSlider;

    @BindView(R.id.beer)
    CardView beerCard;

    @BindView(R.id.karaoke)
    CardView karaokeCard;

    @BindView(R.id.night_club)
    CardView nightClubCard;

    @BindView(R.id.bar)
    CardView barCard;

    @BindView(R.id.massage)
    CardView massageCard;

    @BindView(R.id.restaurant)
    CardView restaurantCard;

    private String[] images = {
            "http://myanmarbeer.com/wp-content/uploads/2016/07/Home-Banner-New2.jpg",
            "https://www.myanmore.com/yangon/wp-content/uploads/sites/2/2016/05/fuse-logo.jpg",
            "http://www.hardrockhotelpuntacana.com/files/1431/LADIESNIGHThotelwww-banner.jpg",
            "http://myanmarbeer.com/wp-content/uploads/2016/07/Home-Banner-New2.jpg"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,view);

        ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));

        beerCard.setOnClickListener(this);
        karaokeCard.setOnClickListener(this);
        nightClubCard.setOnClickListener(this);
        barCard.setOnClickListener(this);
        massageCard.setOnClickListener(this);
        restaurantCard.setOnClickListener(this);

        imageSlider.setNumPage(images.length);
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(images);
        viewPager.setAdapter(mAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                imageSlider.setCurrentPage(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.beer:
                Intent beerIntent = ShopActivity.newInstance("beer");
                startActivity(beerIntent);
                break;

            case R.id.karaoke:
                Intent karaokeIntent = ShopActivity.newInstance("ktv");
                startActivity(karaokeIntent);
                break;

            case R.id.night_club:
                Intent clubIntent = ShopActivity.newInstance("club");
                startActivity(clubIntent);
                break;

            case R.id.bar:
                Intent barIntent = ShopActivity.newInstance("bar");
                startActivity(barIntent);
                break;

            case R.id.massage:
                Intent massageIntent = ShopActivity.newInstance("massage");
                startActivity(massageIntent);
                break;

            case R.id.restaurant:
                Intent restaurantIntent = ShopActivity.newInstance("Restaurant");
                startActivity(restaurantIntent);
                break;

        }

    }
}
