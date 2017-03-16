package com.heyi.bean;

import java.io.Serializable;

/**
 * Created by  on 2017/3/15 0015.
 * 公司：HeYi
 * 美团实体类
 */
public class Mei_T implements Serializable {
    private String id;
    private String title;
    //由于图片地址用的本地图片，所以类型为int 如果用网络图片 改为String类型
    private int imgId;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
