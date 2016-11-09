package myanmarnightlife.lower.team1;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import myanmarnightlife.lower.team1.font.CustomFont;

/**
 * Created by winthanhtike on 10/11/16.
 */
public class MyanmarNightLifeApp extends Application {

  private static Context context;

  @Override public void onCreate() {
    super.onCreate();

    context = getApplicationContext();

    Firebase.setAndroidContext(context);

    RealmConfiguration realmConfiguration =
        new RealmConfiguration.Builder(MyanmarNightLifeApp.getContext()).name(
            Realm.DEFAULT_REALM_NAME).schemaVersion(0).deleteRealmIfMigrationNeeded().build();
    Realm.setDefaultConfiguration(realmConfiguration);
    CustomFont customFontFamily = CustomFont.getInstance();
    customFontFamily.addFont("regular", "AvenirNext-Regular.ttf");
    customFontFamily.addFont("thin", "Roboto-Light.ttf");
    customFontFamily.addFont("bold", "AvenirNext-DemiBold.ttf");
    customFontFamily.addFont("medium", "AvenirNext-Medium.ttf");
  }

  public static Context getContext() {
    return context;
  }
}
