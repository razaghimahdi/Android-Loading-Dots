package com.razzaghimahdi78.dotsloading.linear;

import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_COLOR;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DOTS_COUNT;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DOTS_SIZE;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DURATION;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.razzaghimahdi78.dotsloading.R;
import com.razzaghimahdi78.dotsloading.core.BaseLinearLoading;
import com.razzaghimahdi78.dotsloading.core.CheckValidation;
import com.razzaghimahdi78.dotsloading.core.CheckValidationImpl;
import com.razzaghimahdi78.dotsloading.core.CircleView;
import com.razzaghimahdi78.dotsloading.core.Constant;
import com.razzaghimahdi78.dotsloading.core.Convertor;
import com.razzaghimahdi78.dotsloading.core.ConvertorImpl;
import com.razzaghimahdi78.dotsloading.core.DotSize;


public class LoadingFady extends BaseLinearLoading {


    private Convertor convertor;
    private CheckValidation checkValidation;

    private int DOTS_SIZE = DEFAULT_DOTS_SIZE;
    private int DOTS_COUNT = DEFAULT_DOTS_COUNT;
    private int DURATION = DEFAULT_DURATION;
    private int COLOR = DEFAULT_COLOR;

    private ObjectAnimator animator[];
    boolean onLayoutReach = false;

    public LoadingFady(Context context) {
        super(context);
        initView(context, null, DEFAULT_DOTS_SIZE, DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
    }

    public LoadingFady(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        initView(context, attrs, DEFAULT_DOTS_SIZE, DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
    }

    public LoadingFady(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, DEFAULT_DOTS_SIZE, DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!onLayoutReach) {
            onLayoutReach = true;

            animateView(true);
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

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingFady);
            setColor(typedArray.getColor(R.styleable.LoadingFady_dots_color, DEFAULT_COLOR));
            setDuration(typedArray.getInt(R.styleable.LoadingFady_dots_duration, DEFAULT_DURATION));
            setDotsCount(typedArray.getInt(R.styleable.LoadingFady_dots_count, DEFAULT_DOTS_COUNT));
            setSize(typedArray.getInt(R.styleable.LoadingFady_dots_size, 2));
        }
        super.initView(context, attrs, DOTS_SIZE, DOTS_COUNT, COLOR);
    }

    private void animateView(final boolean show) {
        animator = new ObjectAnimator[DOTS_COUNT];
        for (int i = 0; i < DOTS_COUNT; i++) {
            PropertyValuesHolder A = PropertyValuesHolder.ofFloat("alpha", 0.2f);
            PropertyValuesHolder B = PropertyValuesHolder.ofFloat("alpha", 1.0f);
            PropertyValuesHolder alpha = show ? A : B;
            animator[i] = ObjectAnimator.ofPropertyValuesHolder(getChildAt(i), alpha);
            animator[i].setRepeatCount(0);
            animator[i].setRepeatMode(ValueAnimator.REVERSE);
            animator[i].setDuration(DURATION);
            animator[i].setStartDelay(DURATION * i);
            animator[i].start();
        }
        animator[DOTS_COUNT - 1].addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animateView(!show);
            }
        });

    }

    public void setDotsCount(int value) {
        if (checkValidation.isCountValid(value)) {
            this.DOTS_COUNT = value;
            initView(getContext(), null, DEFAULT_DOTS_SIZE, DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
        }
    }

    public void setDuration(int value) {
        if (checkValidation.isDurationValid(value)) {
            this.DURATION = value;
            initView(getContext(), null, DEFAULT_DOTS_SIZE, DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
        }
    }

    public void setColor(int value) {
        this.COLOR = value;
        initView(getContext(), null, DEFAULT_DOTS_SIZE, DEFAULT_DOTS_COUNT, DEFAULT_COLOR);
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
