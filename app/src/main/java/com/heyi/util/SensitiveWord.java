package com.heyi.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author 作者 cp
 * @version 1.0
 * @date 创建时间 2016年7月1日 上午10:27:54
 * @parameter
 * @return
 **/
public class SensitiveWord {

    private Context mContext;
    private HashSet<String> sensitiwordHashSet;  //保存要查找的敏感词
    private Map sensitiveHashMap;        //敏感词构造的hashmap

    // Constructor
    private SensitiveWord() {
    }

    private static final SensitiveWord SENSITIVE_WORD = new SensitiveWord();

    public static SensitiveWord getInstance() {
        return SENSITIVE_WORD;
    }

    private static final String TAG = "SensitiveWord";

    /**
     * @param
     * @return void
     * @throws
     * @Description: 读取敏感词文件，存入hashset中
     */
    private void readfile() {
        sensitiwordHashSet = new HashSet<String>();
        String str;
        BufferedReader bufferedReader = null;
        AssetManager asset = mContext.getAssets();
        try {
            InputStream open = asset.open("words.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(open, "unicode");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((str = bufferedReader.readLine()) != null) {
                sensitiwordHashSet.add(str.trim());
//                Log.d(TAG, "readfile: " + str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally { // 关闭流
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    private void consSensitiveHashMap(HashSet<String> wordsSet) {

        sensitiveHashMap = new HashMap(wordsSet.size());

        Map nowMap;
        Map temhashmap;
        String key;

        Iterator<String> iterator = wordsSet.iterator();

        while (iterator.hasNext()) {
            nowMap = sensitiveHashMap; // 当前处理的map
            key = iterator.next();
            Log.d(TAG, "consSensitiveHashMap: " + key);

            for (int i = 0; i < key.length(); i++) { // 遍历每个词的每个字
                char oneWord_char = key.charAt(i);

                Object wordMap = nowMap.get(oneWord_char);// 查找当前map，看是否有当前的字
                // 若有，则对应的value(对应的hash树)给wordMap

                if (wordMap != null) { // 存在有这个字
                    nowMap = (Map) wordMap;
                } else {
                    temhashmap = new HashMap<String, String>();
                    if (i != key.length() - 1) {
                        temhashmap.put("isEnd", 0);
                    } else {
                        temhashmap.put("isEnd", 1);
                    }

                    nowMap.put(oneWord_char, temhashmap);
                    nowMap = temhashmap;

                }

            }
        }

        Iterator iterator2 = sensitiveHashMap.entrySet().iterator();
        while (iterator2.hasNext()) {
            Entry entry = (Entry) iterator2.next();
            System.out.println("The key of hashmap is  " + entry.getKey());
        }

    }


    /**
     * @param
     * @return void
     * @throws
     * @Description: 初始化
     */
    public void init(Context context) {
        mContext = context;
        readfile();
        consSensitiveHashMap(sensitiwordHashSet);

    }

    /**
     * @return the sensitiwordHashSet
     */
    public HashSet<String> getSensitiwordHashSet() {
        return sensitiwordHashSet;
    }

    /**
     * @return the sensitiveHashMap
     */
    public Map getSensitiveHashMap() {
        return sensitiveHashMap;
    }

}
