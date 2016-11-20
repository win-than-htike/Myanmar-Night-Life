package myanmarnightlife.lower.team1.data;

import java.util.List;

import io.realm.RealmList;
import myanmarnightlife.lower.team1.helper.HotelRealmHelper;

/**
 * Created by winthanhtike on 11/18/16.
 */
public class HotelData {

    private static final String HOTEL_LIST = "hotel.json";
    private static List<Hotel> hotels;


    public static void getData(){

        HotelRealmHelper helper = HotelRealmHelper.getInstance();
        hotels = HotelModel.getInstance().hotelsList();
        helper.addHotel(new RealmList<Hotel>(hotels.toArray(new Hotel[hotels.size()])));


    }

}
