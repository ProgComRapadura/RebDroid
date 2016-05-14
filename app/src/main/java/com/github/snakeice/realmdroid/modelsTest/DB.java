package com.github.snakeice.realmdroid.modelsTest;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Rodrigo on 12/05/2016.
 */
public class DB {
public static RealmConfiguration getConfiguration(Context context){
        return new RealmConfiguration.Builder(context)
                .name("realm.realm")
//                .encryptionKey()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .modules(new SchemaTeste())
                .build();
    }

    public static RealmConfiguration getConfigurationTest(Context context){
        return new RealmConfiguration.Builder(context)
                .name("realm.realm")
//                .encryptionKey()
                .schemaVersion(1)
                .build();
    }


    public static Realm getRealm(Context context){
        return Realm.getInstance(getConfiguration(context));
    }
}
