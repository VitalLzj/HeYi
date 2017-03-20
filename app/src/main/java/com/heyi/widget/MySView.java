package com.heyi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by HeYi on 2017/3/20 0020.
 * 自定义ScrollView，由于addScrollView 是在API23以后出现的，没有向下兼容，需要自定义
 */
public class MySView extends ScrollView {
    private OnScrollChanged mOnScrollChanged;
    private static final String TAG = "TaoBActivity";
    //按下时的坐标
    private float mStartX, mStartY;
    //抬起时的坐标
    private float mEndX, mEndY;

    public MySView(Context context) {
        this(context, null);
    }

    public MySView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChanged != null)
            mOnScrollChanged.onScroll(l, t, oldl, oldt);
    }

    public void setOnScrollChanged(OnScrollChanged onScrollChanged) {
        this.mOnScrollChanged = onScrollChanged;
    }

    public interface OnScrollChanged {
        void onScroll(int l, int t, int oldl, int oldt);
    }

}
