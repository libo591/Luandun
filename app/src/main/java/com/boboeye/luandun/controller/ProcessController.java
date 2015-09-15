package com.boboeye.luandun.controller;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Debug;
import android.util.Log;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.event.ProcessEvent;
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
        Context ctx = AppConfig.getInst().getContext();
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        PackageManager packMan = ctx.getPackageManager();
        List<ApplicationInfo> applist = packMan.getInstalledApplications(0);

        List<ActivityManager.RunningServiceInfo> ss = am.getRunningServices(Integer.MAX_VALUE);
        List<ActivityManager.RunningAppProcessInfo> appPro = am.getRunningAppProcesses();
        int sizeService = ss.size();
        Log.d(TAG, "appService数目:" + sizeService);
        int size = appPro.size();
        Log.d(TAG, "appProcess数目:" + size);
        List<ProcessModel> list = new ArrayList<ProcessModel>(size);
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
                        pm.setPackageName(appInfo.packageName);
                        break;
                    }
                }
                pm.setImportance(pro.importance);
                list.add(pm);
            }
        }
        for(int i=0;i<sizeService;i++){
            ActivityManager.RunningServiceInfo service = ss.get(i);
            if(service.foreground) {
                ProcessModel pm = new ProcessModel();
                pm.setId(service.pid + "");
                pm.setName("service:" + service.process +
                        "-" + service.service.getShortClassName());
                pm.setPackageName(service.service.getPackageName());
                pm.setImportance(ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND);
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
            list.get(i).setMemory(memoryinfo.dalvikPrivateDirty);
        }

        getBus().post(new ProcessEvent(ProcessEvent.TYPE_BASELIST,list));
    }
}
