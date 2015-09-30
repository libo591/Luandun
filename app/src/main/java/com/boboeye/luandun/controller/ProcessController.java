package com.boboeye.luandun.controller;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Debug;
import android.util.Log;
import android.view.View;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.event.ProcessEvent;
import com.boboeye.luandun.event.WebSiteEvent;
import com.boboeye.luandun.model.impl.ProcessModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by libo_591 on 15/8/22.
 */
public class ProcessController extends BaseListController {
    private static final String TAG = "ProcessController";
    private static ProcessController _inst = new ProcessController();
    private ProcessController(){}
    public static ProcessController getInst(){
        return _inst;
    }
    @Override
    protected void requestSomePage(int index, int count) {
        Class[] clzs = {};
        Object[] objs = {};
        doInAsyncTask(this,"doRequestSomePage",clzs,objs,"afterReqSomePage");
    }

    public List<BaseModel> doRequestSomePage(){
        Context ctx = AppConfig.getInst().getContext();
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        PackageManager packMan = ctx.getPackageManager();
        List<ApplicationInfo> applist = packMan.getInstalledApplications(0);

        List<ActivityManager.RunningAppProcessInfo> appPro = am.getRunningAppProcesses();
        int size = appPro.size();
        Log.d(TAG, "appProcess数目:" + size);
        List<BaseModel> list = new ArrayList<BaseModel>(size);
        for(int i=0;i<size;i++){
            ActivityManager.RunningAppProcessInfo pro = appPro.get(i);
            if(pro.importance<= ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                ProcessModel pm = new ProcessModel();
                pm.setId(pro.pid + "");
                //pm.setName(pro.processName);

                for (Iterator<ApplicationInfo> iter = applist.iterator(); iter.hasNext(); ) {
                    ApplicationInfo appInfo = iter.next();
                    if (appInfo.processName.equals(pro.processName)) {
                        pm.setName(appInfo.loadLabel(packMan).toString());
                        pm.setIcon(appInfo.loadIcon(packMan));
                        pm.setPackageName(appInfo.packageName);
                        break;
                    }
                }
                pm.setImportance(pro.importance);
                list.add(pm);
            }
        }
        int listsize = list.size();
        int[] pids = new int[listsize];
        for(int i=0;i<listsize;i++){
            pids[i] = Integer.parseInt(list.get(i).getId());
        }
        Debug.MemoryInfo[] memoryinfos = am.getProcessMemoryInfo(pids);
        int msize = memoryinfos.length;
        for(int i=0;i<msize;i++){
            Debug.MemoryInfo memoryinfo = memoryinfos[i];
            ((ProcessModel)list.get(i)).setMemory(memoryinfo.dalvikPrivateDirty);
        }
        return list;

    }

    public void afterReqSomePage(ArrayList<BaseModel> list){
        getBus().post(new ProcessEvent(ProcessEvent.TYPE_BASELIST, list));
    }



    public void killProccess(ProcessModel pm,int position){
        Class[] clz = {ProcessModel.class,int.class};
        Object[] obj = {pm,position};
        doInAsyncTask(this,"doKillProcess",clz,obj,"afterKillProcess");
    }

    public int doKillProcess(ProcessModel pm,int position){
        Context ctx = AppConfig.getInst().getContext();
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        int result = position;
        try {
            am.killBackgroundProcesses(pm.getPackageName());
        }catch(Throwable e){
            e.printStackTrace();
            result = -1;
        }
        return result;
    }
    public void afterKillProcess(Integer position){
        List datas = new ArrayList(1);
        datas.add(position);
        getBus().post(new ProcessEvent(ProcessEvent.TYPE_DELETE, datas));
    }
}
