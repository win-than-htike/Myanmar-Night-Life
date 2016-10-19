package myanmarnightlife.lower.team1.data;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

import io.realm.PlacesRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by winthanhtike on 10/11/16.
 */
@Parcel(implementations = {PlacesRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = { Places.class })

public class Places extends RealmObject {


    @PrimaryKey
    @SerializedName("_id")
    int _id;

    @ParcelProperty("shopName")
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

    @SerializedName("shopTime")
    String shopTime;

    @SerializedName("shopRoute")
    String shopRoute;

    @SerializedName("shopCity")
    String shopCity;

    @SerializedName("shopType")
    String shopType;

    @SerializedName("rating")
    String rating;
    int isSaved;

    public Places() {
    }

    @ParcelConstructor
    public Places(int _id, @ParcelProperty("shopName") String shopName, String shopImage, String shopReview, String shopPhoneNumber, String shopAddress, String shopTime, String shopRoute, String shopCity, String shopType, String rating) {
        this._id = _id;
        this.shopName = shopName;
        this.shopImage = shopImage;
        this.shopReview = shopReview;
        this.shopPhoneNumber = shopPhoneNumber;
        this.shopAddress = shopAddress;
        this.shopTime = shopTime;
        this.shopRoute = shopRoute;
        this.shopCity = shopCity;
        this.shopType = shopType;
        this.rating = rating;
    }

//    @ParcelConstructor
//    public Places(@ParcelProperty("shopName") String shopName, String shopImage, String shopReview, String shopPhoneNumber, String shopAddress, String shopTime, String shopRoute, String shopCity, String shopType, String rating) {
//        this.shopName = shopName;
//        this.shopImage = shopImage;
//        this.shopReview = shopReview;
//        this.shopPhoneNumber = shopPhoneNumber;
//        this.shopAddress = shopAddress;
//        this.shopTime = shopTime;
//        this.shopRoute = shopRoute;
//        this.shopCity = shopCity;
//        this.shopType = shopType;
//        this.rating = rating;
//    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @ParcelProperty("shopName")
    public String getShopName() {
        return shopName;
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

    public String getShopTime() {
        return shopTime;
    }

    public String getShopRoute() {
        return shopRoute;
    }

    public String getShopCity() {
        return shopCity;
    }

    public String getShopType() {
        return shopType;
    }

    public int getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(int isSaved) {
        this.isSaved = isSaved;
    }

    public String getRating() {
        return rating;
    }

    public String getShopImage() {
        return shopImage;
    }


}
