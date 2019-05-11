package com.dong.nfcsecurity.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dong.nfcsecurity.R;

public class PairContentView extends LinearLayout {
    public PairContentView(Context context) {
        this(context, null);
    }

    public PairContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    TextView tvKey;
    TextView tvValue;

    public PairContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);

    }

    public PairContentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflate(context, R.layout.layout_pair, this);
        tvKey = $(R.id.key);
        tvValue = $(R.id.value);
    }

    public void setContent(String key, String value) {
        tvKey.setText(key);
        tvValue.setText(value);
    }

    public <T> T $(int resID) {
        return (T) findViewById(resID);
    }

}
