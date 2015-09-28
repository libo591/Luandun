package com.boboeye.luandun.model.impl;

import android.graphics.drawable.Drawable;

import com.boboeye.luandun.base.BaseModel;

/**
 * Created by libo_591 on 15/9/17.
 */
public class SpaceCleanerModel extends BaseModel {
    private String label;
    private Drawable icon;
    private String name;
    private String pkgName;
    private long size;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
