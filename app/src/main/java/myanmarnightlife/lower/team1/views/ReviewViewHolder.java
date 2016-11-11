package myanmarnightlife.lower.team1.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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

}
