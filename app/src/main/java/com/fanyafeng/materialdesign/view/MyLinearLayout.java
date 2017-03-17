package com.fanyafeng.materialdesign.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by fanyafeng on 2015/12/18,0018.
 */
public class MyLinearLayout extends LinearLayout {
    private OnScrollListener onScrollListener;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) {
            onScrollListener.OnScroll(l, t, oldl, oldt);
        }
    }

    public interface OnScrollListener {
        public void OnScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }
}
