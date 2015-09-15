package com.boboeye.luandun.base;

import android.content.SharedPreferences;

import com.boboeye.luandun.AppConfig;

/**
 * Created by libo_591 on 15/8/3.
 */
public class BaseInfoStore {
    private SharedPreferences mSharePreferences;
    public BaseInfoStore(String name){
        mSharePreferences = AppConfig.getInst().getContext().getSharedPreferences(name, 0);
    }
    public void put(String key,String value){
        SharedPreferences.Editor edit = mSharePreferences.edit();
        edit.putString(key,value);
        edit.apply();
    }

    public String get(String key,String def){
        return mSharePreferences.getString(key,def);
    }

    public void remove(String key){
        SharedPreferences.Editor edit = mSharePreferences.edit();
        edit.remove(key);
        edit.apply();
    }
}
