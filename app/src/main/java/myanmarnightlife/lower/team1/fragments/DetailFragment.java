package myanmarnightlife.lower.team1.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import io.realm.Realm;
import io.realm.exceptions.RealmException;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.activities.DetailPagerActivity;
import myanmarnightlife.lower.team1.adapters.PlacesRVAdapter;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.databinding.FragmentDetailBinding;
import myanmarnightlife.lower.team1.utils.Constants;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
  FragmentDetailBinding fragmentDetailBinding;
  private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 100;
  private static final String PLACES = "PLACES";

  private String numberToCall = null;

  @BindView(R.id.tv_review) TextView tvReview;

  @BindView(R.id.tv_phone) TextView tvPhone;

  @BindView(R.id.tv_time) TextView tvTime;

  @BindView(R.id.tv_address) TextView tvAddress;

  @BindView(R.id.fab_map) FloatingActionButton fabMap;

  @BindView(R.id.iv_phone) ImageView ivPhone;

  @BindView(R.id.iv_col_shop) ImageView ivShopImage;

  @BindView(R.id.toolbar) Toolbar toolbar;

  @BindView(R.id.tv_rating) RatingBar ratingBar;

  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;

  private Places mPlaces;

  public static DetailFragment INSTANCE;

  public static final String BUNDLE_EXTRA = "place";

  private Realm realm;

  private Menu menu;

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

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_detail, container, false);
    fragmentDetailBinding = FragmentDetailBinding.bind(view);
    ButterKnife.bind(this, view);

    Bundle bundle = getArguments();
    mPlaces = Parcels.unwrap(bundle.getParcelable(PLACES));

    ((DetailPagerActivity) getActivity()).setSupportActionBar(toolbar);

    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      ivShopImage.setTransitionName(getString(R.string.share_image_transition));
    }

    fabMap.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        navigateInMap(mPlaces.getShopRoute());
      }
    });

    ivPhone.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        makeCall(mPlaces.getShopPhoneNumber());
      }
    });

    toolbar.setTitle(mPlaces.getShopName());
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
    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
        Uri.parse("http://maps.google.com/maps?daddr=" + uriToOpen));
    startActivity(intent);
  }

  protected void makeCall(String numberToCall) {
    numberToCall.replaceAll(" ", "");
    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + numberToCall));
    startActivity(intent);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    inflater.inflate(R.menu.detail_menu, menu);

    this.menu = menu;

    if (mPlaces.getIsSaved() == Constants.SAVED) {

      menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_white_24dp));

    } else {

      menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_border_white_24dp));

    }

    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {

      case android.R.id.home:
        getActivity().onBackPressed();
        return true;

      case R.id.action_share:
        Toast.makeText(MyanmarNightLifeApp.getContext(), "Share", Toast.LENGTH_SHORT).show();
        return true;

      case R.id.action_fav:
        try {

          realm = Realm.getDefaultInstance();

          realm.beginTransaction();

          if (mPlaces.getIsSaved() == Constants.SAVED) {
            mPlaces.setIsSaved(Constants.UNSAVED);
            item.setIcon(getResources().getDrawable(R.drawable.ic_favorite_border_white_24dp));
          } else {
            mPlaces.setIsSaved(Constants.SAVED);
            item.setIcon(getResources().getDrawable(R.drawable.ic_favorite_white_24dp));
          }

          realm.copyToRealmOrUpdate(mPlaces);

          realm.commitTransaction();
        } catch (RealmException e) {
          e.printStackTrace();
        }

        PlacesRVAdapter mAdapter = new PlacesRVAdapter();
        mAdapter.notifyDataSetChanged();

        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
