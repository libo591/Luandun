package com.boboeye.luandun.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.model.impl.AutoStartModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/9/18.
 */
public class AutoStarterUtil {
    public static List<BaseModel> referAutoStartList(){
        Context ctx = AppConfig.getInst().getContext();
        PackageManager pm = ctx.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_BOOT_COMPLETED);
        List<ResolveInfo> list = pm.queryBroadcastReceivers(intent, PackageManager.GET_DISABLED_COMPONENTS);
        int size = list.size();
        List<BaseModel> models = new ArrayList<BaseModel>(size);
        for(int i=0;i<size;i++){
            ResolveInfo info = list.get(i);
            if((info.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0){
                continue;
            }
            AutoStartModel existModel = null;
            for(int j=0;j<models.size();j++){
                AutoStartModel model = (AutoStartModel)models.get(j);
                if(model.getPkgName().equals(info.activityInfo.packageName)){
                    existModel = model;
                    break;
                }
            }
            if(existModel==null){
                existModel = new AutoStartModel();
                existModel.setId(i + "");
                existModel.setLabel(info.loadLabel(pm).toString());
                existModel.setIsSystem(false);
                existModel.setPkgName(info.activityInfo.packageName);
                existModel.setIcon(info.loadIcon(pm));
                ComponentName cn = new ComponentName(info.activityInfo.packageName,info.activityInfo.name);
                int enable = pm.getComponentEnabledSetting(cn);
                existModel.setEnable(enable != 2);
                existModel.setName(info.activityInfo.name);
                models.add(existModel);
            }else{
                existModel.setName(existModel.getName() + ";" + info.activityInfo.name);
            }
        }
        return models;
    }
}
