package myanmarnightlife.lower.team1.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.PlacesRVAdapter;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.data.PlacesData;
import myanmarnightlife.lower.team1.helper.PlacesRealmHelper;
import myanmarnightlife.lower.team1.interfaces.ItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {

  private static final String ARG_TWS = "ARG_TWS";
  @BindView(R.id.rv_shop_list) RecyclerView rvBeerShop;

  private static String TYPE;

  private List<Places> mPlaces;
  private PlacesRVAdapter mAdapter;
  private ItemClickListener itemClickListener;

  private static final String IE_TYPE = "TYPE";

  String township;

  public static ShopFragment newInstance(String township){
    ShopFragment fragment = new ShopFragment();
    Bundle args = new Bundle();
    args.putString(ARG_TWS,township);
    fragment.setArguments(args);
    return fragment;
  }

  public ShopFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    itemClickListener = (ItemClickListener) context;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_shop, container, false);
    ButterKnife.bind(this, view);

    if (this.getActivity().getIntent().getExtras() != null) {

      TYPE = this.getActivity().getIntent().getStringExtra(IE_TYPE);

    }

    township = getArguments().getString(ARG_TWS);


    return view;
  }

  @Override public void onResume() {
    super.onResume();

    rvBeerShop.setHasFixedSize(true);

    int gridColumn = getResources().getInteger(R.integer.list_grid);
    rvBeerShop.setLayoutManager(
        new GridLayoutManager(MyanmarNightLifeApp.getContext(), gridColumn));

    PlacesRealmHelper helper = PlacesRealmHelper.getInstance();


    mPlaces = helper.getBarListByTownship(TYPE,township);

    if (mPlaces.size() <= 0) {
      PlacesData.getData();
      mPlaces = helper.getBarListByTownship(TYPE,township);
    }
    SlideInUpAnimator slideInUpAnimator = new SlideInUpAnimator();
    slideInUpAnimator.setAddDuration(500);
    rvBeerShop.setItemAnimator(slideInUpAnimator);
    mAdapter = new PlacesRVAdapter(itemClickListener, (AppCompatActivity) getActivity());
    rvBeerShop.setAdapter(mAdapter);

    new Handler().post(new Runnable() {
      @Override public void run() {
        setList(mPlaces);
      }
    });

    mAdapter.notifyDataSetChanged();

  }



  public void setList(List<Places> list) {
    for (Places places : list) {

        mAdapter.addItem(places);


    }
  }
}
