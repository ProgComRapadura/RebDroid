package com.github.snakeice.realmdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.snakeice.realmdroid.modelsTest.DB;
import com.loopeer.android.librarys.scrolltable.ScrollTableView;

public class DataViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_viewer);
        ScrollTableView scrollTableView = (ScrollTableView) findViewById(R.id.scroll_table_view);
        DBMetadataCollector db = new DBMetadataCollector(DB.getConfigurationTest(this));
        db.execute();
        ViewAdapter va = new ViewAdapter(db.tables.get(0), scrollTableView, DB.getConfigurationTest(this));
        va.createView();
    }
}
