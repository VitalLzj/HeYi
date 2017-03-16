package com.heyi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heyi.R;
import com.heyi.adapter.MeiTAdapter;
import com.heyi.bean.Mei_T;
import com.heyi.util.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by  on 2017/3/15 0015.
 * 美团首页
 * 公司：HeYi
 */
public class MeiTuanFragment extends Fragment {

    private Context mContext;

    @BindView(R.id.mei_t_recycler)
    RecyclerView mRecyclerView;

    private List<Mei_T> mLists = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        this.mContext = context;
        super.onAttach(context);
    }

    public static MeiTuanFragment newInstance(List<Mei_T> lists) {
        Bundle args = new Bundle();
        MeiTuanFragment fragment = new MeiTuanFragment();
        args.putSerializable("key", (Serializable) lists);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meit, container, false);
        ButterKnife.bind(this, view);
        //接收传来的list作为数据
        Bundle bundle = getArguments();
        if (bundle != null) {
            mLists = (List<Mei_T>) bundle.getSerializable("key");
        }
        initData();
        return view;
    }


    private void initData() {
        MeiTAdapter mAdapter = new MeiTAdapter(mLists, mContext);
        GridLayoutManager manager = new GridLayoutManager(mContext, 5);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        //点击事件
        mAdapter.setOnItemClickListener(new MeiTAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ToastUtil.showText(mContext, mLists.get(position).getTitle());
            }
        });
    }
}
