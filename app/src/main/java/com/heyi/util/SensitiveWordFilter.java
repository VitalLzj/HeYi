/**
 * @Title: SensitiveWordFilter.java
 * @Package d
 * @Description: TODO
 * @author cp
 * @date 2016年7月6日
 * @version V1.0
 */
package com.heyi.util;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 作者 cp
 * @date 创建时间 2016年7月6日 下午5:37:05
 * @parameter
 * @return
 **/

public class SensitiveWordFilter {

    private int num; // 保存存在多少个敏感词数量
    private HashSet<String> sensitiwordHashSet;// 要查找的敏感词set
    private Map sensitiveHashMap; // 用敏感词构造的hashmap
    private HashSet<String> foundSensitiveWordSet; // 用于保存已经发现的敏感词
    private Context mContext;

    public SensitiveWordFilter(Context context) {
        this.mContext = context;
        initFilter();
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: TODO
     */
    public void initFilter() {
        SensitiveWord instanceSensitiveWord = SensitiveWord.getInstance();
        instanceSensitiveWord.init(mContext);
        sensitiveHashMap = instanceSensitiveWord.getSensitiveHashMap();
        sensitiwordHashSet = instanceSensitiveWord.getSensitiwordHashSet();
        foundSensitiveWordSet = new HashSet<String>();
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 遍历待处理文档中的每个字开头到最后，与敏感字构成的hashmap进行匹对
     */
    public void findSensitiveWordInTxt(String txtString) {
        for (int i = 0; i < txtString.length(); i++) {
            if (isContainSensitiveWord(i, txtString))
                num++;
        }
    }

    private static final String TAG = "SensitiveWordFilter";

    /**
     * @param
     * @return void
     * @throws
     * @Description: TODO
     */
    public String printResult() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = foundSensitiveWordSet.iterator();
        sb.append("敏感词为");
        while (iterator.hasNext()) {
            //这里需要赋值一个变量 否则汇报java.util.NoSuchElementException
            String s = iterator.next();
            if (!"的".equals(s)) {
                //研究发现 这个的会被屏蔽
                sb.append(s).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * @param @param i
     * @param @param str
     * @return 是否存在敏感词
     * @throws
     * @Description:
     */
    private boolean isContainSensitiveWord(int i, String txt) {
        int length = 0; // 已匹配的敏感字长度,每找对一个字，加1
        boolean isExist = false;
        Map nowWorkMap = sensitiveHashMap;
        for (int j = i; j < txt.length(); j++) { // 从开头第一个字开始匹对
            Map wordMap = (Map) nowWorkMap.get(txt.charAt(j));
            Log.d(TAG, "isContainSensitiveWord: " + "当前处理的txt中的word是" + txt.charAt(j));
            if (wordMap == null) {
                break; // 文档中的当前字不匹配敏感词中的字，退出
            } else {
                length++;
                Log.d(TAG, "isContainSensitiveWord: isEnd" + wordMap.get("isEnd").toString());
                if (wordMap.get("isEnd").toString().equals("1"))
                    isExist = true;
                nowWorkMap = wordMap;
            }

        }

        if (isExist) {
            foundSensitiveWordSet.add(txt.substring(i, i + length));
        }
        return isExist;
    }
}
