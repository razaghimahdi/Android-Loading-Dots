package com.razzaghimahdi78.dotsloading.core;

import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_COLOR;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DOTS_COUNT;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DOTS_SIZE;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DURATION;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public abstract class BaseLinearLoading extends LinearLayout {


    private int DOTS_SIZE = DEFAULT_DOTS_SIZE;
    private int DOTS_COUNT = DEFAULT_DOTS_COUNT;
    private int COLOR = DEFAULT_COLOR;

    public BaseLinearLoading(Context context) {
        super(context);
    }

    public BaseLinearLoading(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseLinearLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void initView(
            Context context,
            @Nullable AttributeSet attrs,
            int dotsSize,
            int dotsCount,
            int color
    ) {

        DOTS_SIZE = dotsSize;
        DOTS_COUNT = dotsCount;
        COLOR = color;

        adjustView();

        removeAllViews();

        addDots();


    }

    private void adjustView() {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        setBackgroundColor(Color.TRANSPARENT);
    }

    private void addDots() {


        LayoutParams layoutParams2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < DOTS_COUNT; i++) {
            CircleView circleView = new CircleView(getContext(), DOTS_SIZE, COLOR, true);
            LinearLayout rel = new LinearLayout(getContext());
            layoutParams2.setMargins(DOTS_SIZE / 2, DOTS_SIZE / 2, DOTS_SIZE / 2, DOTS_SIZE / 2);
            rel.setGravity(Gravity.CENTER);
            rel.addView(circleView);
            addView(rel, layoutParams2);
        }
    }



}
