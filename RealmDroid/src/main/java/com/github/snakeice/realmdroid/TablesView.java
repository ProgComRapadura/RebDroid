package com.github.snakeice.realmdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class TablesView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables_view);
        ListView lv = findViewById(R.id.lv_tables);
        DBMetadataCollector db = new DBMetadataCollector();
        assert lv != null;
        lv.setAdapter(new ArrayAdapter<>(this, R.layout.list_table_item_text, db.getTablesName()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(TablesView.this, DataViewerActivity.class);
                it.putExtra(DBViewer.TABLE_NUM, position);
                startActivity(it);
            }
        });
    }
}
