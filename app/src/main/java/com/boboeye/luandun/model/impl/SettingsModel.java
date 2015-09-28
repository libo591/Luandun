package com.boboeye.luandun.model.impl;

import com.boboeye.luandun.base.BaseModel;

/**
 * Created by libo_591 on 15/9/17.
 */
public class SettingsModel extends BaseModel {
    private String title;
    private String content;
    private String linkurl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }
}
