package com.razzaghimahdi78.dotsloading.core;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.razzaghimahdi78.dotsloading.R;

public class CircleView extends View {

    int circleRadius = 20;
    int strokeWidth = 0;

    int circleColor = 0;
    boolean drawOnlyStroke = false;

    boolean isAntiAlias = true;

    private float xyCordinates = 0.0f;
    private Paint paint = new Paint();

    public CircleView(Context context, int circleRadius, int circleColor, boolean isAntiAlias) {
        super(context);
        this.circleRadius = circleRadius;
        this.circleColor = circleColor;
        this.isAntiAlias = isAntiAlias;
        initValues();
    }

    public CircleView(Context context, int circleRadius, int circleColor, boolean drawOnlyStroke, int strokeWidth) {
        super(context);
        this.circleRadius = circleRadius;
        this.circleColor = circleColor;
        this.drawOnlyStroke = drawOnlyStroke;
        this.strokeWidth = strokeWidth;
        initValues();
    }

    public CircleView(Context context) {
        super(context);
        initValues();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(attrs);
        initValues();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(attrs);
        initValues();
    }


    void initAttributes(AttributeSet attrs) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0);

        this.circleRadius = typedArray.getDimensionPixelSize(R.styleable.CircleView_circleRadius, 30);
        this.circleColor = typedArray.getColor(R.styleable.CircleView_circleColor, 0);

        this.drawOnlyStroke = typedArray.getBoolean(R.styleable.CircleView_circleDrawOnlystroke, false);

        if (drawOnlyStroke) {
            this.strokeWidth = typedArray.getDimensionPixelSize(R.styleable.CircleView_circleStrokeWidth, 0);
        }

        typedArray.recycle();
    }

    private void initValues() {
        paint.setAntiAlias(isAntiAlias);

        if (drawOnlyStroke) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth((float) strokeWidth);
        } else {
            paint.setStyle(Paint.Style.FILL);
        }
        paint.setColor(circleColor);

        //adding half of strokeWidth because
        //the stroke will be half inside the drawing circle and half outside
        xyCordinates = (circleRadius + ((float) (strokeWidth / 2)));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthHeight = (2 * (circleRadius)) + strokeWidth;
        setMeasuredDimension(widthHeight, widthHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(xyCordinates, xyCordinates, (float) circleRadius, paint);
    }
}
