package com.boboeye.luandun.controller;

import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;

import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseLocalModelService;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.event.NetEvent;
import com.boboeye.luandun.model.impl.NetModel;
import com.boboeye.luandun.model.service.impl.NetModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by libo_591 on 15/8/5.
 */
public class NetController extends BaseController {
    private static final String TAG = "NetController";
    private static NetController _inst = new NetController();
    private NetController(){}
    public static NetController getInst(){
        return _inst;
    }
    private NetModelService mNetModelService = new NetModelService();

    //============refer
    public void requestNetPage(int page,int count){
        Integer[] params = new Integer[2];
        params[0] = page;
        params[1] = count;
        Class[] clzs = new Class[]{Integer.class,Integer.class};
        doInAsyncTask(this,"realdoRequestNetPage",clzs,params,"afterRequestNetPage");
    }

    public List<BaseModel> realdoRequestNetPage(int page,int count){
        BaseLocalModelService localService = this.mNetModelService.getLocalService();
        List<BaseModel> netModels= localService.referDatas();
        return netModels;
    }
    public void afterRequestNetPage(List<BaseModel> datas){
        Log.d(TAG, "获取的数据大小:>" + datas.size());
        NetEvent netEvent = new NetEvent(1,datas,NetEvent.TYPE_BASELIST);
        getBus().post(netEvent);
    }

    //============edit
    public void edit(NetModel nm){
        Log.d(TAG, "edit:" + nm.getId());
        NetModel[] params = new NetModel[]{nm};
        Class[] clzs = new Class[]{NetModel.class};
        doInAsyncTask(this, "realdoEdit", clzs, params, "afterEdit");
    }

    public int realdoEdit(NetModel nm){
        BaseLocalModelService localService = this.mNetModelService.getLocalService();
        return localService.updateData(nm);
    }
    public void afterEdit(int state){
        NetEvent netEvent = new NetEvent(state,null,NetEvent.TYPE_EDIT);
        getBus().post(netEvent);
    }

    //============delete
    public void delete(NetModel nm){
        Log.d(TAG,"delete:"+nm.getId());
        NetModel[] params = new NetModel[]{nm};
        Class[] clzs = new Class[]{NetModel.class};
        doInAsyncTask(this, "realDelete", clzs, params, "afterDelete");
    }

    public int realDelete(NetModel nm){
        BaseLocalModelService localService = this.mNetModelService.getLocalService();
        return localService.delData(nm);
    }
    public void afterDelete(int state){
        NetEvent netEvent = new NetEvent(state,null,NetEvent.TYPE_DELETE);
        getBus().post(netEvent);
    }

}
