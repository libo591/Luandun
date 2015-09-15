package com.boboeye.luandun.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;

import com.boboeye.library.R;
import com.boboeye.luandun.AppConfig;

/**
 * Created by libo_591 on 15/8/6.
 */
public class BasePopupManager {
    private static final String TAG = "BasePopupManager";
    private static SparseArray<PopupWindow> popupWindows = new SparseArray<PopupWindow>();

    public static int addPopup(View view,View anchor,int gravity,int offx,int offy,int width,int height){
        if(view==null){
            return 0;
        }
        int key = toKey(view);
        Log.d(TAG,"addPopup.key is:"+key);
        PopupWindow popupWindow = getWindow(key);
        if(popupWindow==null) {
            popupWindow = new PopupWindow(view);
            popupWindow.setWidth(width);
            popupWindow.setHeight(height);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setTouchable(true);
            popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
        }
        popupWindow.showAtLocation(anchor, gravity, offx, offy);
        popupWindows.put(key,popupWindow);
        return key;
    }
    public static int addPopup(View view,Window window){
        return addPopup(view, window, Gravity.CENTER,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    public static int addPopup(View view,Window window,int gravity,int width,int height){
        return addPopup(view, window.getDecorView(), gravity, 0, 0,width,height);
    }
    public static int addPopup(View view,Window window,int width,int height){
        return addPopup(view,window,Gravity.CENTER,width,height);
    }
    private static int toKey(View view){
        return view.getId();
    }

    private static PopupWindow getWindow(View view){
        int key = toKey(view);
        return getWindow(key);
    }
    private static PopupWindow getWindow(int key){
        PopupWindow popup = popupWindows.get(key);
        return popup;
    }

    public static void centerPop(View view,Context context,Window mainWindow){
        int[] wh = AppConfig.getInst().getDisplaySize(context);
        PopupWindow window = getWindow(view);
        if(window==null){
            addPopup(view,mainWindow);
        }else{
            window.showAtLocation(mainWindow.getDecorView(), Gravity.CENTER, 0, 0);
        }
    }
    public static void removePop(int key){
        PopupWindow popupWindow = getWindow(key);
        if(popupWindow!=null){
            Log.d(TAG,"remove popupWindow.");
            popupWindow.dismiss();
            popupWindows.remove(key);
        }else{
            Log.d(TAG,"remove popupWindow.but popupWindow is null.");
        }
    }
    public static void removePop(View view){
        if(view==null){
            Log.d(TAG,"remove view.but view is null.");
            return;
        }

        removePop(toKey(view));
    }

    public static void removeAllPops(){
        int size = popupWindows.size();
        for(int i=0;i<size;i++){
            int key = popupWindows.keyAt(i);
            removePop(key);
        }
    }


}
