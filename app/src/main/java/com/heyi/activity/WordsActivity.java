package com.heyi.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.heyi.R;
import com.heyi.base.BaseActivity;
import com.heyi.util.SensitiveUtil;
import com.heyi.util.SensitiveWordFilter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HeYi on 2017/3/23 0023.
 * 关键字屏蔽
 */
public class WordsActivity extends BaseActivity {

    @BindView(R.id.words_input)
    EditText mInputEditText;
    @BindView(R.id.words_result)
    TextView mResultText;
    private static final String TAG = "WordsActivity";

    private MyTask mTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.words_check)
    public void onClick() {
        mTask = new MyTask();
        mTask.execute(mInputEditText.getText().toString());
    }

    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: ");
            mResultText.setText("正在检测");
        }

        @Override
        protected String doInBackground(String... strings) {
            return SensitiveUtil.getSensitiveWords(strings[0], WordsActivity.this);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mResultText.setText(s);
        }
    }


}
