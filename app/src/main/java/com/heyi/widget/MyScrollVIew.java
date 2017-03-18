package com.heyi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by HeYi on 2017/3/18 0018.
 * 自定义ScrollView 处理滑动与点击事件
 */
public class MyScrollVIew extends ScrollView {

    //手指按下的坐标
    private float mStartX, mStartY;
    //手指抬起的坐标
    private float mEndX, mEndY;

    private static final String TAG = "MyScrollVIew";
    //标志位 是否滑动
    private boolean isScroll = false;

    public MyScrollVIew(Context context) {
        super(context);
    }

    public MyScrollVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: " + "点击");
                mStartX = ev.getX();
                mStartY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mEndX = ev.getX();
                mEndY = ev.getY();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
