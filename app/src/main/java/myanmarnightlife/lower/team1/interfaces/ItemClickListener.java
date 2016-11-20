package myanmarnightlife.lower.team1.interfaces;

import android.view.View;
import android.widget.ImageView;

import myanmarnightlife.lower.team1.data.Hotel;
import myanmarnightlife.lower.team1.data.Places;

/**
 * Created by winthanhtike on 10/12/16.
 */
public interface ItemClickListener {

    void onTapShop(View v,Places places, ImageView imageView);
    void onTapHotel(Hotel hotel,ImageView imageView);

}
