package myanmarnightlife.lower.team1.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import io.realm.Realm;
import io.realm.exceptions.RealmException;
import java.util.ArrayList;
import java.util.List;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.databinding.BeerCardItemBinding;
import myanmarnightlife.lower.team1.interfaces.ItemClickListener;
import myanmarnightlife.lower.team1.utils.Constants;

/**
 * Created by winthanhtike on 10/11/16.
 */
public class PlacesRVAdapter extends RecyclerView.Adapter<PlacesRVAdapter.PlcacesShopViewHolder> {

  private LayoutInflater inflater;
  private List<Places> mPlacesLists=new ArrayList<>();
  private ItemClickListener itemClickListener;
  private Context context;

  public PlacesRVAdapter(List<Places> mPlacesLists, ItemClickListener itemClickListener,
      Context context) {
    this.mPlacesLists = mPlacesLists;
    this.itemClickListener = itemClickListener;
    this.context = context;
    inflater = LayoutInflater.from(MyanmarNightLifeApp.getContext());
  }

  public  PlacesRVAdapter(ItemClickListener itemClickListener,Context context) {
    this.itemClickListener = itemClickListener;

    this.context = context;
    inflater = LayoutInflater.from(MyanmarNightLifeApp.getContext());
  }


  public void addItem(Places place) {
    //if (!mPlacesLists.contains(place)) {
      mPlacesLists.add(place);
      notifyItemInserted(mPlacesLists.size());
    //}
  }

  public PlacesRVAdapter() {
  }

  @Override public PlcacesShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_card_item, parent, false);

    return new PlcacesShopViewHolder(rootView, itemClickListener);
  }

  @Override public void onBindViewHolder(PlcacesShopViewHolder holder, int position) {

    Places places = mPlacesLists.get(position);
    holder.setData(places,position);

    if (places.getIsSaved() == Constants.SAVED) {

      holder.fav.setImageResource(R.drawable.ic_favorite_white_24dp);
    } else {

      holder.fav.setImageResource(R.drawable.ic_favorite_border_white_24dp);
    }
  }

  public Places getSelectedPlace(int position) {
    return mPlacesLists.get(position);
  }

  @Override public int getItemCount() {
    return mPlacesLists.size();
  }

  public class PlcacesShopViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener {
    BeerCardItemBinding beerCardItemBinding;

    @BindView(R.id.iv_beer_shop) ImageView imgShop;

    @BindView(R.id.tv_shop_name) TextView tvShopName;

    @BindView(R.id.rate_beer_shop) RatingBar shopRatingBar;

    @BindView(R.id.iv_fav) ImageView fav;

    private Realm realm;

    private Places places;

    private ItemClickListener itemClickListener;

    public PlcacesShopViewHolder(View itemView, ItemClickListener itemClickListener) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      beerCardItemBinding = DataBindingUtil.bind(itemView);
      this.itemClickListener = itemClickListener;
      itemView.setOnClickListener(this);
    }

    public void setData(final Places places,final int position) {

      this.places = places;

      shopRatingBar.setRating(Float.parseFloat(places.getRating()));

      tvShopName.setText(places.getShopName());

      fav.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {

          Log.i("Name", places.get_id() + "");
          try {

            realm = Realm.getDefaultInstance();

            realm.beginTransaction();

            if (places.getIsSaved() == Constants.SAVED) {
              places.setIsSaved(Constants.UNSAVED);
              fav.setImageResource(R.drawable.ic_favorite_border_white_24dp);
            } else {
              places.setIsSaved(Constants.SAVED);
              fav.setImageResource(R.drawable.ic_favorite_white_24dp);
            }

            realm.copyToRealmOrUpdate(places);

            realm.commitTransaction();
          } catch (RealmException e) {
            e.printStackTrace();
          }

          //notifyItemChanged(position);
        }
      });

      Glide.with(MyanmarNightLifeApp.getContext())
          .load(places.getShopImage())
          .centerCrop()
          .crossFade()
          .error(R.drawable.night)
          .placeholder(R.drawable.placeholder)
          .into((imgShop));
    }

    @Override public void onClick(View view) {
      if (itemClickListener != null) itemClickListener.onTapShop(view,places, imgShop);
    }
  }
}
