package com.razzaghimahdi78.dotsloading.core;

import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_CIRCLE_DOTS_COUNT;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_COLOR;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DOTS_SIZE;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DURATION;
import static com.razzaghimahdi78.dotsloading.core.Constant.SIN_45;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public abstract class BaseCircleLoading extends FrameLayout {

    private Float[] dotsXCorArr;
    private Float[] dotsYCorArr;

    private int DOTS_SIZE = DEFAULT_DOTS_SIZE;
    protected final int DOTS_COUNT = DEFAULT_CIRCLE_DOTS_COUNT;
    private int COLOR = DEFAULT_COLOR;

    public BaseCircleLoading(Context context) {
        super(context);
    }

    public BaseCircleLoading(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseCircleLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int calWidthHeight = (int) (2 * (DOTS_SIZE * 2.5) + 2 * (DOTS_SIZE * 2.5));
        setMeasuredDimension(calWidthHeight, calWidthHeight);
    }

    protected void initView(
            Context context,
            @Nullable AttributeSet attrs,
            int dotsSize,
            int color
    ) {

        DOTS_SIZE = dotsSize;
        COLOR = color;

        adjustView();


        removeAllViews();

        addDots();


    }

    private void addDots() {

        LayoutParams layoutParams2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        for (int i = 0; i < DOTS_COUNT; i++) {
            CircleView circleView = new CircleView(getContext(), DOTS_SIZE, COLOR, true);
            LinearLayout rel = new LinearLayout(getContext());


            rel.setX(dotsXCorArr[i]);
            rel.setY(dotsYCorArr[i]);

            rel.setGravity(Gravity.CENTER);
            rel.addView(circleView);
            addView(rel, layoutParams2);
        }
    }

    private void adjustView() {
        initCordinates();


        setBackgroundColor(Color.TRANSPARENT);
    }

    private void initCordinates() {
        float sin45Radius = SIN_45 * (DOTS_SIZE * 3);
        dotsXCorArr = new Float[DOTS_COUNT];
        dotsYCorArr = new Float[DOTS_COUNT];

        for (int i = 0; i < DOTS_COUNT; i++) {
            dotsYCorArr[i] = (float) ((DOTS_SIZE * 3) + DOTS_SIZE);
            dotsXCorArr[i] = dotsYCorArr[i];

        }

        dotsXCorArr[1] = dotsXCorArr[1] + sin45Radius;
        dotsXCorArr[2] = dotsXCorArr[2] + (DOTS_SIZE * 3);
        dotsXCorArr[3] = dotsXCorArr[3] + sin45Radius;

        dotsXCorArr[5] = dotsXCorArr[5] - sin45Radius;
        dotsXCorArr[6] = dotsXCorArr[6] - (DOTS_SIZE * 3);
        dotsXCorArr[7] = dotsXCorArr[7] - sin45Radius;

        dotsYCorArr[0] = dotsYCorArr[0] - (DOTS_SIZE * 3);
        dotsYCorArr[1] = dotsYCorArr[1] - sin45Radius;
        dotsYCorArr[3] = dotsYCorArr[3] + sin45Radius;

        dotsYCorArr[4] = dotsYCorArr[4] + (DOTS_SIZE * 3);
        dotsYCorArr[5] = dotsYCorArr[5] + sin45Radius;
        dotsYCorArr[7] = dotsYCorArr[7] - sin45Radius;
    }

}

