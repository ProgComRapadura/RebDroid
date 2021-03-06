package com.github.snakeice.realmdroid;


import android.util.Log;

import com.github.snakeice.realmdroid.models.Column;
import com.github.snakeice.realmdroid.models.TableStructure;
import com.github.snakeice.realmdroid.scrolltable.ScrollTableView;
import com.github.snakeice.realmdroid.utils.L;

import java.util.ArrayList;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmFieldType;
import io.realm.RealmResults;

/**
 * Generate TableView
 * Created by Rodrigo on 12/05/2016.
 */
public class ViewAdapter {
    private ArrayList<String> titles;
    private ArrayList<String> leftTitle;
    private ArrayList<ArrayList<String>> content;
    private RealmResults<DynamicRealmObject> mItens;
    private TableStructure mTable;
    private DynamicRealm realm;
    private ScrollTableView mView;

    public ViewAdapter(TableStructure table, ScrollTableView view) {
        this.mTable = table;
        mView = view;
        realm = DynamicRealm.getInstance(DBMetadataCollector.mConfiguration);
        mItens = realm.where(mTable.getName()).findAll();
        generateData();
    }

    public void generateData() {
        titles = createTitles();
        leftTitle = createLeftTitle();
        content = createContent();
    }

    public ScrollTableView createView() {
        setData();
        return mView;
    }

    private void setData() {
        mView.setDatas(titles, leftTitle, content);
    }

    private ArrayList<String> createTitles() {
        ArrayList<String> results = new ArrayList<>();
        for (Column c : mTable.getColumns()) {
            results.add(c.getName());
        }
        return results;
    }

    private ArrayList<String> createLeftTitle() {
        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < mItens.size(); i++) {
            results.add(String.valueOf(i));
        }
        return results;
    }

    private ArrayList<ArrayList<String>> createContent() {
        int row = mItens.size();
        int column = mTable.getColumns().size();
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            ArrayList<String> strings = new ArrayList<>();
            DynamicRealmObject object = realm.where(mTable.getName()).findAll().get(i);
            for (int j = 0; j < column; j++) {
                String cel = "";
                try {
                    if (object.get(mTable.getColumns().get(j).getName()) != null)
                        if (mTable.getColumns().get(j).getType() == RealmFieldType.LIST) {
                            cel = "RealmList<" + object.getList(mTable.getColumns().get(j).getName()).first().getType() + '>';
                        } else {
                            cel = object.get(mTable.getColumns().get(j).getName()).toString();
                        }
                } catch (NullPointerException e) {
                    L.e("Except > row size: " + row + " column size: " + column + "   " + i + "/" + j);
                }
                strings.add(cel);
            }
            results.add(strings);
        }
        return results;
    }


    public boolean isNotEmpty() {
        return (!mItens.isEmpty());
    }

    public ScrollTableView getView() {
        return mView;
    }

    public void refreshView() {
        generateData();
        setData();
    }
}
