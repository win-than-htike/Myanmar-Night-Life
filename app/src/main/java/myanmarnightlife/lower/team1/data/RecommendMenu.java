package myanmarnightlife.lower.team1.data;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

import io.realm.RealmObject;
import io.realm.RecommendMenuRealmProxy;

/**
 * Created by winthanhtike on 11/16/16.
 */
@Parcel(implementations = { RecommendMenuRealmProxy.class }, value = Parcel.Serialization.BEAN, analyze = { RecommendMenu.class })
public class RecommendMenu extends RealmObject {

    @SerializedName("name")
    String menuName;

    public RecommendMenu() {
    }

    @ParcelConstructor
    public RecommendMenu(@ParcelProperty("name") String menuName) {
        this.menuName = menuName;
    }

    @ParcelProperty("name")
    public String getMenuName() {
        return menuName;
    }
}
