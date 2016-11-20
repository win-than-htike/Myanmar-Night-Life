package myanmarnightlife.lower.team1.data;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.HotelRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by winthanhtike on 11/18/16.
 */
@Parcel(implementations = {HotelRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = { Hotel.class })

public class Hotel extends RealmObject{

    @PrimaryKey
    @SerializedName("_id")
    int _id;

    @SerializedName("shopName")
    String shopName;

    @SerializedName("shopImage")
    String shopImage;

    @SerializedName("shopReview")
    String shopReview;

    @SerializedName("shopPhoneNumber")
    String shopPhoneNumber;

    @SerializedName("shopAddress")
    String shopAddress;

    @SerializedName("shopCity")
    String shopCity;

    @SerializedName("shopType")
    String shopType;

    @SerializedName("Rating")
    String shopRating;

    @SerializedName("tsp")
    String township;

    int isSaved;

    public Hotel() {
    }

    public Hotel(int _id, String shopName, String shopImage, String shopReview, String shopPhoneNumber, String shopAddress, String shopCity, String shopType, String shopRating, String township) {
        this._id = _id;
        this.shopName = shopName;
        this.shopImage = shopImage;
        this.shopReview = shopReview;
        this.shopPhoneNumber = shopPhoneNumber;
        this.shopAddress = shopAddress;
        this.shopCity = shopCity;
        this.shopType = shopType;
        this.shopRating = shopRating;
        this.township = township;
    }

    public int get_id() {
        return _id;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopImage() {
        return shopImage;
    }

    public String getShopReview() {
        return shopReview;
    }

    public String getShopPhoneNumber() {
        return shopPhoneNumber;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public String getShopCity() {
        return shopCity;
    }

    public String getShopType() {
        return shopType;
    }

    public String getShopRating() {
        return shopRating;
    }

    public String getTownship() {
        return township;
    }

    public int getIsSaved() {
        return isSaved;
    }
}
