package com.boboeye.luandun.controller;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.StatFs;
import android.view.View;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.event.ProcessEvent;
import com.boboeye.luandun.event.SpaceCleanerEvent;
import com.boboeye.luandun.model.impl.AutoStartModel;
import com.boboeye.luandun.model.impl.SpaceCleanerModel;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/9/17.
 */
public class SpaceCleanerController extends BaseListController{
    private static final String TAG = SpaceCleanerController.class.getSimpleName();
    private static SpaceCleanerController _inst;
    private SpaceCleanerController(){}
    public static SpaceCleanerController getInst(){
        if(_inst==null){
            _inst = new SpaceCleanerController();
        }
        return _inst;
    }

    @Override
    public void requestSomePage(int index, int count) {
        Class[] clz = {};
        Object[] objs = {};
        doInAsyncTask(this,"doReqDatas",clz,objs,"afterReqDatas");
    }

    public List<BaseModel> doReqDatas(){
        Context ctx = AppConfig.getInst().getContext();
        PackageManager pm = ctx.getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        int size = apps.size();
        final List<BaseModel> models = new ArrayList<BaseModel>(size);
        Method getPackageSizeInfo = null;
        try {
            getPackageSizeInfo = pm.getClass().getMethod(
                    "getPackageSizeInfo", String.class, IPackageStatsObserver.class);
            getPackageSizeInfo.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if(getPackageSizeInfo!=null){
            for(int i=0;i<size;i++){
                final ApplicationInfo appInfo = apps.get(i);
                try {
                    getPackageSizeInfo.invoke(pm,appInfo.packageName,new IPackageStatsObserver.Stub(){
                        @Override
                        public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
                            if(succeeded&&pStats.cacheSize>0){
                                PackageManager pmInMethod = AppConfig.getInst().getContext().getPackageManager();
                                SpaceCleanerModel model = new SpaceCleanerModel();
                                model.setName(appInfo.name);
                                model.setPkgName(appInfo.packageName);
                                model.setId(appInfo.packageName);
                                model.setIcon(appInfo.loadIcon(pmInMethod));
                                model.setLabel(appInfo.loadLabel(pmInMethod).toString());
                                model.setSize(pStats.cacheSize/1024);
                                models.add(model);
                            }
                        }
                    });
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return models;
    }

    public void afterReqDatas(ArrayList<BaseModel> datas){
        getBus().post(new SpaceCleanerEvent(SpaceCleanerEvent.TYPE_BASELIST, datas));
    }

    public void cleanSpace(){
        Class[] clz = {};
        Object[] objs = {};
        doInAsyncTask(this, "doClean", clz, objs, null);
    }

    public void doClean(){
        File dataDir = Environment.getDataDirectory();
        StatFs fs = new StatFs(dataDir.getAbsolutePath());
        Context ctx = AppConfig.getInst().getContext();
        PackageManager pm = ctx.getPackageManager();
        Method freeStorageAndNotify = null;
        try {
            freeStorageAndNotify = pm.getClass().getMethod("freeStorageAndNotify",long.class,IPackageDataObserver.class);
            freeStorageAndNotify.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(freeStorageAndNotify!=null){
            try {
                long sizesum = 0;
                if(Build.VERSION.SDK_INT>=18){
                    sizesum = fs.getBlockCountLong()*fs.getBlockCountLong();
                }else{
                    sizesum = (long)fs.getBlockCount()*(long)fs.getBlockSize();
                }
                freeStorageAndNotify.invoke(pm,sizesum,new IPackageDataObserver.Stub(){
                    @Override
                    public void onRemoveCompleted(String packageName, boolean succeeded) throws RemoteException {
                        handler.sendEmptyMessage(0);
                    }
                });
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            getBus().post(new SpaceCleanerEvent(SpaceCleanerEvent.TYPE_DELETE,null));
        }
    };
}
