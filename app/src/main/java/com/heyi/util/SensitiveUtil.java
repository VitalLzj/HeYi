package com.heyi.util;

import android.content.Context;

/**
 * Created by HeYi on 2017/3/24 0024.
 * 检索敏感词
 */
public class SensitiveUtil {

    /**
     * @param s 待检测语句
     * @param context 上下文对象
     * @return 敏感词
     */
    public static String getSensitiveWords(String s, Context context) {
        SensitiveWordFilter sFilter = new SensitiveWordFilter(context);
        sFilter.findSensitiveWordInTxt(s);
        return sFilter.printResult();
    }

}
