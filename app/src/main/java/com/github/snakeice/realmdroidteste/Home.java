package com.github.snakeice.realmdroidteste;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.snakeice.realmdroid.DBViewer;
import com.github.snakeice.realmdroidteste.modeltest.DB;
import com.github.snakeice.realmdroidteste.modeltest.Empresa;
import com.github.snakeice.realmdroidteste.modeltest.Pessoa;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class Home extends AppCompatActivity {
    //    private DBMetadataCollector collector;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        realm = DB.getRealm(this);

//        collector = new DBMetadataCollector(realm);
//        collector.execute();
        ButterKnife.bind(this);
    }


    @OnClick(R.id.bnt_gridView)
    public void gridview() {
        DBViewer.start(this, realm.getConfiguration());
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
