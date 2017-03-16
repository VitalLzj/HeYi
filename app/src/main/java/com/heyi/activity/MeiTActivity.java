package com.heyi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.heyi.R;
import com.heyi.base.BaseActivity;
import com.heyi.bean.Mei_T;
import com.heyi.fragment.MeiTuanFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by  on 2017/3/15 0015.
 * 公司：HeYi
 * 美团首页
 */
public class MeiTActivity extends BaseActivity {

    @BindView(R.id.mei_t_viewPager)
    ViewPager mViewPager;
    //存放圆点
    @BindView(R.id.mei_t_point_layout)
    LinearLayout mPointLayout;
    //初始化数据
    List<Mei_T> mLists;
    //存放图标
    int[] mImgIds = {R.mipmap.my_index_1, R.mipmap.my_index_2, R.mipmap.my_index_3, R.mipmap.my_index_4,
            R.mipmap.my_index_5, R.mipmap.my_index_6, R.mipmap.my_index_7, R.mipmap.my_index_8,
            R.mipmap.my_index_9, R.mipmap.my_index_10};
    //共几页数据,用来分页
    private int page_num = 0;
    ArrayList<MeiTuanFragment> fragments = new ArrayList<>();
    //上次页码的索引
    private int mSelectPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meit);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mLists = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            int j = i % 10;
            Mei_T mei = new Mei_T();
            mei.setId(i + "");
            mei.setTitle("item" + i);
            mei.setImgId(mImgIds[j]);
            mLists.add(mei);
        }

        //根据添加的数据量多少，进行分页，每页10条
        page_num = mLists.size() / 10;
        //list的size是否有余数，如果有余数，需要再加一页
        if (mLists.size() % 10 != 0) {
            page_num++;
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        initPoint();
        initFragment();
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("tag", "nowPosition=" + position);
                //position从0开始
                Log.d("tag", "size=" + mPointLayout.getChildCount());
                //上一个恢复未选中状态
                ((ImageView) mPointLayout.getChildAt(mSelectPosition)).setImageResource(android.R.drawable.presence_invisible);
                //当前页选中状态
                ImageView currentImg = (ImageView) mPointLayout.getChildAt(position);
                currentImg.setImageResource(android.R.drawable.presence_online);
                mSelectPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 构造fragment
     */
    private void initFragment() {
        //由于每页只显示10个，需要将mList拆开传递
        for (int i = 0; i < page_num; i++) {
            int j = i * 10;
            int m = 10;
            if (i == page_num - 1) {
                m = mLists.size() % 10;
            }
            ArrayList<Mei_T> list = new ArrayList<>();
            for (int k = j; k < j + m; k++) {
                list.add(mLists.get(k));
            }
            MeiTuanFragment fragment = MeiTuanFragment.newInstance(list);
            fragments.add(fragment);
        }

    }

    /**
     * 构造圆点
     */
    private void initPoint() {
        //构造圆点
        for (int i = 0; i < page_num; i++) {
            ImageView mPointImg = new ImageView(this);
            if (i == 0) {
                mPointImg.setImageResource(android.R.drawable.presence_online);
            } else {
                mPointImg.setImageResource(android.R.drawable.presence_invisible);
            }
            LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(30, 30);
            mParams.setMargins(5, 0, 5, 0);
            mPointImg.setLayoutParams(mParams);
            mPointLayout.addView(mPointImg);
        }
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<MeiTuanFragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<MeiTuanFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
