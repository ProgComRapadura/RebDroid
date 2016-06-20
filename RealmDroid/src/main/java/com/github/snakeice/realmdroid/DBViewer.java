package com.github.snakeice.realmdroid;

import android.content.Context;
import android.content.Intent;

import io.realm.RealmConfiguration;

/**
 * Show data
 * Created by RodrigoB on 27/05/2016.
 */
public class DBViewer {
    public static String TABLE_NUM = "TaBlENuM123";


    public DBViewer() {
    }

    public static void start(Context context, RealmConfiguration configuration) {
        DBMetadataCollector.mConfiguration = configuration;
        Intent intent = new Intent(context, TablesView.class);
        context.startActivity(intent);
    }
}
