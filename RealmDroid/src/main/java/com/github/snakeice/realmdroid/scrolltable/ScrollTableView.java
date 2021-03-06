package com.github.snakeice.realmdroid.scrolltable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.github.snakeice.realmdroid.R;

import java.util.ArrayList;

public class ScrollTableView extends LinearLayout implements CustomTableView.OnPositionClickListener {

    private LeftTitleView headerVertical;
    private TopTitleView headerHorizontal;
    private CustomTableView contentView;
    private ArrayList<Position> selectPositions;
    private ArrayList<String> topTitles;
    private ArrayList<String> leftTitles;
    private ArrayList<ArrayList<String>> datas;
    private ScrollTableListener mListener;

    public ScrollTableView(Context context) {
        this(context, null);
    }

    public ScrollTableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("NewApi")
    public ScrollTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.view_container, this);
        setUpView();
        setUpData();
        selectPositions = new ArrayList<>();


        if (attrs == null) return;
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScrollTableView, defStyleAttr, 0);
        if (a == null) return;

        headerVertical.setUpAttrs(context, attrs, defStyleAttr);
        headerHorizontal.setUpAttrs(context, attrs, defStyleAttr);
        contentView.setUpAttrs(context, attrs, defStyleAttr);

        a.recycle();
    }

    private void setUpView() {
        IHorizontalScrollView scrollHeaderHorizontal = findViewById(R.id.scroll_header_horizontal);
        IHorizontalScrollView scrollHorizontal = findViewById(R.id.scroll_horizontal);
        headerVertical = findViewById(R.id.header_vertical);
        headerHorizontal = findViewById(R.id.header_horizontal);
        contentView = findViewById(R.id.content_view);
        scrollHorizontal.setIScroller(scrollHeaderHorizontal);
        scrollHeaderHorizontal.setIScroller(scrollHorizontal);
    }

    public LeftTitleView getLeftTitleView() {
        return headerVertical;
    }

    public TopTitleView getTopTitleView() {
        return headerHorizontal;
    }

    public CustomTableView getContentView() {
        return contentView;
    }

    private void setUpData() {
        leftTitles = new ArrayList<>();
        topTitles = new ArrayList<>();
        datas = new ArrayList<>();
        contentView.setRowAndColumn(leftTitles.size(), topTitles.size());
        contentView.setOnPositionChangeListener(this);
    }

    public void setDatas(ArrayList<String> topTitlesData, ArrayList<String> leftTitlesData, ArrayList<ArrayList<String>> itemData) {
        topTitles.clear();
        leftTitles.clear();
        datas.clear();
        topTitles.addAll(topTitlesData);
        leftTitles.addAll(leftTitlesData);
        datas.addAll(itemData);
        updateView();
    }

    private void updateView() {
        ArrayList<Float> tamanhosColunas = Utils.getMaxStringSizeArray(getContext(), datas, topTitles);
        headerVertical.updateTitles(leftTitles);
        headerHorizontal.updateTitles(topTitles, tamanhosColunas);
        contentView.setRowAndColumn(leftTitles.size(), topTitles.size());
        contentView.setDatas(datas, tamanhosColunas);
    }

    @Override
    public void onPositionClick(Position position) {
        if (mListener != null) {
            mListener.onItemClickListener(position);
        } else {
            if (selectPositions.contains(position)) {
                selectPositions.remove(position);
            } else {
                selectPositions.add(position);
            }
            contentView.setSelectPositions(selectPositions);
        }
    }

    @Override
    public void onLongPositionClick(Position position) {
        if(mListener != null) mListener.onLongItemClickListener(position);
    }

    public void setListener(ScrollTableListener mListener) {
        this.mListener = mListener;
    }

    public interface ScrollTableListener {
        void onItemClickListener(Position position);

        void onLongItemClickListener(Position position);
    }

}
