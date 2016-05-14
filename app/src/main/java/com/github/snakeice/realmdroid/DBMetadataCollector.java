package com.github.snakeice.realmdroid;

import android.util.Log;

import com.github.snakeice.realmdroid.models.Column;
import com.github.snakeice.realmdroid.models.TableStructure;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObjectSchema;

/**
 * Carrega os dados do banco
 * Created by Rodrigo on 12/05/2016.
 */
public class DBMetadataCollector {
    private Realm mRealm;
    private Boolean isLoaded = Boolean.FALSE;
    List<TableStructure> tables;

    public DBMetadataCollector(RealmConfiguration configuration) {
        tables = new ArrayList<>();
        this.mRealm = Realm.getInstance(configuration);
    }

    public DBMetadataCollector(Realm realm) {
        tables = new ArrayList<>();
        this.mRealm = realm;
    }

    public void execute() {
        loadTables();
        String s = new Gson().toJson(tables);
        Log.e(this.toString(), "execute: " + s);
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
                this.tables.add(table);
            }
        }
        return tables;
    }
}
