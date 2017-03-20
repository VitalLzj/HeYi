package com.heyi.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.heyi.R;
import com.heyi.base.BaseActivity;
import com.heyi.widget.MySView;
import com.heyi.widget.MyTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HeYi on 2017/3/18 0018.
 * 淘宝详情页
 */
public class TaoBActivity extends BaseActivity {

    @BindView(R.id.tb_scroll)
    MySView mScroll;

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

    @BindView(R.id.tb_back_img)
    ImageView mBackImg;
    @BindView(R.id.tb_more_img)
    ImageView mMoreImg;
    @BindView(R.id.tb_gwc_img)
    ImageView mGwcImg;

    //商品详情图
    @BindView(R.id.tb_img)
    ImageView mBigImg;

    private static final String TAG = "TaoBActivity";
    @BindView(R.id.tb_title)
    RelativeLayout mTitleLayout;

    private int mImgHeight;
    private int mHalfHeight;

    //文字
    @BindView(R.id.tb_bb_text)
    MyTextView mBbText;
    @BindView(R.id.tb_pj_text)
    MyTextView mPjText;
    @BindView(R.id.tb_xq_text)
    MyTextView mXqText;
    @BindView(R.id.tb_tj_text)
    MyTextView mTjText;
    //缩略图
    @BindView(R.id.tb_img_thumb)
    ImageView mThumbImg;

    //评价距离顶部距离 详情距离顶部距离 推荐距离顶部距离 标题栏高度
    private int mPjHeight, mXqHeight, mTjHeight, mTitleHeight;

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

        mPjHeight = mPjLayout.getTop();
        mXqHeight = mXqLayout.getTop();
        mTjHeight = mTjLayout.getTop();
        mTitleHeight = mTitleLayout.getHeight();

        super.onWindowFocusChanged(hasFocus);
    }


    private void initEvent() {

        mScroll.setOnScrollChanged(new MySView.OnScrollChanged() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
//                Log.d(TAG, "onScroll: " + l + "---" + t + "---" + oldl + "----" + oldt);

                //①大图滚动一半的时候，将三个灰色背景的LinearLayout背景色逐渐透明
                //随着屏幕的滚动，获取宝贝布局距离屏幕顶部的距离
                int top = mBbLayout.getTop() - t;
                //当top距离屏幕顶端的距离为一半时，三个灰色完全透明
                if (top >= 0 && top < mImgHeight) {
                    int progress = (int) ((float) (mImgHeight - top) / (float) mImgHeight * 255);
                    mTitleLayout.setBackgroundColor(Color.argb((int) ((float) progress), 0xff, 0xff, 0xff));
                    mBbText.setTextColor(Color.argb((int) ((float) progress), 0xff, 0x7f, 0x00));   //文字透明度
                    mPjText.setTextColor(Color.argb((int) ((float) progress), 0x22, 0x22, 0x22));   //文字透明度
                    mXqText.setTextColor(Color.argb((int) ((float) progress), 0x22, 0x22, 0x22));   //文字透明度
                    mTjText.setTextColor(Color.argb((int) ((float) progress), 0x22, 0x22, 0x22));   //文字透明度
                    //缩略图
                    mThumbImg.setImageResource(R.mipmap.bg_thumb);
                    mThumbImg.getDrawable().mutate().setAlpha(progress);
                    if (top <= mHalfHeight) {
                        mBackLayout.getBackground().mutate().setAlpha(0);
                        mGwcLayout.getBackground().mutate().setAlpha(0);
                        mMoreLayout.getBackground().mutate().setAlpha(0);

                        mBackImg.setImageResource(R.mipmap.back_hover);
                        mGwcImg.setImageResource(R.mipmap.gwc_hover);
                        mMoreImg.setImageResource(R.mipmap.more_hover);

                        int progress3 = (int) ((float) (mHalfHeight - top) / (float) mImgHeight * 255);
                        mBackImg.getDrawable().mutate().setAlpha(progress3);
                        mGwcImg.getDrawable().mutate().setAlpha(progress3);
                        mMoreImg.getDrawable().mutate().setAlpha(progress3);
                    } else if (top > mHalfHeight && top < mHalfHeight * 2) {
                        //当宝贝布局还没有滚动出一半的时候

                        //更换图片
                        mBackImg.setImageResource(R.mipmap.back);
                        mGwcImg.setImageResource(R.mipmap.gwc);
                        mMoreImg.setImageResource(R.mipmap.more);

                        int progress2 = (int) ((float) (top - mHalfHeight) / (float) mHalfHeight * 255);
                        mBackLayout.getBackground().mutate().setAlpha(progress2);
                        mGwcLayout.getBackground().mutate().setAlpha(progress2);
                        mMoreLayout.getBackground().mutate().setAlpha(progress2);

                        mBackImg.getDrawable().mutate().setAlpha(progress2);
                        mGwcImg.getDrawable().mutate().setAlpha(progress2);
                        mMoreImg.getDrawable().mutate().setAlpha(progress2);
                    }
                } else if (top >= mImgHeight) {
                    //如果滑动过快，距离会出现错误，需要加一层判断
                    mBackLayout.getBackground().mutate().setAlpha(255);
                    mGwcLayout.getBackground().mutate().setAlpha(255);
                    mMoreLayout.getBackground().mutate().setAlpha(255);

                    mBackImg.getDrawable().mutate().setAlpha(255);
                    mGwcImg.getDrawable().mutate().setAlpha(255);
                    mMoreImg.getDrawable().mutate().setAlpha(255);
                    mThumbImg.getDrawable().mutate().setAlpha(0);

                    mTitleLayout.setBackgroundColor(Color.argb(0, 0xff, 0xff, 0xff));
                    mBbText.setTextColor(Color.argb(0, 0x22, 0x22, 0x22));   //文字透明度
                    mPjText.setTextColor(Color.argb(0, 0x22, 0x22, 0x22));   //文字透明度
                    mXqText.setTextColor(Color.argb(0, 0x22, 0x22, 0x22));   //文字透明度
                    mTjText.setTextColor(Color.argb(0, 0x22, 0x22, 0x22));   //文字透明度
                } else {
                    mBackLayout.getBackground().mutate().setAlpha(0);
                    mGwcLayout.getBackground().mutate().setAlpha(0);
                    mMoreLayout.getBackground().mutate().setAlpha(0);

                    mBackImg.getDrawable().mutate().setAlpha(255);
                    mGwcImg.getDrawable().mutate().setAlpha(255);
                    mMoreImg.getDrawable().mutate().setAlpha(255);
                    mThumbImg.getDrawable().mutate().setAlpha(255);

                    mTitleLayout.setBackgroundColor(Color.argb(255, 0xff, 0xff, 0xff));
                    mBbText.setTextColor(Color.argb(255, 0xff, 0x7f, 0x00));   //文字透明度
                    mPjText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                    mXqText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                    mTjText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                }

                //修改文字颜色
                if (mTjLayout.getTop() - t - mTitleLayout.getHeight() <= 0) {
                    resetTextColor();
                    //说明滑到了推荐布局，更改文字颜色
                    mBbText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                    mPjText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                    mXqText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                    mTjText.setTextColor(Color.argb(255, 0xff, 0x7f, 0x00));   //文字透明度
                } else if (mXqLayout.getTop() - t - mTitleLayout.getHeight() <= 0) {
                    resetTextColor();
                    //说明滑到了详情布局，更改文字颜色
                    mBbText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                    mPjText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                    mXqText.setTextColor(Color.argb(255, 0xff, 0x7f, 0x00));   //文字透明度
                    mTjText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                } else if (mPjLayout.getTop() - t - mTitleLayout.getHeight() <= 0) {
                    resetTextColor();
                    //说明滑到了评价布局，更改文字颜色
                    mBbText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                    mPjText.setTextColor(Color.argb(255, 0xff, 0x7f, 0x00));   //文字透明度
                    mXqText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                    mTjText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
                }
            }
        });

        mBbText.setOnIndexClickListener(new MyTextView.onIndexClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tb+onIndex: ");
                mScroll.scrollTo(0, 0);
                mScroll.smoothScrollTo(0, 0);
            }
        });
        mPjText.setOnIndexClickListener(new MyTextView.onIndexClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tb+onIndex: ");
                mScroll.scrollTo(0, mPjHeight - mTitleHeight);
                mScroll.smoothScrollTo(0, mPjHeight - mTitleHeight);
            }
        });
        mXqText.setOnIndexClickListener(new MyTextView.onIndexClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tb+onIndex: ");
                mScroll.scrollTo(0, mXqHeight - mTitleHeight);
                mScroll.smoothScrollTo(0, mXqHeight - mTitleHeight);
            }
        });
        mTjText.setOnIndexClickListener(new MyTextView.onIndexClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tb+onIndex: ");
                mScroll.scrollTo(0, mTjHeight - mTitleHeight);
                mScroll.smoothScrollTo(0, mTjHeight - mTitleHeight);
            }
        });


    }

    /**
     * 重置文字颜色
     */
    public void resetTextColor() {
        mBbText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
        mPjText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
        mXqText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
        mTjText.setTextColor(Color.argb(255, 0x22, 0x22, 0x22));   //文字透明度
    }

//    //点击事件
//    @OnClick({R.id.tb_bb_text, R.id.tb_pj_text, R.id.tb_xq_text, R.id.tb_tj_text})
//    public void onClick(View v) {
//        Log.d(TAG, "onClick: ");
//        switch (v.getId()) {
//            case R.id.tb_bb_text:
//                mScroll.scrollTo(0, 0);
//                break;
//            //由于标题栏显示着 需要减去标题栏的高度
//            case R.id.tb_pj_text:
//                mScroll.scrollTo(0, mPjHeight - mTitleHeight);
//                break;
//            case R.id.tb_xq_text:
//                mScroll.scrollTo(0, mXqHeight - mTitleHeight);
//                break;
//            case R.id.tb_tj_text:
//                mScroll.scrollTo(0, mTjHeight - mTitleHeight);
//                break;
//        }
//    }
}
