package com.github.snakeice.realmdroid;

import com.github.snakeice.realmdroid.models.Column;
import com.github.snakeice.realmdroid.models.TableStructure;
import com.loopeer.android.librarys.scrolltable.ScrollTableView;

import java.util.ArrayList;

import io.realm.DynamicRealm;
import io.realm.RealmConfiguration;

/**
 * Generate TableView
 * Created by Rodrigo on 12/05/2016.
 */
public class ViewAdapter {
    private TableStructure mTable;
    private DynamicRealm realm;
    private ScrollTableView mView;
    ArrayList<String> titles;
    ArrayList<String> leftTitle;
    ArrayList<ArrayList<String>> content;

    public ViewAdapter(TableStructure table, ScrollTableView view, RealmConfiguration configuration) {
        this.mTable = table;
        mView = view;
        realm = DynamicRealm.getInstance(configuration);
        generateData();
    }

     void generateData() {
        titles = createTitles();
        leftTitle = createLeftTitle();
        content = createContent();
    }

    public ScrollTableView createView() {
        mView.setDatas(createTitles(), createLeftTitle(), createContent());
        return mView;
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
        for (int i = 0; i < realm.where(mTable.getName()).findAll().size(); i++) {
            results.add(String.valueOf(i));
        }
        return results;
    }

    private ArrayList<ArrayList<String>> createContent() {
        int row = realm.where(mTable.getName()).findAll().size();
        int column = mTable.getColumns().size();
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            ArrayList<String> strings = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                String cel;
                cel = realm.where(mTable.getName()).findAll().get(i)
                        .getString(mTable.getColumns().get(j).getName());
                strings.add(cel);
            }
            results.add(strings);
        }
        return results;
    }


}
