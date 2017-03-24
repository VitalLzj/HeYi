package com.heyi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.heyi.activity.MeiTActivity;
import com.heyi.activity.TaoBActivity;
import com.heyi.activity.TencentActivity;
import com.heyi.activity.WordsActivity;
import com.heyi.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mei_t, R.id.qq, R.id.tao_bao, R.id.words})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mei_t:
                startActivity(new Intent(mContext, MeiTActivity.class));
                break;
            case R.id.qq:
                startActivity(new Intent(mContext, TencentActivity.class));
                break;
            case R.id.tao_bao:
                startActivity(new Intent(mContext, TaoBActivity.class));
                break;
            case R.id.words:
                startActivity(new Intent(mContext, WordsActivity.class));
                break;
        }
    }

}
