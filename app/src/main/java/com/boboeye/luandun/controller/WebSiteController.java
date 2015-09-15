package com.boboeye.luandun.controller;

import android.util.Log;

import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.base.BaseLocalModelService;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.event.WebSiteEvent;
import com.boboeye.luandun.model.impl.WebSiteModel;
import com.boboeye.luandun.model.service.impl.WebSiteModelService;
import com.boboeye.luandun.utils.UrlUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/8/5.
 */
public class WebSiteController extends BaseListController {
    private static final String TAG = "WebSiteController";
    private static WebSiteController _inst = new WebSiteController();
    private WebSiteController(){}
    public static WebSiteController getInst(){
        return _inst;
    }
    private WebSiteModelService mNetModelService = new WebSiteModelService();

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
            Log.d(TAG, "数据小于0，使用模拟数据");
            netModels.addAll(createSystemDatas());
        }
        return netModels;
    }
    public void afterRequestNetPage(ArrayList datas){
        Log.d(TAG, "获取的数据大小:>" + datas.size());
        WebSiteEvent webSiteEvent = new WebSiteEvent(datas, WebSiteEvent.TYPE_BASELIST);
        getBus().post(webSiteEvent);
    }

    //============edit
    public void edit(WebSiteModel nm){
        Log.d(TAG, "edit:" + nm.getId());
        WebSiteModel[] params = new WebSiteModel[]{nm};
        Class[] clzs = new Class[]{WebSiteModel.class};
        doInAsyncTask(this, "realdoEdit", clzs, params, "afterEdit");
    }

    public int realdoEdit(WebSiteModel nm){
        BaseLocalModelService localService = this.mNetModelService.getLocalService();
        return localService.updateData(nm);
    }
    public void afterEdit(Integer state){
        WebSiteEvent webSiteEvent = new WebSiteEvent(null, WebSiteEvent.TYPE_EDIT);
        getBus().post(webSiteEvent);
    }

    //============delete
    public void delete(WebSiteModel nm){
        Log.d(TAG,"delete:"+nm.getId());
        WebSiteModel[] params = new WebSiteModel[]{nm};
        Class[] clzs = new Class[]{WebSiteModel.class};
        doInAsyncTask(this, "realDelete", clzs, params, "afterDelete");
    }

    public int realDelete(WebSiteModel nm){
        BaseLocalModelService localService = this.mNetModelService.getLocalService();
        return localService.delData(nm);
    }
    public void afterDelete(Integer state){
        WebSiteEvent webSiteEvent = new WebSiteEvent(null, WebSiteEvent.TYPE_DELETE);
        getBus().post(webSiteEvent);
    }


    //========add
    public void add(WebSiteModel nm){
        Log.d(TAG,"add:"+nm.getId());
        WebSiteModel[] params = new WebSiteModel[]{nm};
        Class[] clzs = new Class[]{WebSiteModel.class};
        doInAsyncTask(this, "realAdd", clzs, params, "afterAdd");
    }

    public int realAdd(WebSiteModel nm){
        BaseLocalModelService localService = this.mNetModelService.getLocalService();
        return localService.createData(nm);
    }
    public void afterAdd(Integer state){
        WebSiteEvent webSiteEvent = new WebSiteEvent(null, WebSiteEvent.TYPE_AFTERADD);
        getBus().post(webSiteEvent);
    }


    public List<BaseModel> createSystemDatas(){
        List<BaseModel> models = new ArrayList<BaseModel>();
        String[] names = {"百度","网易",
        "CSDN","codeKK",
        "博客园","Innost博客",
        "GITHUB","oschina-git",
        "StackOverFlow","JAVAEYE",
        "eoeandroid"};
        String[] urls = {"http://www.baidu.com/","http://www.163.com",
        "http://blog.csdn.net/","http://codekk.com/",
        "http://www.cnblogs.com/","http://blog.csdn.net/Innost/article/list/8",
        "https://github.com/","http://git.oschina.net/",
        "http://stackoverflow.com/","http://javaeye.com",
        "http://www.eoeandroid.com/"};
        int size = names.length;
        for(int i=0;i<size;i++){
            WebSiteModel netmodel = new WebSiteModel();
            netmodel.setId(String.valueOf(1000 + i));
            netmodel.setTitle(names[i]);
            netmodel.setUrl(urls[i]);
            netmodel.setIcon(UrlUtil.getFaviconIconUrl(urls[i]));
            models.add(netmodel);
        }
        return models;
    }
}
