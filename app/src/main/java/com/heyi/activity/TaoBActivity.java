package com.heyi.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.heyi.R;
import com.heyi.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HeYi on 2017/3/18 0018.
 * 淘宝详情页
 */
public class TaoBActivity extends BaseActivity {

    @BindView(R.id.tb_scroll)
    ScrollView mScroll;

    @BindView(R.id.tb_bb)
    LinearLayout mBbLayout;
    @BindView(R.id.tb_pj)
    LinearLayout mPjLayout;
    @BindView(R.id.tb_xq)
    LinearLayout mXqLayout;
    @BindView(R.id.tb_tj)
    LinearLayout mTjLayout;

    @BindView(R.id.tb_back)
    LinearLayout mBackLayout;
    @BindView(R.id.tb_more)
    LinearLayout mMoreLayout;
    @BindView(R.id.tb_gwc)
    LinearLayout mGwcLayout;

    //商品详情图
    @BindView(R.id.tb_img)
    ImageView mBigImg;

    private static final String TAG = "TaoBActivity";
    @BindView(R.id.tb_title)
    RelativeLayout mTitleLayout;

    private int mImgHeight;
    private int mHalfHeight;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao);
        ButterKnife.bind(this);
        initEvent();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        //界面完成绘制时获取高度
        //获取大图的高度
        mImgHeight = mBigImg.getHeight();
        //获取大图高度的一半
        mHalfHeight = (int) ((float) mImgHeight / 2.0f);
        super.onWindowFocusChanged(hasFocus);
    }

    private void initEvent() {
        mScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                //①大图滚动一半的时候，将三个灰色背景的LinearLayout背景色逐渐透明
                //随着屏幕的滚动，获取宝贝布局距离屏幕顶部的距离
                int top = mBbLayout.getTop() - i1;
                //当top距离屏幕顶端的距离为一半时，三个灰色完全透明

                if (top >= 0 && top <= mImgHeight) {
                    int progress = (int) ((float) (top - mImgHeight) / (float) mImgHeight * 255);
                    mTitleLayout.setBackgroundColor(Color.argb(255 - (int) ((float) progress), 0xff, 0xff, 0xff));
                    if (top <= mHalfHeight) {
                        mBackLayout.getBackground().mutate().setAlpha(0);
                        mGwcLayout.getBackground().mutate().setAlpha(0);
                        mMoreLayout.getBackground().mutate().setAlpha(0);
                    } else if (top > mHalfHeight && top <= mHalfHeight * 2) {
                        //如果滑动过快，距离会出现错误，需要加一层判断
                        if (top == mHalfHeight * 2) {
                            mBackLayout.getBackground().mutate().setAlpha(255);
                            mGwcLayout.getBackground().mutate().setAlpha(255);
                            mMoreLayout.getBackground().mutate().setAlpha(255);
                            mTitleLayout.setBackgroundColor(Color.argb(0, 0xff, 0xff, 0xff));
                        } else {
                            //当宝贝布局还没有滚动出一半的时候
                            int progress2 = (int) ((float) (top - mHalfHeight) / (float) mHalfHeight * 255);
                            Log.d(TAG, "onScrollChange: " + progress);
                            mBackLayout.getBackground().mutate().setAlpha(progress2);
                            mGwcLayout.getBackground().mutate().setAlpha(progress2);
                            mMoreLayout.getBackground().mutate().setAlpha(progress2);
                        }
                    }
                }


            }
        });
    }
}
