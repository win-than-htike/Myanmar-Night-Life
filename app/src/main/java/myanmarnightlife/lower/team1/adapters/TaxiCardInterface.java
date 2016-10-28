package myanmarnightlife.lower.team1.adapters;

import android.support.v7.widget.CardView;

/**
 * Created by winthanhtike on 10/27/16.
 */
public interface TaxiCardInterface {


        int MAX_ELEVATION_FACTOR = 8;

        float getBaseElevation();

        CardView getCardViewAt(int position);

        int getCount();
    }

