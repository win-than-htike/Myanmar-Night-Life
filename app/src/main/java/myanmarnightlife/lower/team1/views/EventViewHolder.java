package myanmarnightlife.lower.team1.views;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.data.Event;

/**
 * Created by winthanhtike on 11/9/16.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_event_title)
    TextView tvEventTitle;

    @BindView(R.id.tv_event_desc)
    TextView tvEventDesc;

    @BindView(R.id.iv_event_photo)
    ImageView ivEventPhoto;

    @BindView(R.id.tv_event_time)
    TextView tvEventTime;

    public EventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

    }

    public void setEventData(Event event){

        tvEventTitle.setText(event.getEventName());
        tvEventDesc.setText(event.getEventInfo());
        tvEventTime.setText(event.getEventTime());

        Glide.with(MyanmarNightLifeApp.getContext())
                .load(event.getEventImageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.night)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivEventPhoto);

    }


}
