package com.razzaghimahdi78.dotsloading.linear;

import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_COLOR;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DOTS_COUNT;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DOTS_SIZE;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DURATION;
import static com.razzaghimahdi78.dotsloading.core.Constant.MAX_JUMP;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.razzaghimahdi78.dotsloading.R;
import com.razzaghimahdi78.dotsloading.core.BaseLinearLoading;
import com.razzaghimahdi78.dotsloading.core.CheckValidation;
import com.razzaghimahdi78.dotsloading.core.CheckValidationImpl;
import com.razzaghimahdi78.dotsloading.core.Convertor;
import com.razzaghimahdi78.dotsloading.core.ConvertorImpl;
import com.razzaghimahdi78.dotsloading.core.DotSize;


public class LoadingWavy extends BaseLinearLoading {

    private Convertor convertor;
    private CheckValidation checkValidation;


    private int DOTS_SIZE = DEFAULT_DOTS_SIZE;
    private int DOTS_COUNT = DEFAULT_DOTS_COUNT;
    private int COLOR = DEFAULT_COLOR;
    private int DURATION = DEFAULT_DURATION;

    private ObjectAnimator animator[];
    boolean onLayoutReach = false;

    public LoadingWavy(Context context) {
        super(context);
        initView(context, null, DEFAULT_DOTS_SIZE,DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
    }

    public LoadingWavy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        initView(context, attrs, DEFAULT_DOTS_SIZE,DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
    }

    public LoadingWavy(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, DEFAULT_DOTS_SIZE,DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!onLayoutReach) {
            onLayoutReach = true;

            animateView();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        for (int i = 0; i < DOTS_COUNT; i++) {
            if (animator[i].isRunning()) {
                animator[i].removeAllListeners();
                animator[i].end();
                animator[i].cancel();
            }
        }
    }

    @Override
    protected void initView(Context context, @Nullable AttributeSet attrs, int dotsSize, int dotsCount, int color) {
        convertor = new ConvertorImpl();
        checkValidation = new CheckValidationImpl();

        if (attrs!=null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingWavy);
            setColor(typedArray.getColor(R.styleable.LoadingWavy_dots_color, DEFAULT_COLOR));
            setDuration(typedArray.getInt(R.styleable.LoadingWavy_dots_duration, DEFAULT_DURATION));
            setDotsCount(typedArray.getInt(R.styleable.LoadingWavy_dots_count, DEFAULT_DOTS_COUNT));
            setSize(typedArray.getInt(R.styleable.LoadingWavy_dots_size, 2));
        }



        super.initView(context, attrs, DOTS_SIZE,DOTS_COUNT, COLOR);
    }

    private void animateView() {
        animator = new ObjectAnimator[DOTS_COUNT];
        for (int i = 0; i < DOTS_COUNT; i++) {
            getChildAt(i).setTranslationY(getHeight() / MAX_JUMP);
            PropertyValuesHolder Y = PropertyValuesHolder.ofFloat(getChildAt(i).TRANSLATION_Y, -getHeight() / MAX_JUMP);
            PropertyValuesHolder X = PropertyValuesHolder.ofFloat(getChildAt(i).TRANSLATION_X, 0);
            animator[i] = ObjectAnimator.ofPropertyValuesHolder(getChildAt(i), X, Y);
            animator[i].setRepeatCount(-1);
            animator[i].setRepeatMode(ValueAnimator.REVERSE);
            animator[i].setDuration(DURATION);
            animator[i].setStartDelay((DURATION / DOTS_COUNT) * i);
            animator[i].start();
        }

    }


    public void setDotsCount(int value) {
        if (checkValidation.isCountValid(value)) {
            this.DOTS_COUNT = value;
            initView(getContext(), null, DEFAULT_DOTS_SIZE,DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
        }
    }

    public void setDuration(int value) {
        if (checkValidation.isDurationValid(value)) {
            this.DURATION = value;
            initView(getContext(), null, DEFAULT_DOTS_SIZE,DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
         }
    }


    public void setColor(int value) {
        this.COLOR = value;
        initView(getContext(), null, DEFAULT_DOTS_SIZE,DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
     }

    private void setSize(int value) {
        this.DOTS_SIZE = convertor.convertDotSize(value);
        initView(getContext(), null, DEFAULT_DOTS_SIZE, DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
    }

    public void setSize(DotSize value) {
        this.DOTS_SIZE = convertor.convertDotSize(value);
        initView(getContext(), null, DEFAULT_DOTS_SIZE, DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
    }


}
