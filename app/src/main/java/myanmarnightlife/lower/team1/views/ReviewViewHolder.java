package myanmarnightlife.lower.team1.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import de.hdodenhof.circleimageview.CircleImageView;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;

/**
 * Created by winthanhtike on 11/11/16.
 */
public class ReviewViewHolder extends RecyclerView.ViewHolder{

    View mView;

    public ReviewViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

    }

    public void setReview(String review){

        TextView tvReview = (TextView)mView.findViewById(R.id.tv_review);
        tvReview.setText(review);

    }

    public void setUserName(String name){

        TextView tvUsername = (TextView)mView.findViewById(R.id.tv_username);
        tvUsername.setText(name);

    }

    public void setProfilePicture(String photoUrl){

        CircleImageView ivProfile = (CircleImageView) mView.findViewById(R.id.iv_review_userprofile);

        Glide.with(MyanmarNightLifeApp.getContext())
                .load(photoUrl)
                .crossFade()
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivProfile);

    }

}
