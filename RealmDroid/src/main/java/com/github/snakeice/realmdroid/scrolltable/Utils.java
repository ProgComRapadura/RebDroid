package com.github.snakeice.realmdroid.scrolltable;

import android.content.Context;
import android.graphics.Paint;

import com.github.snakeice.realmdroid.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Get max column size
 * Created by RodrigoB on 27/05/2016.
 */
public class Utils {
    public static ArrayList<Float> getMaxStringSizeArray(Context context, ArrayList<ArrayList<String>> itemData, ArrayList<String> topTitles) {
        Float[] sizes = new Float[itemData.get(0).size()];
        Paint mPaintTextNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
        float mTextNormal = context.getResources().getDimension(R.dimen.table_default_text_size);
        float mTextMargin = context.getResources().getDimension(R.dimen.table_item_margin);
        mPaintTextNormal.setTextSize(mTextNormal);
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 0F;
        }
        for (ArrayList<String> column : itemData) {
            for (int i = 0; i < column.size(); i++) {
                if (mPaintTextNormal.measureText(column.get(i)) + mTextMargin > sizes[i]) {
                    sizes[i] = mPaintTextNormal.measureText(column.get(i)) + mTextMargin;
                }
            }
        }
        for (int i = 0; i < topTitles.size(); i++) {
            if (mPaintTextNormal.measureText(topTitles.get(i)) + mTextMargin > sizes[i]) {
                sizes[i] = mPaintTextNormal.measureText(topTitles.get(i)) + mTextMargin;
            }
        }
        ArrayList<Float> result = new ArrayList<>();
        Collections.addAll(result, sizes);
        return result;
    }
}
