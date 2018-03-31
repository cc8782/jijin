package com.example.app.db;

import java.util.List;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObject;

/**
 * Created by rainL on 2015-11-16.
 */
public class DBHelper {

    protected Realm realm;
    private static RealmConfiguration realmConfiguration;
    private static final RealmMigration migration = new RealmMigration() {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        }
    };

    public DBHelper(Realm realm) {
        this.realm = realm;
    }

    public static Realm getRealm() {
        Realm realm=Realm.getInstance(getRealmConfiguration());

        return realm;
    }

    public static RealmConfiguration getRealmConfiguration() {

        String realmFilename = "jijin.realm";
        if (realmConfiguration != null && realmFilename.equals(realmConfiguration.getRealmFileName())) {
            return realmConfiguration;
        }

        realmConfiguration = new RealmConfiguration.Builder()
                .name(realmFilename)
                .schemaVersion(0)
                .migration(migration)
                .build();
        return realmConfiguration;
    }

    public <T extends RealmObject> void saveOrUpdate(T model) {
        Realm realm = getRealm();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
        realm.close();
    }

    public <T extends RealmObject> void saveOrUpdate(List<T> model) {
        Realm realm = getRealm();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
        realm.close();
    }
}
