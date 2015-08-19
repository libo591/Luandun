package com.boboeye.luandun.model.impl;

import android.graphics.Bitmap;

import com.boboeye.luandun.base.BaseModel;

/**
 * Created by libo_591 on 15/8/5.
 */
public class NetModel extends BaseModel {
    private Object icon;
    private String title;
    private String url;
    private int sort;

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
