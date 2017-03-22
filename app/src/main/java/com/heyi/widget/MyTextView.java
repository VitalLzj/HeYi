package com.heyi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HeYi on 2017/3/20 0020.
 * 最底层View没有事件拦截
 * 自定义TextView
 * 好像没啥用，就这吧
 */
public class MyTextView extends TextView {
    private static final String TAG = "TaoBActivity";

    private onIndexClickListener onIndexClickListener;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyText+dispatchTouchEvent: ");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyText+onTouchEvent: ");
        if (onIndexClickListener != null) {
            onIndexClickListener.onClick(this);
        }
        return true;
    }

    public void setOnIndexClickListener(MyTextView.onIndexClickListener onIndexClickListener) {
        this.onIndexClickListener = onIndexClickListener;
    }

    public interface onIndexClickListener {
        void onClick(View v);
    }

}
