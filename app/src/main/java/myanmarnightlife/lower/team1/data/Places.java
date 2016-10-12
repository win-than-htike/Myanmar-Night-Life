package myanmarnightlife.lower.team1.data;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

import io.realm.PlacesRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by winthanhtike on 10/11/16.
 */
@Parcel(implementations = { PlacesRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Places.class })

public class Places extends RealmObject {


    @PrimaryKey
    int _id;
    @ParcelProperty("shopName")
    String shopName;
    String shopImage;
    String shopReview;
    String shopPhoneNumber;
    String shopWebsite;
    String shopAddress;
    String shopTime;
    String shopRoute;
    String shopCity;
    String shopType;
    String rating;
    int isSaved;

    public Places() {
    }


    @ParcelConstructor
    public Places(@ParcelProperty("shopName") String shopName, String shopImage, String shopReview, String shopPhoneNumber, String shopWebsite, String shopAddress, String shopTime, String shopRoute, String shopCity, String shopType, String rating) {
        this.shopName = shopName;
        this.shopImage = shopImage;
        this.shopReview = shopReview;
        this.shopPhoneNumber = shopPhoneNumber;
        this.shopWebsite = shopWebsite;
        this.shopAddress = shopAddress;
        this.shopTime = shopTime;
        this.shopRoute = shopRoute;
        this.shopCity = shopCity;
        this.shopType = shopType;
        this.rating = rating;
    }

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

    public String getShopWebsite() {
        return shopWebsite;
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
