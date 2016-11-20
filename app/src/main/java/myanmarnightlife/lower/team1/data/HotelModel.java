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
 * Created by winthanhtike on 11/18/16.
 */
public class HotelModel {

    private static final String HOTEL_LIST = "hotel.json";

    private static HotelModel objInstance;

    private List<Hotel> mHotelsList;

    public static HotelModel getInstance(){
        if (objInstance == null){
            objInstance = new HotelModel();
        }
        return objInstance;
    }

    private HotelModel(){
        mHotelsList = initializeHotelsList();
    }

    public List<Hotel> hotelsList() {
        Collections.sort(mHotelsList, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel lhs, Hotel rhs) {
                return lhs.getShopName().compareToIgnoreCase(rhs.getShopName());
            }
        });

        return mHotelsList;
    }

    private List<Hotel> initializeHotelsList(){

        List<Hotel> places = new ArrayList<>();

        try {
            String basicItem = JsonUtils.getInstance().loadBasicItemData(HOTEL_LIST);
            Type listType = new TypeToken<List<Hotel>>(){
            }.getType();
            places = CommonInstances.getGsonInstance().fromJson(basicItem, listType);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return places;
    }

}
