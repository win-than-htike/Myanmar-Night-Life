package myanmarnightlife.lower.team1.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;

/**
 * Created by winthanhtike on 11/16/16.
 */
public class HotelRVAdapter extends RecyclerView.Adapter<HotelRVAdapter.HotelViewHolder> {


    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyanmarNightLifeApp.getContext()).inflate(R.layout.card_hotel,parent,false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_hotel)
        ImageView ivHotel;

        @BindView(R.id.tv_hotel_name)
        TextView tvHotelName;

        @BindView(R.id.tv_hotel_price)
        TextView tvHotelPrice;

        @BindView(R.id.rate_hotel)
        RatingBar rHotelRating;

        public HotelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
