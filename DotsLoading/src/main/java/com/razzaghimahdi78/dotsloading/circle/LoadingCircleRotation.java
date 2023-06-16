package com.razzaghimahdi78.dotsloading.circle;

import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_CIRCLE_DOTS_COUNT;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_COLOR;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DOTS_COUNT;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DOTS_SIZE;
import static com.razzaghimahdi78.dotsloading.core.Constant.DEFAULT_DURATION;
import static com.razzaghimahdi78.dotsloading.core.Constant.SIN_45;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.Nullable;

import com.razzaghimahdi78.dotsloading.R;
import com.razzaghimahdi78.dotsloading.core.BaseCircleLoading;
import com.razzaghimahdi78.dotsloading.core.CheckValidation;
import com.razzaghimahdi78.dotsloading.core.CheckValidationImpl;
import com.razzaghimahdi78.dotsloading.core.CircleView;
import com.razzaghimahdi78.dotsloading.core.Convertor;
import com.razzaghimahdi78.dotsloading.core.ConvertorImpl;
import com.razzaghimahdi78.dotsloading.core.DotSize;


public class LoadingCircleRotation extends BaseCircleLoading {

    private Convertor convertor;
    private CheckValidation checkValidation;


    private int DOTS_SIZE = DEFAULT_DOTS_SIZE;
    private int DURATION = DEFAULT_DURATION;
    private int COLOR = DEFAULT_COLOR;

    private ObjectAnimator rotateAnimator;
    private ObjectAnimator scaleAnimator;
    private ObjectAnimator alphaAnimator;
    boolean onLayoutReach = false;

    public LoadingCircleRotation(Context context) {
        super(context);
        initView(context, null, DEFAULT_DOTS_SIZE, DEFAULT_COLOR);
    }

    public LoadingCircleRotation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, DEFAULT_DOTS_SIZE, DEFAULT_COLOR);
    }

    public LoadingCircleRotation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, DEFAULT_DOTS_SIZE, DEFAULT_COLOR);
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
        if (rotateAnimator.isRunning()) {
            rotateAnimator.removeAllListeners();
            rotateAnimator.end();
            rotateAnimator.cancel();
        }
        if (scaleAnimator.isRunning()) {
            scaleAnimator.removeAllListeners();
            scaleAnimator.end();
            scaleAnimator.cancel();
        }
        if (alphaAnimator.isRunning()) {
            alphaAnimator.removeAllListeners();
            alphaAnimator.end();
            alphaAnimator.cancel();
        }
    }


    @Override
    protected void initView(Context context, @Nullable AttributeSet attrs, int dotsSize, int color) {
        convertor = new ConvertorImpl();
        checkValidation = new CheckValidationImpl();

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingCircleRotation);
            setColor(typedArray.getColor(R.styleable.LoadingCircleRotation_dots_color, DEFAULT_COLOR));
            setDuration(typedArray.getInt(R.styleable.LoadingCircleRotation_dots_duration, DEFAULT_DURATION));
            // setSize(typedArray.getInt(R.styleable.LoadingCircleRotation_dots_size, 2));
            setSize(typedArray.getDimensionPixelSize(R.styleable.LoadingCircleRotation_dots_size, DEFAULT_DOTS_SIZE));
        }

        super.initView(context, attrs, DOTS_SIZE, COLOR);
    }

    private void animateView(final boolean show) {
        rotateAnimator = new ObjectAnimator();
        rotateAnimator = ObjectAnimator.ofFloat(this,
                "rotation", 0f, 360f);
        rotateAnimator.setRepeatCount(-1);
        rotateAnimator.setRepeatMode(ValueAnimator.REVERSE);
        rotateAnimator.setDuration(DURATION);
        rotateAnimator.setStartDelay(0);
        rotateAnimator.start();


        scaleAnimator = new ObjectAnimator();
        PropertyValuesHolder SCALE_X = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.5f);
        PropertyValuesHolder SCALE_Y = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.5f);
        scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(this, SCALE_X, SCALE_Y);
        scaleAnimator.setRepeatCount(-1);
        scaleAnimator.setRepeatMode(ValueAnimator.REVERSE);
        scaleAnimator.setDuration(DURATION);
        scaleAnimator.setStartDelay(0);
        scaleAnimator.start();


        alphaAnimator = new ObjectAnimator();
        PropertyValuesHolder A = PropertyValuesHolder.ofFloat("alpha", 0.0f);
        PropertyValuesHolder B = PropertyValuesHolder.ofFloat("alpha", 1.0f);
        PropertyValuesHolder alpha = show ? A : B;
        alphaAnimator = ObjectAnimator.ofPropertyValuesHolder(this, alpha);
        alphaAnimator.setRepeatCount(-1);
        alphaAnimator.setRepeatMode(ValueAnimator.REVERSE);
        alphaAnimator.setDuration(DURATION);
        alphaAnimator.setStartDelay(0);
        alphaAnimator.start();

        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animateView(!show);
            }
        });

    }

    public void setDuration(int value) {
        if (checkValidation.isDurationValid(value)) {
            this.DURATION = value;
            initView(getContext(), null, DEFAULT_DOTS_SIZE, DEFAULT_COLOR);
        }
    }

    public void setColor(int value) {
        this.COLOR = value;
        initView(getContext(), null, DEFAULT_DOTS_SIZE, DEFAULT_COLOR);
    }

    public void setSize(int value) {
        this.DOTS_SIZE = value;
        initView(getContext(), null, DEFAULT_DOTS_SIZE, DEFAULT_COLOR);
    }

    private void setSize(DotSize value) {
        this.DOTS_SIZE = convertor.convertDotSize(value);
        initView(getContext(), null, DEFAULT_DOTS_SIZE, DEFAULT_COLOR);
    }

}
