package com.boboeye.luandun;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.boboeye.library.R;
import com.boboeye.luandun.base.BaseActivityStack;
import com.boboeye.luandun.base.BasePopupManager;

/**
 * Created by libo_591 on 15/8/5.
 */
public class AppConfig {
    public static final String PREFER_COMMONNAME = "luandun";
    private static AppConfig _inst = new AppConfig();
    private AppConfig(){}
    private Context mContext;
    private Typeface typeFace;
    public static AppConfig getInst(){
        return _inst;
    }


    public void setContext(Context context){
        mContext = context;
        typeFace = Typeface.createFromAsset(mContext.getAssets(), "iconfont/iconfont.ttf");
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

    public void setPrefer(String key,String value){
        SharedPreferences sp = mContext.getSharedPreferences(PREFER_COMMONNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getPrefer(String key,String defValue){
        SharedPreferences sp = mContext.getSharedPreferences(PREFER_COMMONNAME, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }

    public Typeface getTypeFace() {
        return typeFace;
    }

    public void setTypeFace(Typeface typeFace) {
        this.typeFace = typeFace;
    }
}
