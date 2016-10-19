package myanmarnightlife.lower.team1.data;

import java.util.List;

import io.realm.RealmList;
import myanmarnightlife.lower.team1.helper.PlacesRealmHelper;

/**
 * Created by winthanhtike on 10/2/16.
 */
public class PlacesData {

    private static final String PLACES_LIST = "places.json";
    private static List<Places> places;


    public static void getData(){

        PlacesRealmHelper helper = PlacesRealmHelper.getInstance();
        places = PlacesModel.getInstance().placesList();
        helper.addPlaces(new RealmList<Places>(places.toArray(new Places[places.size()])));


    }

}
