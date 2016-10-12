package myanmarnightlife.lower.team1;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by winthanhtike on 10/11/16.
 */
public class MyanmarNightLifeApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(MyanmarNightLifeApp.getContext())
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }

    public static Context getContext() {
        return context;
    }
}
