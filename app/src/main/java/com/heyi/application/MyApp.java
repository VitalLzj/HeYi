package com.heyi.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.heyi.R;
import com.lzy.ninegrid.NineGridView;
import com.squareup.picasso.Picasso;

/**
 * 自定义application
 * Created by HeYi on 2017/3/16 0016.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NineGridView.setImageLoader(new PicassoImageLoader());

    }

    /**
     * Picasso 加载
     */
    private class PicassoImageLoader implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Picasso.with(context).load(url)//
                    .placeholder(R.drawable.ic_default_image)//
                    .error(R.drawable.ic_default_image)//
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }
}
