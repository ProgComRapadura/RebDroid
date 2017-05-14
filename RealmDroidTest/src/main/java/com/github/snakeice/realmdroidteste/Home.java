package com.github.snakeice.realmdroidteste;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.snakeice.realmdroid.DBViewer;
import com.github.snakeice.realmdroidteste.modeltest.Pessoa;
import com.github.snakeice.realmdroidteste.modeltest.SchemaTeste;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Home extends AppCompatActivity {
    //    private DBMetadataCollector collector;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("realm.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .modules(new SchemaTeste())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        realm = Realm.getDefaultInstance();
        ButterKnife.bind(this);
    }


    @OnClick(R.id.bnt_gridView)
    public void gridview() {
        DBViewer.start(this, realm.getConfiguration());
    }

    @OnClick(R.id.btn_add)
    public void add100Data() {
        for (int i = 0; i < 100; i++) {
            Pessoa p = new Pessoa();
            p.setNome("Teste");
            p.setId(new Random().nextLong());
            p.setEndereco("Narnia");
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(p);
            realm.commitTransaction();
        }
    }


}
