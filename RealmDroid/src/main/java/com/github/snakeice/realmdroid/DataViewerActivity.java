package com.github.snakeice.realmdroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;

import com.github.snakeice.realmdroid.scrolltable.Position;
import com.github.snakeice.realmdroid.scrolltable.ScrollTableView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.DynamicRealmObject;
import io.realm.ObjectChangeSet;
import io.realm.RealmList;

public class DataViewerActivity extends Activity implements ScrollTableView.ScrollTableListener {
    private DBMetadataCollector mDBController;
    private ViewAdapter va;

    public void alertWithButton(String title, String msg, final Activity activity,
                                 SweetAlertDialog.OnSweetClickListener onClickListener) {
        new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText(title)
                .setContentText(msg)
                .setConfirmClickListener(onClickListener)
                .setConfirmText("OK")
                .show();
    }

    public void alert(String title, String msg, Activity activity) {
        new SweetAlertDialog(activity)
                .setTitleText(title)
                .setContentText(msg)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_viewer);
        Bundle bundle = getIntent().getExtras();
        mDBController = new DBMetadataCollector();
        mDBController.setActiveTable(bundle.getInt(DBViewer.TABLE_NUM, 0));
        ScrollTableView scrollTableView = (ScrollTableView) findViewById(R.id.scroll_table_view);
        scrollTableView.setListener(this);
        va = new ViewAdapter(mDBController.getActiveTable(), scrollTableView);
        if (va.isNotEmpty()) {
            va.createView();
        } else {
            alertWithButton("Notice", "Empty table!", this,
                    new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            DataViewerActivity.this.finish();
                        }
                    });
        }
    }




    @Override
    public void onItemClickListener(final Position position) {
        final Object value = mDBController.getValue(position);
        if (value.getClass().equals(RealmList.class)) {
            alert("Value", "RealmList<" +
                            ((DynamicRealmObject) ((RealmList) value).first()).getType() + ">",
                    DataViewerActivity.this);
        } else {
            AlertEdit.buider(this)
                    .setTitle(mDBController.getActiveTable().getColumns().get(position.x).getName())
                    .setContent(value.toString())
                    .setOnClickListener(new AlertEdit.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, Editable text) {
                            mDBController.alterValue(position, text.toString());
                            va.generateData();
                            va.refreshView();
                        }
                    }).buildAndShow();
        }
    }

    @Override
    public void onLongItemClickListener(final Position position) {
        final Object value = mDBController.getValue(position);
        if (!value.getClass().equals(RealmList.class)){
         new AlertDialog.Builder(this)
                 .setTitle("Confirm")
                 .setMessage("Delete row?")
                 .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         mDBController.deleteRow(position);
                         va.refreshView();
                     }
                 }).setNegativeButton("No", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         }).show();
        }
    }
}
