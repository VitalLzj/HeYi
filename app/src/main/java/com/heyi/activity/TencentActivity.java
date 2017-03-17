package com.heyi.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.heyi.R;
import com.heyi.base.BaseActivity;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HeYi on 2017/3/16 0016.
 * QQ空间
 */
public class TencentActivity extends BaseActivity {


//    ImageInfo是库中提供的数据Bean，需要两个url，
//    分别表示小图和大图的url，没有大图或者小图，则都赋给相同的Url即可。
//    ClickNineGridViewAdapter是库中提供的默认实现了点击预览的Adapter，
//    如果不想使用预览效果，可以自己继承 NineGridViewAdapter 实现其中 onDisplayImage 方法即可。

    private Context mContext;
    @BindView(R.id.nineGrid)
    NineGridView mNineView;

    private String mImgUrl = "http://img4.imgtn.bdimg.com/it/u=787324823,4149955059&fm=23&gp=0.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        setContentView(R.layout.activity_tencent);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        ArrayList<ImageInfo> imageInfos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ImageInfo info = new ImageInfo();
            info.setBigImageUrl(mImgUrl);
            info.setThumbnailUrl(mImgUrl);
            imageInfos.add(info);
        }
        mNineView.setAdapter(new NineGridViewClickAdapter(mContext, imageInfos));
    }
}
