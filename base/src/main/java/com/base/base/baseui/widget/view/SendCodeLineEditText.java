package com.base.base.baseui.widget.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.base.base.R;


/**
 * 发送验证码的输入控件
 */
public class SendCodeLineEditText extends LinearLayout implements View.OnClickListener {

    private LineEditText lineEditText;
    private CountDownTextView countDownTextView;

    public SendCodeLineEditText(Context context) {
        this(context, null);
    }

    public SendCodeLineEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SendCodeLineEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_send_code_line_edit, this);
        lineEditText = view.findViewById(R.id.line_et);
        countDownTextView = view.findViewById(R.id.count_text);
        countDownTextView.setOnClickListener(this);
    }

    public String getString() {
        if (null != lineEditText) {
            String s = lineEditText.getText().toString().trim();
            return s;
        }
        return "";
    }

    @Override
    public void onClick(View view) {

    }

}
