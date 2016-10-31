package myanmarnightlife.lower.team1.fragments;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.activities.MainActivity;
import myanmarnightlife.lower.team1.activities.ShopActivity;
import myanmarnightlife.lower.team1.adapters.PlacesRVAdapter;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.data.PlacesData;
import myanmarnightlife.lower.team1.data.PlacesModel;
import myanmarnightlife.lower.team1.helper.PlacesRealmHelper;
import myanmarnightlife.lower.team1.interfaces.ItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {

    @BindView(R.id.rv_shop_list)
    RecyclerView rvBeerShop;

    private static String TYPE;

    private List<Places> mPlaces;
    private PlacesRVAdapter mAdapter;
    private ItemClickListener itemClickListener;

    private static final String IE_TYPE = "TYPE";

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this,view);

        if (this.getActivity().getIntent().getExtras() != null){

            TYPE = this.getActivity().getIntent().getStringExtra(IE_TYPE);

        }

        rvBeerShop.setHasFixedSize(true);

        int gridColumn = getResources().getInteger(R.integer.list_grid);
        rvBeerShop.setLayoutManager(new GridLayoutManager(MyanmarNightLifeApp.getContext(),gridColumn));

        PlacesRealmHelper helper = PlacesRealmHelper.getInstance();


        mPlaces = helper.getBarList(TYPE);

        if (mPlaces.size() <= 0){
            PlacesData.getData();
            mPlaces = helper.getBarList(TYPE);
        }

        mAdapter = new PlacesRVAdapter(mPlaces, itemClickListener,(AppCompatActivity)getActivity());
        rvBeerShop.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
}
