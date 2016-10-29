package myanmarnightlife.lower.team1.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import java.util.HashMap;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.activities.MainActivity;
import myanmarnightlife.lower.team1.activities.ShopActivity;
import myanmarnightlife.lower.team1.databinding.FragmentMainBinding;

/**
 * Created by winthanhtike on 10/11/16.
 */
public class FragmentMain extends Fragment
    implements View.OnClickListener, BaseSliderView.OnSliderClickListener,
    ViewPagerEx.OnPageChangeListener {

  @BindView(R.id.pager_shop_images) SliderLayout viewPager;

  @BindView(R.id.beer) CardView beerCard;

  @BindView(R.id.karaoke) CardView karaokeCard;

  @BindView(R.id.night_club) CardView nightClubCard;

  @BindView(R.id.bar) CardView barCard;

  @BindView(R.id.massage) CardView massageCard;

  @BindView(R.id.restaurant) CardView restaurantCard;

  private String[] images = {
      "http://myanmarbeer.com/wp-content/uploads/2016/07/Home-Banner-New2.jpg",
      "https://www.myanmore.com/yangon/wp-content/uploads/sites/2/2016/05/fuse-logo.jpg",
      "http://www.hardrockhotelpuntacana.com/files/1431/LADIESNIGHThotelwww-banner.jpg",
      "http://myanmarbeer.com/wp-content/uploads/2016/07/Home-Banner-New2.jpg"
  };
  private FragmentMainBinding contentHomeNavBinding;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    contentHomeNavBinding = DataBindingUtil.bind(view);
    ButterKnife.bind(this, view);

    ((MainActivity) getActivity()).getSupportActionBar()
        .setBackgroundDrawable(
            new ColorDrawable(getResources().getColor(android.R.color.transparent)));

    beerCard.setOnClickListener(this);
    karaokeCard.setOnClickListener(this);
    nightClubCard.setOnClickListener(this);
    barCard.setOnClickListener(this);
    massageCard.setOnClickListener(this);
    restaurantCard.setOnClickListener(this);

    HashMap<String, String> url_maps = new HashMap<String, String>();
    url_maps.put("Fuse Bar",
        "https://www.myanmore.com/yangon/wp-content/uploads/sites/2/2016/05/fuse-logo.jpg");
    url_maps.put("Lady Night Event",
        "http://www.hardrockhotelpuntacana.com/files/1431/LADIESNIGHThotelwww-banner.jpg");
    url_maps.put("Myanmar Beer",
        "http://myanmarbeer.com/wp-content/uploads/2016/07/Home-Banner-New2.jpg");

    for (String name : url_maps.keySet()) {
      TextSliderView textSliderView = new TextSliderView(MyanmarNightLifeApp.getContext());
      // initialize a SliderLayout
      textSliderView.description(name)
          .image(url_maps.get(name))
          .setScaleType(BaseSliderView.ScaleType.Fit);

      //add your extra information
      textSliderView.bundle(new Bundle());
      textSliderView.getBundle().putString("extra", name);

      viewPager.addSlider(textSliderView);
    }

    viewPager.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
    viewPager.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    viewPager.setCustomAnimation(new DescriptionAnimation());
    viewPager.setDuration(4000);

    return view;
  }

  @Override public void onClick(View view) {

    switch (view.getId()) {

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

  @Override public void onPause() {
    super.onPause();
  }

  @Override public void onResume() {
    super.onResume();
    viewPager.startAutoCycle();
  }

  @Override public void onStop() {
    viewPager.stopAutoCycle();
    super.onStop();
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override public void onPageSelected(int position) {

  }

  @Override public void onPageScrollStateChanged(int state) {

  }

  @Override public void onSliderClick(BaseSliderView slider) {

  }
}
