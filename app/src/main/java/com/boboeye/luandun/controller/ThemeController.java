package com.boboeye.luandun.controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.event.ThemeEvent;
import com.boboeye.luandun.model.impl.ThemeModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/9/27.
 */
public class ThemeController extends BaseListController {
    private static final String TAG = ThemeController.class.getSimpleName();
    private static ThemeController _inst;
    private ThemeController(){}
    public static ThemeController getInst(){
        if(_inst==null){
            _inst = new ThemeController();
        }
        return _inst;
    }

    @Override
    protected void requestSomePage(int index, int count) {
        Class[] clz = {};
        Object[] objs = {};
        doInAsyncTask(this, "doRequestPages", clz, objs, "afterRequestPages");
    }

    public List<BaseModel> doRequestPages(){
        Context ctx = AppConfig.getInst().getContext();
        String themeconfig = null;
        InputStream themeconfigis = null;
        ByteArrayOutputStream bos = null;
        try {
            themeconfigis = ctx.getResources().openRawResource(R.raw.themeconfig);
            byte[] buffer = new byte[1024];
            int len = -1;
            bos = new ByteArrayOutputStream();
            while ((len = themeconfigis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
            themeconfig = bos.toString("UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bos.close();
                themeconfigis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG,"theme config>>>>"+themeconfig);
        JSONArray themearray = JSONObject.parseArray(themeconfig);
        int size = themearray.size();
        List<BaseModel> result = new ArrayList<BaseModel>(size);
        for(int i=0;i<size;i++){
            JSONObject themeObj = themearray.getJSONObject(i);
            ThemeModel tm = new ThemeModel();
            tm.setName(themeObj.getString("name"));
            tm.setPreview(Color.parseColor(themeObj.getString("preview")));
            String resname = themeObj.getString("style");
            tm.setThemeRes(ctx.getResources().getIdentifier(resname,"style",ctx.getPackageName()));
            tm.setTextpreview(Color.parseColor(themeObj.getString("textcolor")));
            result.add(tm);
        }
        return result;
    }

    public void afterRequestPages(ArrayList datas){
        getBus().post(new ThemeEvent(ThemeEvent.TYPE_BASELIST,datas));
    }
}
