package com.boboeye.luandun;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.WindowManager;

/**
 * Created by libo_591 on 15/8/5.
 */
public class AppConfig {
    private static AppConfig _inst = new AppConfig();
    private Context mContext;
    public static AppConfig getInst(){
        return _inst;
    }

    public void setContext(Context context){
        mContext = context;
    }
    public Context getContext(){
        return mContext;
    }

    public int getAppVersion(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int[] getDisplaySize(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        return new int[]{wm.getDefaultDisplay().getWidth(),wm.getDefaultDisplay().getHeight()};
    }
}
