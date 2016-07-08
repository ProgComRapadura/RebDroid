package com.github.snakeice.realmdroidteste.modeltest;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Test
 * Created by Rodrigo on 12/05/2016.
 */
public class MigrationTest implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        Log.i("Realm -> Migration", "Version: " + oldVersion + " to " + newVersion);
    }
}
