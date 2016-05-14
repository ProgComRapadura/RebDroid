package com.github.snakeice.realmdroid;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.snakeice.realmdroid.modelsTest.DB;
import com.github.snakeice.realmdroid.modelsTest.Empresa;
import com.github.snakeice.realmdroid.modelsTest.Pessoa;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class Home extends AppCompatActivity {
    private DBMetadataCollector collector;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        realm = DB.getRealm(this);

        collector = new DBMetadataCollector(realm);
        collector.execute();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_loaddata)
    public void collector() {
        collector.execute();
    }

    @OnClick(R.id.bnt_gridView)
    public void gridview() {
        realm.close();
        Intent grid = new Intent(this, DataViewerActivity.class);
        startActivity(grid);
    }

    @OnClick(R.id.btn_add)
    public void add100Data() {
        for (int i = 0; i < 100; i++) {
            Empresa e = new Empresa();
            e.setCnpj("12345678909876543");
            e.setId(new Random().nextLong());
            e.setNome("Jacinto");
            Pessoa p = new Pessoa();
            p.setNome("Teste");
            p.setId(new Random().nextLong());
            p.setEndereco("Narnia");
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(e);
            realm.copyToRealmOrUpdate(p);
            realm.commitTransaction();
        }
    }


}
