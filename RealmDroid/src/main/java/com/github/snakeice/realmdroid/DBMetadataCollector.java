package com.github.snakeice.realmdroid;

import android.util.Log;

import com.github.snakeice.realmdroid.models.Column;
import com.github.snakeice.realmdroid.models.TableStructure;
import com.github.snakeice.realmdroid.scrolltable.Position;
import com.github.snakeice.realmdroid.utils.L;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmConfiguration;
import io.realm.RealmObjectSchema;

/**
 * Load metadada
 * Created by Rodrigo on 12/05/2016.
 */
public class DBMetadataCollector {
    public static RealmConfiguration mConfiguration;
    private List<TableStructure> mTables;
    private DynamicRealm mRealm;
    private Boolean isLoaded = Boolean.FALSE;
    private TableStructure mActiveTable;


    public DBMetadataCollector() {
        mTables = new ArrayList<>();
        if (mConfiguration == null)
            throw new NullPointerException("RealmConfiguration is null");
        this.mRealm = DynamicRealm.getInstance(mConfiguration);
        execute();
    }

    private void execute() {
        loadTables();
    }

    public TableStructure getActiveTable() {
        return mActiveTable;
    }

    public void setActiveTable(TableStructure mActiveTable) {
        this.mActiveTable = mActiveTable;
    }

    public void setActiveTable(Integer pos) {
        this.mActiveTable = mTables.get(pos);
    }

    public List<TableStructure> getmTables() {
        return mTables;
    }

    public List<String> getTablesName() {
        List<String> names = new ArrayList<>();
        for (TableStructure table : mTables) {
            names.add(table.getName());
        }
        return names;
    }

    private List<TableStructure> loadTables() {
        if (!isLoaded) {
            for (RealmObjectSchema tables : mRealm.getSchema().getAll()) {
                TableStructure table = new TableStructure();
                table.setName(tables.getClassName());
                List<Column> columns = new ArrayList<>();
                for (String f : tables.getFieldNames()) {
                    Column column = new Column();
                    column.setName(f);
                    column.setRequired(tables.isRequired(f));
                    if (tables.hasPrimaryKey()) {
                        column.setPrimaryKey(tables.isPrimaryKey(f));
                    }
                    column.setType(tables.getFieldType(f));
                    columns.add(column);
                }
                table.setColumns(columns);
                this.mTables.add(table);
            }
            String s = new Gson().toJson(mTables);
            L.i("execute: " + s);
        }
        return mTables;
    }

    public <S> S getValue(Position position) {
        return mRealm.where(mActiveTable.getName()).findAll().get(position.y)
                .get(mActiveTable.getColumns().get(position.x).getName());
    }

    public DynamicRealmObject getRow(Position position) {
        return mRealm.where(mActiveTable.getName()).findAll().get(position.y);
    }

    public void alterValue(final Position position, final String s) {
        mRealm.executeTransaction(new DynamicRealm.Transaction() {
            @Override
            public void execute(DynamicRealm realm) {
                DynamicRealmObject dynamicRealmObject = realm.where(mActiveTable.getName())
                        .findAll().get(position.y);
                dynamicRealmObject.set(mActiveTable.getColumns().get(position.x).getName(),s);
            }
        });

    }
}
