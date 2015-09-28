package com.boboeye.luandun.model.impl;

import android.graphics.drawable.Drawable;

import com.boboeye.luandun.base.BaseModel;

/**
 * Created by libo_591 on 15/9/17.
 */
public class AutoStartModel extends BaseModel {
    private String label;
    private Drawable icon;
    private String name;
    private String pkgName;
    private boolean isSystem;
    private boolean enable;

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

    public boolean isSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
