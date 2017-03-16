package com.heyi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.heyi.activity.MeiTActivity;
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

    @OnClick({R.id.mei_t})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mei_t:
                startActivity(new Intent(mContext, MeiTActivity.class));
                break;
        }
    }

}
