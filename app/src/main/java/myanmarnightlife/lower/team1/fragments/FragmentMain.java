package myanmarnightlife.lower.team1.fragments;

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
import myanmarnightlife.lower.team1.R;
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
            "http://www.madridchow.com/wp-content/uploads/2012/08/La-Buena-Pinta-Madrid-craft-beer-shop.jpg",
            "http://warsawfoodie.pl/wp-content/uploads/2012/12/The_Beer_Store.jpg",
            "http://i2.getbucks.co.uk/incoming/article9139373.ece/ALTERNATES/s615/craft-beer.jpg"
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
                break;

            case R.id.karaoke:
                break;

            case R.id.night_club:
                break;

            case R.id.bar:
                break;

            case R.id.massage:
                break;

            case R.id.restaurant:
                break;

        }

    }
}
