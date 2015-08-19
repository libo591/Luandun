package com.boboeye.luandun.controller;

import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;

import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.base.BaseLocalModelService;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.event.NetEvent;
import com.boboeye.luandun.model.impl.NetModel;
import com.boboeye.luandun.model.service.impl.NetModelService;

import org.kymjs.kjframe.KJBitmap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by libo_591 on 15/8/5.
 */
public class NetController extends BaseListController {
    private static final String TAG = "NetController";
    private static NetController _inst = new NetController();
    private NetController(){}
    public static NetController getInst(){
        return _inst;
    }
    private NetModelService mNetModelService = new NetModelService();

    //============refer
    public void requestSomePage(int page,int count){
        Integer[] params = new Integer[2];
        params[0] = page;
        params[1] = count;
        Class[] clzs = new Class[]{Integer.class,Integer.class};
        doInAsyncTask(this,"realdoRequestNetPage",clzs,params,"afterRequestNetPage");
    }

    public List<BaseModel> realdoRequestNetPage(Integer page,Integer count){
        BaseLocalModelService localService = this.mNetModelService.getLocalService();
        List<BaseModel> netModels= localService.referDatas();
        //debug
        if(netModels.size()<=0){
            Log.d(TAG,"数据小于0，使用模拟数据");
            netModels = new ArrayList();
            NetModel netmodel = new NetModel();
            netmodel.setId("1001");
            netmodel.setTitle("测试网站管理标题1");
            netmodel.setUrl("http://www.baidu.com");
            netmodel.setSort(1);
            netmodel.setIcon("http://www.baidu.com/favicon.ico");
            netModels.add(netmodel);
        }
        return netModels;
    }
    public void afterRequestNetPage(ArrayList datas){
        Log.d(TAG, "获取的数据大小:>" + datas.size());
        int mState = 0;
        if(mPageIndex==0&&datas.size()<countPerPage){
            mState = BaseListAdapter.FOOTERSTATE_LESSTHANONEPAGE;
        }else{
            mState = BaseListAdapter.FOOTERSTATE_LOADED;
        }
        NetEvent netEvent = new NetEvent(mState,datas,NetEvent.TYPE_BASELIST);
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
    public void afterEdit(Integer state){
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
    public void afterDelete(Integer state){
        NetEvent netEvent = new NetEvent(state,null,NetEvent.TYPE_DELETE);
        getBus().post(netEvent);
    }


    //========add
    public void add(NetModel nm){
        Log.d(TAG,"add:"+nm.getId());
        NetModel[] params = new NetModel[]{nm};
        Class[] clzs = new Class[]{NetModel.class};
        doInAsyncTask(this, "realAdd", clzs, params, "afterAdd");
    }

    public int realAdd(NetModel nm){
        BaseLocalModelService localService = this.mNetModelService.getLocalService();
        return localService.createData(nm);
    }
    public void afterAdd(Integer state){
        NetEvent netEvent = new NetEvent(state,null,NetEvent.TYPE_ADD);
        getBus().post(netEvent);
    }
}
