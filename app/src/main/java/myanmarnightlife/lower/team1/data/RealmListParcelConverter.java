package myanmarnightlife.lower.team1.data;

import org.parceler.converter.CollectionParcelConverter;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by winthanhtike on 11/16/16.
 */
public abstract class RealmListParcelConverter<T extends RealmObject> extends CollectionParcelConverter<T, RealmList<T>> {

    @Override
    public RealmList<T> createCollection() {
        return new RealmList<T>();
    }

}
