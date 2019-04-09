package com.base.base.baseui.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.base.base.R;
import com.base.base.utilcode.util.ConvertUtils;

/**
 * Created by zhangyinlei on 2018/6/20
 */
public class CustomCirclePercentView extends View {
    private int radius;
    private float stripeWidth;
    private int smallCircleColor;
    private int bigCircleColor;
    private int mPercent;
    private int middleTextSize;
    private int middleTextColor;

    private int x, y;//圆心
    private int mEndAngle;

    public CustomCirclePercentView(Context context) {
        this(context, null);
    }

    public CustomCirclePercentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCirclePercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        <attr name="radius" format="dimension" />
//        <attr name="stripeWidth" format="dimension" />
//        <attr name="smallCircleColor" format="color" />
//        <attr name="bigCircleColor" format="color" />
//        <attr name="percent" format="integer" />
//        <attr name="middleTextSize" format="dimension" />
//        <attr name="middleTextColor" format="color" />
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCirclePercentView);
        radius = typedArray.getDimensionPixelSize(R.styleable.CustomCirclePercentView_radius, ConvertUtils.dp2px(100));
        stripeWidth = typedArray.getDimension(R.styleable.CustomCirclePercentView_stripeWidth, ConvertUtils.dp2px(30));
        smallCircleColor = typedArray.getColor(R.styleable.CustomCirclePercentView_smallCircleColor, 0xffafb4db);
        bigCircleColor = typedArray.getColor(R.styleable.CustomCirclePercentView_bigCircleColor, 0xff6950a1);
        mPercent = typedArray.getInt(R.styleable.CustomCirclePercentView_percent, 0);
        middleTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomCirclePercentView_middleTextSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        middleTextColor = typedArray.getColor(R.styleable.CustomCirclePercentView_middleTextColor, Color.BLUE);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取测量大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0, height = 0;
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            radius = widthSize / 2;
            x = widthSize / 2;
            y = heightSize / 2;
            width = widthSize;
            height = heightSize;
        }
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            width = radius * 2;
            height = radius * 2;
            x = radius;
            y = radius;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mEndAngle = (int) (mPercent * 3.6);

        Paint bigCirclePaint = new Paint();
        bigCirclePaint.setColor(bigCircleColor);
        bigCirclePaint.setAntiAlias(true);
        canvas.drawCircle(x, y, radius, bigCirclePaint);

        Paint sectorPaint = new Paint();
        sectorPaint.setColor(smallCircleColor);
        sectorPaint.setAntiAlias(true);
        RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawArc(rectF, 270, mEndAngle, true, sectorPaint);

        Paint smallPaint = new Paint();
        smallPaint.setColor(bigCircleColor);
        smallPaint.setAntiAlias(true);
        canvas.drawCircle(x, y, radius - stripeWidth, smallPaint);

        Paint textPaint = new Paint();
        Rect mBound = new Rect();
        String text = mPercent + "%";
        textPaint.setColor(middleTextColor);
        textPaint.setTextSize(middleTextSize);
        textPaint.getTextBounds(text, 0, text.length(), mBound);
        canvas.drawText(text, x - mBound.width() / 2, y + mBound.height() / 2, textPaint);
    }

    /**
     * 外部调用
     *
     * @param mPercent
     */
    public void setmPercent(int mPercent) {
        if (mPercent > 100) {
            mPercent = 100;
        }
        setCurrentPercent(mPercent);
    }

    private void setCurrentPercent(final int percent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= percent; i++) {
                    try {
                        Thread.sleep(100);
                        mPercent = i;
                        CustomCirclePercentView.this.postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
