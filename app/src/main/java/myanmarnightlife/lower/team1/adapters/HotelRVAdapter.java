package myanmarnightlife.lower.team1.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.data.Hotel;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.interfaces.ItemClickListener;

/**
 * Created by winthanhtike on 11/16/16.
 */
public class HotelRVAdapter extends RecyclerView.Adapter<HotelRVAdapter.HotelViewHolder> {

    private List<Hotel> hotelLists;
    private ItemClickListener clickListener;
    private Context context;

    public HotelRVAdapter(List<Hotel> hotelLists, ItemClickListener clickListener, Context context) {
        this.hotelLists = hotelLists;
        this.clickListener = clickListener;
        this.context = context;
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyanmarNightLifeApp.getContext()).inflate(R.layout.card_hotel,parent,false);
        return new HotelViewHolder(view,clickListener);
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {
        holder.setData(hotelLists.get(position));
    }

    @Override
    public int getItemCount() {
        return hotelLists.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_hotel)
        ImageView ivHotel;

        @BindView(R.id.tv_hotel_name)
        TextView tvHotelName;

        @BindView(R.id.rate_hotel)
        RatingBar rHotelRating;

        @BindView(R.id.tv_hotel_address)
        TextView tvHotelAddress;

        @BindView(R.id.tv_hotel_phone)
        TextView tvHotelPhone;

        @BindView(R.id.iv_hotel_phone)
        AppCompatImageView ivHotelPhone;

        private Hotel hotel;

        private ItemClickListener clickListener;

        public HotelViewHolder(View itemView,ItemClickListener clickListener) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            this.clickListener = clickListener;
            itemView.setOnClickListener(this);

        }

        public void setData(final Hotel hotel){
            this.hotel = hotel;

            rHotelRating.setRating(Float.parseFloat(hotel.getShopRating()));

            tvHotelName.setText(hotel.getShopName());

            tvHotelAddress.setText(hotel.getShopAddress());

            tvHotelPhone.setText(hotel.getShopPhoneNumber());

            Glide.with(MyanmarNightLifeApp.getContext())
                    .load(hotel.getShopImage())
                    .centerCrop()
                    .crossFade()
                    .error(R.drawable.night)
                    .placeholder(R.drawable.placeholder)
                    .into((ivHotel));

            ivHotelPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    makeCall(hotel.getShopPhoneNumber());
                }
            });

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onTapHotel(hotel,ivHotel);
        }

        protected void makeCall(String numberToCall) {
            numberToCall.replaceAll(" ", "");
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + numberToCall));
            context.startActivity(intent);
        }

    }
}
