package myanmarnightlife.lower.team1.utils;

import com.google.gson.Gson;

/**
 * Created by winthanhtike on 7/7/16.
 */
public class CommonInstances {

    private static Gson gson = new Gson();

    public static Gson getGsonInstance() {
        return gson;
    }
}
