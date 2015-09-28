package com.boboeye.luandun.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.boboeye.library.R;
import com.boboeye.luandun.AppConfig;

import org.kymjs.kjframe.utils.DensityUtils;
import org.kymjs.kjframe.utils.ViewUtils;

/**
 * Created by libo_591 on 15/8/6.
 */
public class BasePopupManager {
    private static BasePopupManager _inst=null;
    private BasePopupManager(){}
    private final String TAG = "BasePopupManager";
    private SparseArray<PopupWindow> popupWindows = new SparseArray<PopupWindow>();
    public static BasePopupManager getInst(){
        if(_inst==null){
            _inst = new BasePopupManager();
        }
        return _inst;
    }

    public int addPopup(View view,View anchor,int gravity,int offx,int offy,int width,int height){
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
            popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
            GradientDrawable gd = createGradientDrawable();
            popupWindow.setBackgroundDrawable(gd);
            DismissLis dismisslis = new DismissLis(key);
            popupWindow.setOnDismissListener(dismisslis);
        }
        popupWindow.showAtLocation(anchor, gravity, offx, offy);
        popupWindows.put(key,popupWindow);
        return key;
    }
    public int addPopup(View view,Window window){
        return addPopup(view, window, Gravity.CENTER,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    public int addPopup(View view,Window window,int gravity,int width,int height){
        return addPopup(view, window.getDecorView(), gravity, 0, 0,width,height);
    }
    public int addPopup(View view,Window window,int width,int height){
        return addPopup(view,window,Gravity.CENTER,width,height);
    }
    public int addPopup(View view,View anchor,int gravity,int width,int height){
        return addPopup(view, anchor, gravity, 0, 0,width,height);
    }
    private int toKey(View view){
        return view.getId();
    }

    private PopupWindow getWindow(View view){
        int key = toKey(view);
        return getWindow(key);
    }
    private PopupWindow getWindow(int key){
        PopupWindow popup = popupWindows.get(key);
        return popup;
    }

    public void centerPop(View view,Context context,Window mainWindow){
        int[] wh = AppConfig.getInst().getDisplaySize(context);
        PopupWindow window = getWindow(view);
        if(window==null){
            addPopup(view,mainWindow);
        }else{
            window.showAtLocation(mainWindow.getDecorView(), Gravity.CENTER, 0, 0);
        }
    }
    public void removePop(int key){
        PopupWindow popupWindow = getWindow(key);
        if(popupWindow!=null){
            Log.d(TAG,"remove popupWindow.");
            popupWindow.dismiss();
            popupWindows.remove(key);
        }else{
            Log.d(TAG, "remove popupWindow.but popupWindow is null.");
        }
    }
    public void removePop(View view){
        if(view==null){
            Log.d(TAG,"remove view.but view is null.");
            return;
        }

        removePop(toKey(view));
    }

    public void removeAllPops(){
        int size = popupWindows.size();
        for(int i=0;i<size;i++){
            int key = popupWindows.keyAt(i);
            removePop(key);
        }
    }

    private GradientDrawable createGradientDrawable(){
        Context ctx = AppConfig.getInst().getContext();
        TypedArray typearray = ctx.obtainStyledAttributes(new int[]{
                R.attr.colorPrimary, R.attr.window_bg
        });
        int colorPrimary = typearray.getColor(0, Color.WHITE);
        int colorBack = typearray.getColor(1, Color.WHITE);
        typearray.recycle();
        int tenDP = DensityUtils.dip2px(ctx, 10);
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,new int[]{colorPrimary, colorBack});
        gd.setCornerRadii(new float[]{tenDP, tenDP,tenDP, tenDP, 0, 0, 0, 0});
        gd.setStroke(tenDP / 10, colorPrimary);
        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gd.setGradientCenter(0.5f, 0.5f);
        gd.setShape(GradientDrawable.RECTANGLE);
        return gd;
    }

    class DismissLis implements PopupWindow.OnDismissListener{
        private int key;
        public DismissLis(int key){
            this.key = key;
        }
        @Override
        public void onDismiss() {
            BasePopupManager.getInst().removePop(key);
        }
    }
}
