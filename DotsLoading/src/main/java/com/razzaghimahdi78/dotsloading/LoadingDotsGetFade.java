package com.razzaghimahdi78.dotsloading;

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
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


public class LoadingDotsGetFade extends LinearLayout {

    private ImageView[] img;
    private GradientDrawable circle = new GradientDrawable();

    private int DOTS_COUNT = 3;
    private int DURATION = 300;
    private int COLOR = Color.parseColor("#FF3700B3");

    private ObjectAnimator animator[];
    boolean onLayoutReach = false;

    public LoadingDotsGetFade(Context context) {
        super(context);
        initView(context, null);
    }

    public LoadingDotsGetFade(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        initView(context, attrs);
    }

    public LoadingDotsGetFade(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!onLayoutReach) {
            onLayoutReach = true;
            LayoutParams lp = new LayoutParams(getWidth() / 5, getWidth() / 5);
            for (int i = 0; i < DOTS_COUNT; i++) {
                img[i].setLayoutParams(lp);
            }
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

    private void initView(Context context, @Nullable AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingDotsGetFade);
        setColor(typedArray.getColor(R.styleable.LoadingDotsGetFade_fade_dots_color, Color.parseColor("#FF3700B3")));
        setDuration(typedArray.getInt(R.styleable.LoadingDotsGetFade_fade_dots_duration, 300));
        setDotsCount(typedArray.getInt(R.styleable.LoadingDotsGetFade_fade_dots_count, 3));

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);


        setBackgroundColor(Color.TRANSPARENT);

        removeAllViews();
        img = new ImageView[DOTS_COUNT];
        circle.setShape(GradientDrawable.OVAL);
        circle.setColor(COLOR);
        circle.setSize(200, 200);

        LayoutParams layoutParams2 = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams2.weight = 1;

        LinearLayout[] rel = new LinearLayout[DOTS_COUNT];
        for (int i = 0; i < DOTS_COUNT; i++) {
            rel[i] = new LinearLayout(context);
            rel[i].setGravity(Gravity.CENTER);
            rel[i].setLayoutParams(layoutParams2);
            img[i] = new ImageView(context);
            img[i].setBackgroundDrawable(circle);
            rel[i].addView(img[i]);
            addView(rel[i]);
        }
    }

    private void animateView(final boolean show) {
        animator = new ObjectAnimator[DOTS_COUNT];
        for (int i = 0; i < DOTS_COUNT; i++) {
            PropertyValuesHolder A = PropertyValuesHolder.ofFloat("alpha", 0.2f);
            PropertyValuesHolder B = PropertyValuesHolder.ofFloat("alpha", 1.0f);
            PropertyValuesHolder alpha = show ? A : B;
            animator[i] = ObjectAnimator.ofPropertyValuesHolder(img[i], alpha);
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
        this.DOTS_COUNT = value;
    }

    public void setDuration(int value) {
        this.DURATION = value;
    }

    public void setColor(int value) {
        this.COLOR = value;
    }

}
