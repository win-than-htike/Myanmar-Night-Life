package myanmarnightlife.lower.team1.fragments;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.activities.MainActivity;
import myanmarnightlife.lower.team1.activities.ShopActivity;
import myanmarnightlife.lower.team1.adapters.PlacesRVAdapter;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.helper.PlacesRealmHelper;
import myanmarnightlife.lower.team1.interfaces.ItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    @BindView(R.id.rv_favourite)
    RecyclerView rvFavourite;

    private List<Places> mPlaces;

    private PlacesRVAdapter mAdapter;


    private ItemClickListener itemClickListener;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        itemClickListener = (ItemClickListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        ButterKnife.bind(this,view);

        ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        rvFavourite.setHasFixedSize(true);

        int gridColumn = getResources().getInteger(R.integer.list_grid);
        rvFavourite.setLayoutManager(new GridLayoutManager(MyanmarNightLifeApp.getContext(),gridColumn));

        PlacesRealmHelper helper = PlacesRealmHelper.getInstance();
        mPlaces = helper.getFavouriteList();

        if (mPlaces.size() <= 0){
            mPlaces = helper.getFavouriteList();
        }

        mAdapter = new PlacesRVAdapter(mPlaces,itemClickListener,(AppCompatActivity)getActivity());
        rvFavourite.setAdapter(mAdapter);

        return view;
    }

}
