package myanmarnightlife.lower.team1;

import android.app.Application;
import android.content.Context;

/**
 * Created by winthanhtike on 10/11/16.
 */
public class MyanmarNightLifeApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }

    public static Context getContext() {
        return context;
    }
}
