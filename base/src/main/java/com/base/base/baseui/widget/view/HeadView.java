package com.base.base.baseui.widget.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.base.base.R;

public class HeadView extends LinearLayout {
    private Drawable leftIcon, rightIcon;
    private String leftString, middleString, rightString;
    private boolean lineShow;

    private ImageView mLeftIv, mRightIv;
    private TextView mLeftTv, mRightTv, mMiddleTv;

    public HeadView(Context context) {
        this(context, null);
    }

    public HeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadView);
        leftIcon = typedArray.getDrawable(R.styleable.HeadView_left_icon);
        rightIcon = typedArray.getDrawable(R.styleable.HeadView_right_icon);
        leftString = typedArray.getString(R.styleable.HeadView_left_text);
        rightString = typedArray.getString(R.styleable.HeadView_right_text);
        middleString = typedArray.getString(R.styleable.HeadView_middle_text);
        lineShow = typedArray.getBoolean(R.styleable.HeadView_bottom_line, false);
        typedArray.recycle();
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_header, this);
        if (lineShow) {
            view.findViewById(R.id.view_line).setVisibility(VISIBLE);
        }
        mLeftIv = view.findViewById(R.id.left_image);
        if (null != leftIcon) {
            mLeftIv.setImageDrawable(leftIcon);
            mLeftIv.setVisibility(View.VISIBLE);
        }
        mLeftIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        mRightIv = view.findViewById(R.id.right_image);
        if (null != rightIcon) {
            mRightIv.setImageDrawable(rightIcon);
            mRightIv.setVisibility(View.VISIBLE);
        }
        mLeftTv = view.findViewById(R.id.left_text);
        if (!TextUtils.isEmpty(leftString)) {
            mLeftTv.setText(leftString);
            mLeftTv.setVisibility(VISIBLE);
        }
        mRightTv = view.findViewById(R.id.right_text);
        if (!TextUtils.isEmpty(rightString)) {
            mRightTv.setText(rightString);
            mRightTv.setVisibility(VISIBLE);
        }
        mMiddleTv = view.findViewById(R.id.middle_title);
        if (!TextUtils.isEmpty(middleString)) {
            mMiddleTv.setText(middleString);
            mMiddleTv.setVisibility(VISIBLE);
        }
    }

    public void setLeftIconClickListerner(OnClickListener onClickListener) {
        if (null != mLeftIv) {
            mLeftIv.setOnClickListener(onClickListener);
        }
    }

    public void setLeftTextClickListerner(OnClickListener onClickListener) {
        if (null != mLeftTv) {
            mLeftTv.setOnClickListener(onClickListener);
        }
    }

    public void setRightIconClickListerner(OnClickListener onClickListener) {
        if (null != mRightIv) {
            mRightIv.setOnClickListener(onClickListener);
        }
    }

    public void setRightTextClickListerner(OnClickListener onClickListener) {
        if (null != mRightTv) {
            mRightTv.setOnClickListener(onClickListener);
        }
    }

    public void setMiddleString(String string) {
        if (null != mMiddleTv) {
            mMiddleTv.setText(string);
        }
    }

}


