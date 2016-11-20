package myanmarnightlife.lower.team1.helper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import myanmarnightlife.lower.team1.data.Hotel;
import myanmarnightlife.lower.team1.data.Places;

/**
 * Created by winthanhtike on 11/18/16.
 */
public class HotelRealmHelper {

    private static HotelRealmHelper objInstance;
    private Realm mRealm;

    private HotelRealmHelper(){

        mRealm = Realm.getDefaultInstance();
    }

    public static synchronized HotelRealmHelper getInstance(){
        if (objInstance == null){
            objInstance = new HotelRealmHelper();
        }
        return objInstance;
    }

    public void addHotel(RealmList<Hotel> hotels){

        try {

            mRealm.beginTransaction();

            mRealm.copyToRealmOrUpdate(hotels);

            mRealm.commitTransaction();


        }catch (RealmException e){
            e.printStackTrace();
        }

    }

    public List<Hotel> getHotelList(){

        List<Hotel> placesLists = new ArrayList<>();

        try {

            RealmResults<Hotel> results = mRealm.where(Hotel.class).findAll();

            placesLists = results;

        }catch (RealmException e){
            e.printStackTrace();
        }

        return placesLists;
    }

    public List<Hotel> getBarList(String type){

        List<Hotel> placesLists = new ArrayList<>();

        try {

            RealmResults<Hotel> results = mRealm.where(Hotel.class)
                    .equalTo("shopType",type)
                    .findAll();

            placesLists = results;

        }catch (RealmException e){
            e.printStackTrace();
        }

        return placesLists;
    }

    public List<Hotel> getHotelListByType(String type){

        List<Hotel> placesLists = new ArrayList<>();

        try {

            RealmResults<Hotel> results;

            if (type.equals("Select Hotel Type")){

                results  = mRealm.where(Hotel.class).findAll();

            }else {

                results  = mRealm.where(Hotel.class)
                        .equalTo("shopType",type)
                        .findAll();

            }

            placesLists = results;

        }catch (RealmException e){
            e.printStackTrace();
        }

        return placesLists;
    }

    public List<Hotel> getFavouriteList(){

        List<Hotel> placesLists = new ArrayList<>();

        try {

            RealmResults<Hotel> results = mRealm.where(Hotel.class)
                    .equalTo("isSaved", 1)
                    .findAll();

            placesLists = results;

        }catch (RealmException e){
            e.printStackTrace();
        }

        return placesLists;

    }

//    public List<Places> queryedShops(String placeQuery,String type) {
//
//        List<Places> placesList = new ArrayList<>();
//
//        String upperString = placeQuery.substring(0,1).toUpperCase() + placeQuery.substring(1);
//
//        try {
//
//            RealmResults<Places> results = mRealm.where(Places.class)
//                    .equalTo("shopType",type)
//                    .contains("shopCity",upperString, Case.INSENSITIVE)
//                    .or()
//                    .equalTo("shopType",type)
//                    .contains("shopName",upperString, Case.INSENSITIVE)
//                    .findAll();
//
//            placesList = results;
//
//        }catch (RealmException e){
//            e.printStackTrace();
//        }
//
//        return placesList;
//
//    }
//
//    public List<Places> queryed(String placeQuery,String type,String township) {
//
//        List<Places> placesList = new ArrayList<>();
//
//        String upperString = placeQuery.substring(0,1).toUpperCase() + placeQuery.substring(1);
//
//        try {
//
//            RealmResults<Places> results = mRealm.where(Places.class)
//                    .equalTo("shopType",type)
//                    .contains("shopCity",upperString,Case.INSENSITIVE)
//                    .or()
//                    .equalTo("shopType",type)
//                    .contains("shopName",upperString, Case.INSENSITIVE)
//                    .or()
//                    .equalTo("shopType",type)
//                    .equalTo("township",township)
//                    .findAll();
//
//            placesList = results;
//
//        }catch (RealmException e){
//            e.printStackTrace();
//        }
//
//        return placesList;
//
//    }

}
