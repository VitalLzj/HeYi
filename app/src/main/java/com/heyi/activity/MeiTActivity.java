package com.heyi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.heyi.R;
import com.heyi.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lzj on 2017/3/15 0015.
 * 公司：HeYi
 * 美团首页
 */
public class MeiTActivity extends BaseActivity {

    @BindView(R.id.mei_t_viewPager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meit);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }
}
