package com.boboeye.luandun.model.impl;

import com.boboeye.luandun.base.BaseModel;

/**
 * Created by libo_591 on 15/8/22.
 */
public class ProcessModel extends BaseModel {
    private String name;
    private int importance;
    private int memory;
    private String packageName;

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
}
