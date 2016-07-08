package com.github.snakeice.realmdroid.scrolltable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.github.snakeice.realmdroid.R;

import java.util.ArrayList;


public class TopTitleView extends View {

    private Paint mPaintTextNormal;
    private int mTextTopTitleColor;
    private float mTextNormal;

    private int mItemHeight;
    private int mItemPlaceHeight;
    private int mItemWidth;
    private int mItemMargin;
    private int column;
    private ArrayList<Float> mTamanhosColunas;

    private ArrayList<String> titles;

    public TopTitleView(Context context) {
        this(context, null);
    }

    public TopTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initData();
        initPaint();
        titles = new ArrayList<>();
    }

    private void initData() {
        mItemHeight = getResources().getDimensionPixelSize(R.dimen.table_item_height);
        mItemWidth = getResources().getDimensionPixelSize(R.dimen.table_item_width);
        mItemMargin = getResources().getDimensionPixelSize(R.dimen.table_item_margin);
        mItemPlaceHeight = getResources().getDimensionPixelSize(R.dimen.table_top_title_place);
    }

    private void initPaint() {
        mTextTopTitleColor = ContextCompat.getColor(getContext(), R.color.table_text_secondary_color);
        mTextNormal = getResources().getDimension(R.dimen.table_default_title_size);

        mPaintTextNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintTextNormal.setColor(mTextTopTitleColor);
        mPaintTextNormal.setTextSize(mTextNormal);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mItemHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (mTamanhosColunas != null) {
            Float acumulador = 0F;
            for (int i = 0; i < mTamanhosColunas.size(); i++) {
                acumulador += mTamanhosColunas.get(i);
            }
            setMeasuredDimension((int) (acumulador + (column - 1) * mItemMargin), mItemHeight);
        } else {
            setMeasuredDimension(column * mItemWidth + (column - 1) * mItemMargin, mItemHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawItem(canvas);
    }


    private void drawItem(Canvas canvas) {
        Float LastRight = 0F;
        for (int columnIndex = 0; columnIndex < column; columnIndex++) {
            String content = titles.get(columnIndex);
            Paint.FontMetrics fontMetrics = mPaintTextNormal.getFontMetrics();
            float fontHeight = fontMetrics.bottom - fontMetrics.top;
            float textWidth = mPaintTextNormal.measureText(content);
            float y = mItemPlaceHeight - (mItemPlaceHeight - fontHeight) / 2 - fontMetrics.bottom;
            if (mTamanhosColunas != null) {
                float x = (mTamanhosColunas.get(columnIndex) + mItemMargin * columnIndex / 2 - textWidth / 2) / 2 + LastRight + (textWidth / 2);
                LastRight += (mTamanhosColunas.get(columnIndex) + mItemMargin * columnIndex / 2 - textWidth / 2 + mItemMargin);
                canvas.drawText(content, x, y, mPaintTextNormal);
            } else {
                float x = (mItemMargin + mItemWidth) * columnIndex + mItemWidth / 2 - textWidth / 2;
                canvas.drawText(content, x, y, mPaintTextNormal);
            }
        }
    }

    public void updateTitles(ArrayList<String> data, ArrayList<Float> tamanhosColunas) {
        titles.clear();
        mTamanhosColunas = tamanhosColunas;
        titles.addAll(data);
        updateView();
    }

    private void updateView() {
        invalidate();
        column = titles.size();
    }

    public void setItemHeight(int height) {
        mItemHeight = height;
        invalidate();
    }

    public void setPlaceHeight(int height) {
        mItemPlaceHeight = height;
        invalidate();
    }

    public void setItemWidth(int width) {
        mItemWidth = width;
        invalidate();
    }

    public void setItemMargin(int margin) {
        mItemMargin = margin;
        invalidate();
    }

    public void setTextTopTitleColor(int color) {
        mTextTopTitleColor = color;
        mPaintTextNormal.setColor(mTextTopTitleColor);
        invalidate();
    }

    public void setPaintTextNormalSize(float size) {
        mTextNormal = size;
        mPaintTextNormal.setTextSize(mTextNormal);
        invalidate();
    }

    public void setUpAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs == null) return;
        @SuppressLint("Recycle")
        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ScrollTableView, defStyleAttr, 0);
        if (a == null) return;

        setItemHeight(a.getDimensionPixelSize(R.styleable.ScrollTableView_itemHeight, mItemHeight));
        setItemWidth(a.getDimensionPixelSize(R.styleable.ScrollTableView_itemWidth, mItemWidth));
        setItemMargin(a.getDimensionPixelSize(R.styleable.ScrollTableView_dataMargin, mItemMargin));
        setPlaceHeight(a.getDimensionPixelSize(R.styleable.ScrollTableView_topPlaceHeight, mItemPlaceHeight));
        setTextTopTitleColor(a.getColor(R.styleable.ScrollTableView_textTopTitleColor, mTextTopTitleColor));
        setPaintTextNormalSize(a.getDimension(R.styleable.ScrollTableView_textTitleSize, mTextNormal));

    }
}
