package myanmarnightlife.lower.team1.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import myanmarnightlife.lower.team1.adapters.HotelRVAdapter;
import myanmarnightlife.lower.team1.data.Hotel;
import myanmarnightlife.lower.team1.data.HotelData;
import myanmarnightlife.lower.team1.helper.HotelRealmHelper;
import myanmarnightlife.lower.team1.interfaces.ItemClickListener;

/**
 * Created by winthanhtike on 11/19/16.
 */
public class HotelFragment extends Fragment {

    private static final String ARG_TYPE = "ARG_TYPE";

    @BindView(R.id.rv_hotel)
    RecyclerView rvHotel;

    private HotelRVAdapter mAdapter;

    private List<Hotel> hotels;

    private ItemClickListener clickListener;

    String type;

    public static HotelFragment newInstance(String type){

        HotelFragment hotelFragment = new HotelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE,type);
        hotelFragment.setArguments(args);
        return hotelFragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.clickListener = (ItemClickListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hotel,container,false);
        ButterKnife.bind(this,v);

        type = getArguments().getString(ARG_TYPE);

        rvHotel.setHasFixedSize(true);
        rvHotel.setLayoutManager(new GridLayoutManager(MyanmarNightLifeApp.getContext(),2));

        HotelRealmHelper helper = HotelRealmHelper.getInstance();

        hotels = helper.getHotelListByType(type);

        if (hotels.size() <= 0){

            HotelData.getData();
            hotels = helper.getHotelListByType(type);

        }

        mAdapter = new HotelRVAdapter(hotels , clickListener, getActivity());
        rvHotel.setAdapter(mAdapter);

        return v;
    }
}
