package myanmarnightlife.lower.team1.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.activities.ShopActivity;
import myanmarnightlife.lower.team1.adapters.PlacesRVAdapter;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.data.PlacesData;
import myanmarnightlife.lower.team1.helper.PlacesRealmHelper;
import myanmarnightlife.lower.team1.interfaces.ItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static String ARG_QUERY = "ARG_QUERY";

    public SearchFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.rv_serach)
    RecyclerView rvBeerShop;

    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    private static String TYPE;
    private String query;
    private List<Places> mPlaces;
    private PlacesRVAdapter mAdapter;
    private ItemClickListener itemClickListener;

    private static final String IE_TYPE = "TYPE";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        itemClickListener = (ItemClickListener) context;
    }

    public static SearchFragment newInstance(String query){
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY,query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);

        if (this.getActivity().getIntent().getExtras() != null){

            TYPE = this.getActivity().getIntent().getStringExtra(IE_TYPE);

        }

        query = getArguments().getString(ARG_QUERY);

        rvBeerShop.setHasFixedSize(true);

        int gridColumn = getResources().getInteger(R.integer.list_grid);
        rvBeerShop.setLayoutManager(new GridLayoutManager(MyanmarNightLifeApp.getContext(),gridColumn));

        PlacesRealmHelper helper = PlacesRealmHelper.getInstance();

        mPlaces = helper.queryedShops(query,TYPE);

        if (mPlaces.size() <= 0){
            mPlaces = helper.queryedShops(query,TYPE);
        }

        if (mPlaces.isEmpty()){
            tvEmpty.setVisibility(View.VISIBLE);
        }else {
            tvEmpty.setVisibility(View.GONE);
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
