package myanmarnightlife.lower.team1.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by winthanhtike on 10/11/16.
 */
public class Places extends RealmObject{


    @PrimaryKey
    private int _id;
    private String shopName;
    private String shopReview;
    private String[] shopPhoneNumber;
    private String shopWebsite;
    private String shopAddress;
    private String shopTime;
    private String shopRoute;
    private String shopCity;
    private String shopType;

    public Places() {
    }

    public Places(String shopName, String shopReview, String[] shopPhoneNumber, String shopWebsite, String shopAddress, String shopTime, String shopRoute, String shopCity, String shopType) {
        this.shopName = shopName;
        this.shopReview = shopReview;
        this.shopPhoneNumber = shopPhoneNumber;
        this.shopWebsite = shopWebsite;
        this.shopAddress = shopAddress;
        this.shopTime = shopTime;
        this.shopRoute = shopRoute;
        this.shopCity = shopCity;
        this.shopType = shopType;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopReview() {
        return shopReview;
    }

    public String[] getShopPhoneNumber() {
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
}
