package com.heyi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by HeYi on 2017/3/20 0020.
 * 自定义FrameLayout
 */
public class MyRelativeLayout extends RelativeLayout {
    private static final String TAG = "TaoBActivity";

    //按下时的坐标
    private float mStartX, mStartY;
    //抬起时的坐标
    private float mEndX, mEndY;

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "MyRelative+dispatchTouchEvent: ");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MyRelative+dispatchTouchEvent: Down");
                mStartX = ev.getX();
                mStartY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mEndX = ev.getX();
                mEndY = ev.getY();
                Log.d(TAG, "MyRelative+dispatchTouchEvent: Up");
                break;
        }

        if (Math.abs(mEndY - mStartY) > 20) {
            Log.d(TAG, "MyRelative+dispatchTouchEvent: " + mStartY + "---" + mEndY);
            return super.dispatchTouchEvent(ev);
        } else {
            return false;
        }
//        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "MyRelative+onInterceptTouchEvent: ");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d(TAG, "MyRelative+onTouchEvent: ");
        return super.onTouchEvent(event);
    }
}
