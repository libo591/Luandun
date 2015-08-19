package com.boboeye.luandun.model.service;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.LuanApplication;
import com.boboeye.luandun.base.BaseLocalModelService;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.model.impl.NetModel;
import com.jakewharton.disklrucache.DiskLruCache;

import org.apache.http.NameValuePair;
import org.kymjs.kjframe.utils.CipherUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/7/26.
 */
public class CacheLocalModelService extends BaseLocalModelService {
    private static final String TAG = "CacheLocalModelService";
    //===========================need implements

    /**
     * 缓存的key值，一般比如列表用缓存保存最后一次请求页面数据，会存到一个缓存文件中，此key用于得到此缓存文件
     * @return
     */
    public String getCacheKey(){
        return "";
    }

    /**
     * 获取Model实体类的类对象，用于缓存文件到对象的解析映射
     * @return
     */
    public Class getModelClass(){
        return BaseModel.class;
    }
    //===========================need implements

    public DiskLruCache getLruCache(String filePath){
        File cacheDir = new File(filePath);
        if(!cacheDir.exists()){
            cacheDir.mkdirs();
        }
        DiskLruCache cache = null;
        try {
            cache = DiskLruCache.open(cacheDir, AppConfig.getInst().getAppVersion(), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cache;
    }

    @Override
    public int createData(BaseModel bm) {
        DiskLruCache cache = getLruCache(getCreateFilePath());
        DiskLruCache.Editor cacheeditor = null;
        try {
            DiskLruCache.Snapshot snapshot = cache.get(getCacheKey());
            List<BaseModel> models = null;
            if(snapshot==null){
                models = new ArrayList<BaseModel>();
                models.add(bm);
            }else{
                String jsonString = snapshot.getString(0);
                Class clz = bm.getClass();
                models = JSONArray.parseArray(jsonString, clz);
                models.add(bm);
            }
            cacheeditor = cache.edit(getCacheKey());
            cacheeditor.set(0, JSONArray.toJSONString(models));
            cacheeditor.commit();
        }catch(Exception e){
            e.printStackTrace();
            if(cacheeditor!=null){
                try {
                    cacheeditor.abort();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return 0;
        }
        return 1;
    }

    public int createDataList(List<BaseModel> datas){
        if(datas==null||datas.size()<0){
            return 0;
        }
        DiskLruCache cache = getLruCache(getCreateFilePath());
        List jsonArray = referDatas();
        jsonArray.addAll(datas);
        String key = CipherUtils.md5(this.getCacheKey());
        DiskLruCache.Editor editor = null;
        try {
            editor = cache.edit(key);
            editor.set(0, JSONArray.toJSONString(jsonArray));
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            if(editor!=null){
                try {
                    editor.abort();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return 0;
        }
        return 1;
    }
    public int delData(BaseModel bm){
        DiskLruCache cache = getLruCache(getDeleteFilePath());
        DiskLruCache.Editor cacheeditor = null;
        try {
            DiskLruCache.Snapshot snapshot = cache.get(getCacheKey());
            if(snapshot==null){
                return 0;
            }
            String jsonString = snapshot.getString(0);
            Log.d(TAG,"delData:"+jsonString);
            List<? extends BaseModel> models = JSONArray.parseArray(jsonString, bm.getClass());
            int size = models.size();
            for (int i = 0; i < size; i++) {
                BaseModel model = models.get(i);
                if (model.getId().equals(bm.getId())) {
                    models.remove(i);
                    break;
                }
            }
            cacheeditor = cache.edit(getCacheKey());
            cacheeditor.set(0, JSONArray.toJSONString(models));
            cacheeditor.commit();
        }catch(Exception e){
            e.printStackTrace();
            if(cacheeditor!=null){
                try {
                    cacheeditor.abort();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return 0;
        }
        return 1;
    }
    public int delDataList(List<BaseModel> datas){
        DiskLruCache cache = getLruCache(getDeleteFilePath());
        try {
            if(cache.remove(getCacheKey())){
                return datas.size();
            }else{
                return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<BaseModel> referDatas(){
        DiskLruCache cache = getLruCache(getReferFilePath());
        List modelList = null;
        try {
            DiskLruCache.Snapshot cacheData = cache.get(getCacheKey());
            if(cacheData!=null){
                String data = cacheData.getString(0);
                Log.d(TAG,"referDatas:"+data);
                modelList = JSONArray.parseArray(data, getModelClass());
            }else{
                modelList = new ArrayList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelList;
    }

    public int updateData(BaseModel bm){
        DiskLruCache cache = getLruCache(getUpdateFilePath());
        DiskLruCache.Editor cacheeditor = null;
        try {
            DiskLruCache.Snapshot snapshot = cache.get(getCacheKey());
            if(snapshot==null){
                return 0;
            }
            String jsonString = snapshot.getString(0);
            Class clz= bm.getClass();
            List<BaseModel> models = JSONArray.parseArray(jsonString,clz);
            int size = models.size();
            for (int i = 0; i < size; i++) {
                BaseModel model = models.get(i);
                if (model.getId().equals(bm.getId())) {
                    models.set(i, bm);
                    break;
                }
            }
            cacheeditor = cache.edit(getCacheKey());
            cacheeditor.set(0, JSONArray.toJSONString(models));
            cacheeditor.commit();
        }catch(Exception e){
            e.printStackTrace();
            if(cacheeditor!=null){
                try {
                    cacheeditor.abort();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return 0;
        }
        return 1;
    }
    public int updateDataList(List<BaseModel> datas){
        DiskLruCache cache = getLruCache(getUpdateListFilePath());
        DiskLruCache.Editor editor = null;
        try {
            DiskLruCache.Snapshot snap = cache.get(getCacheKey());
            if(snap==null){
                return 0;
            }
            editor = snap.edit();
            editor.set(0,JSONArray.toJSONString(datas));
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            if(editor!=null){
                try {
                    editor.abort();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return 0;
        }
        return 1;
    }
}
