package com.boboeye.luandun.model.impl;

import com.boboeye.luandun.base.BaseModel;

/**
 * Created by libo_591 on 15/9/27.
 */
public class ThemeModel extends BaseModel {
    private String name;
    private int preview;
    private int themeRes;
    private int textpreview;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPreview() {
        return preview;
    }

    public void setPreview(int preview) {
        this.preview = preview;
    }

    public int getThemeRes() {
        return themeRes;
    }

    public void setThemeRes(int themeRes) {
        this.themeRes = themeRes;
    }

    public int getTextpreview() {
        return textpreview;
    }

    public void setTextpreview(int textpreview) {
        this.textpreview = textpreview;
    }
}
