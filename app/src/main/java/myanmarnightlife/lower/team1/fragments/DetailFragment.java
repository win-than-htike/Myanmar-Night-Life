package myanmarnightlife.lower.team1.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

  private static final int REQUEST_CODE = 200;
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

  @BindView(R.id.btn_user_review)
  Button btnUserReview;

  @BindView(R.id.et_user_review)
  EditText etUserReview;

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
    Log.i("Save",mPlaces.getIsSaved()+"");

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


    btnUserReview.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"myanmarnightlifewwa@gmail.com"});
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Comment");
        shareIntent.putExtra(Intent.EXTRA_TEXT,etUserReview.getText().toString());
        startActivityForResult(Intent.createChooser(shareIntent, "Comment"),REQUEST_CODE);
      }
    });


    return view;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == REQUEST_CODE || requestCode == REQUEST_CODE) {

        etUserReview.setText("");

    }else{
      etUserReview.setText(etUserReview.getText().toString());
    }

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


    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {

      case android.R.id.home:
          getActivity().onBackPressed();
        return true;

      case R.id.action_share:
          Intent sharingIntent = new Intent(Intent.ACTION_SEND);
          sharingIntent.setType("text/plain");
          sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mPlaces.getShopName() + "\n\n" + mPlaces.getShopReview() + "\n\n" + mPlaces.getShopPhoneNumber() + "\n\n" + mPlaces.getShopTime() + "\n\n" + mPlaces.getShopAddress());
          startActivity(Intent.createChooser(sharingIntent,"Share using"));
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
