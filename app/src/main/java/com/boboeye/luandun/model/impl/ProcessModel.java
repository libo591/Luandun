package com.boboeye.luandun.model.impl;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.boboeye.luandun.base.BaseModel;

/**
 * Created by libo_591 on 15/8/22.
 */
public class ProcessModel extends BaseModel {
    private String name;
    private int importance;
    private int memory;
    private String packageName;
    private Drawable icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
