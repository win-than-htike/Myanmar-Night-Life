package myanmarnightlife.lower.team1.helper;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.exceptions.RealmException;
import myanmarnightlife.lower.team1.data.Places;

/**
 * Created by winthanhtike on 10/2/16.
 */
public class PlacesRealmHelper {

    private static PlacesRealmHelper objInstance;
    private Realm mRealm;
    private int id = 1;

    private PlacesRealmHelper(){

        mRealm = Realm.getDefaultInstance();
    }

    public static synchronized PlacesRealmHelper getInstance(){
        if (objInstance == null){
            objInstance = new PlacesRealmHelper();
        }
        return objInstance;
    }

    public void addPlaces(Places places){

        try {

            mRealm.beginTransaction();

            places.set_id(id);

            mRealm.copyToRealmOrUpdate(places);

            mRealm.commitTransaction();

            id++;


        }catch (RealmException e){
            e.printStackTrace();
        }

    }

    public List<Places> getBarList(String type){

        List<Places> placesLists = new ArrayList<>();

        try {

            RealmResults<Places> results = mRealm.where(Places.class)
                                            .equalTo("shopType",type)
                                            .findAll();

            placesLists = results;

        }catch (RealmException e){
            e.printStackTrace();
        }

        return placesLists;
    }

    public List<Places> getFavouriteList(){

        List<Places> placesLists = new ArrayList<>();

        try {

            RealmResults<Places> results = mRealm.where(Places.class)
                    .equalTo("isSaved", 1)
                    .findAll();

            placesLists = results;

        }catch (RealmException e){
            e.printStackTrace();
        }

        return placesLists;

    }

    public List<Places> queryedBooks(String placeType) {

        List<Places> placesList = new ArrayList<>();

        try {

            RealmResults<Places> results = mRealm.where(Places.class)
                    .contains("placeType", placeType)
                    .findAll();

            placesList = results;

        }catch (RealmException e){
            e.printStackTrace();
        }

        return placesList;

    }


}
