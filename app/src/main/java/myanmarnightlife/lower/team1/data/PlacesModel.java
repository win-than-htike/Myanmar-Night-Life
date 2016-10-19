package myanmarnightlife.lower.team1.data;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import myanmarnightlife.lower.team1.utils.CommonInstances;
import myanmarnightlife.lower.team1.utils.JsonUtils;

/**
 * Created by winthanhtike on 10/17/16.
 */
public class PlacesModel {

    private static final String AGILITY_HERO_LIST = "places.json";

    private static PlacesModel objInstance;

    private List<Places> mPlacesList;

    public static PlacesModel getInstance(){
        if (objInstance == null){
            objInstance = new PlacesModel();
        }
        return objInstance;
    }

    private PlacesModel(){
        mPlacesList = initializeStrengthHeroList();
    }

    public List<Places> placesList() {
        Collections.sort(mPlacesList, new Comparator<Places>() {
            @Override
            public int compare(Places lhs, Places rhs) {
                return lhs.getShopName().compareToIgnoreCase(rhs.getShopName());
            }
        });

        return mPlacesList;
    }

    private List<Places> initializeStrengthHeroList(){

        List<Places> places = new ArrayList<>();

        try {
            String basicItem = JsonUtils.getInstance().loadBasicItemData(AGILITY_HERO_LIST);
            Type listType = new TypeToken<List<Places>>(){
            }.getType();
            places = CommonInstances.getGsonInstance().fromJson(basicItem, listType);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return places;
    }


}
