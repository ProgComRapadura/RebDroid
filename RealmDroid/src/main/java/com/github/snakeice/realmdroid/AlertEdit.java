package com.github.snakeice.realmdroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;

/**
 * Alertdialog with data editor
 * Created by RodrigoB on 30/05/2016.
 */
public class AlertEdit {
    private static AlertEdit mAlertEdit;
    private Context mContext;
    private AlertDialog.Builder mBuilder;
    private MultiAutoCompleteTextView mEditText;
    private OnClickListener onClickListener;


    interface OnClickListener {
        void onClick(DialogInterface dialog, int which, Editable text);
    }

    public AlertEdit(Context context) {
        mContext = context;
        mBuilder = new AlertDialog.Builder(context);
    }

    public static AlertEdit Buider(Context context) {
        mAlertEdit = new AlertEdit(context);
        return mAlertEdit;
    }

    public AlertEdit setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public AlertEdit setTitle(String title) {
        mBuilder.setTitle(title);
        return this;
    }

    public AlertEdit setContent(String content) {
        mEditText = new MultiAutoCompleteTextView(mContext);
        mEditText.setText(content);
        return this;
    }


    public void buildAndShow() {
        LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(mEditText);
        mBuilder.setView(layout);
        mBuilder.setCancelable(false);

        mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onClickListener.onClick(dialog, which, mEditText.getText());
            }
        });


        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        mBuilder.show();
    }


}
