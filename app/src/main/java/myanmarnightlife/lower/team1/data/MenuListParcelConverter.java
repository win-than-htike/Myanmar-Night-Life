package myanmarnightlife.lower.team1.data;

import android.os.Parcel;

import org.parceler.Parcels;

/**
 * Created by winthanhtike on 11/16/16.
 */
public class MenuListParcelConverter extends RealmListParcelConverter<RecommendMenu> {


    @Override
    public void itemToParcel(RecommendMenu input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public RecommendMenu itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(RecommendMenu.class.getClassLoader()));
    }
}
