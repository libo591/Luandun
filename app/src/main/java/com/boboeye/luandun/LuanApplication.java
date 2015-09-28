package com.boboeye.luandun;


import android.content.Context;
import android.util.Log;

import com.boboeye.luandun.base.BaseApplication;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by libo_591 on 15/7/25.
 */
public class LuanApplication extends BaseApplication{
    private static final String TAG = LuanApplication.class.getSimpleName();
    private int appVersion;
    private RefWatcher watcher;

    @Override
    public void onCreate() {
        super.onCreate();
        //BaseUncaughtExceptionHandler baseun = new BaseUncaughtExceptionHandler();
        //Thread.setDefaultUncaughtExceptionHandler(baseun);
        watcher = LeakCanary.install(this);
        //watcher = RefWatcher.DISABLED;
        long totleMem = Runtime.getRuntime().totalMemory();
        Log.d(TAG, "应用总内存:" + totleMem / 1024 + "K");
    }

    public static RefWatcher getLeak(Context context){
        LuanApplication app = (LuanApplication) context.getApplicationContext();
        return app.watcher;
        //return RefWatcher.DISABLED;
    }

}
