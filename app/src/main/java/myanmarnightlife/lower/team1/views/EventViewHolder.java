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

    View mView;

    public EventViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

    }

    public void setEventTitle(String name){

        TextView tvEventName = (TextView)mView.findViewById(R.id.tv_event_title);
        tvEventName.setText(name);

    }

    public void setEventPhoto(String imageUrl){

        ImageView ivEventPhoto = (ImageView)mView.findViewById(R.id.iv_event_photo);

        Glide.with(MyanmarNightLifeApp.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.night)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivEventPhoto);

    }

    public void setEventDesc(String eventDesc){

        TextView tvEventDesc = (TextView)mView.findViewById(R.id.tv_event_desc);
        tvEventDesc.setText(eventDesc);

    }

    public void setEventTime(String time){

        TextView tvEventTime = (TextView)mView.findViewById(R.id.tv_event_time);
        tvEventTime.setText(time);

    }

    public void setEventType(String type){

        TextView tvEventType = (TextView)mView.findViewById(R.id.tv_event_type);
        tvEventType.setText(type);

    }

    public void setEventLocation(String location){

        TextView tvEventLocation = (TextView)mView.findViewById(R.id.tv_event_location);
        tvEventLocation.setText(location);

    }


}
