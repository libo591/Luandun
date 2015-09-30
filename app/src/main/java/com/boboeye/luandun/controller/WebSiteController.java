package com.boboeye.luandun.controller;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.base.BaseLocalModelService;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.event.WebSiteEvent;
import com.boboeye.luandun.model.impl.WebSiteModel;
import com.boboeye.luandun.model.service.ImageVolleyModelService;
import com.boboeye.luandun.model.service.impl.WebSiteModelService;
import com.boboeye.luandun.utils.UrlUtil;

import java.util.ArrayList;
import java.util.LinkedList;
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
    private RequestQueue q = Volley.newRequestQueue(AppConfig.getInst().getContext());

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
            localService.createDataList(netModels);
        }
        return netModels;
    }
    public void afterRequestNetPage(ArrayList datas){
        Log.d(TAG, "获取的数据大小:>" + datas.size());
        WebSiteEvent webSiteEvent = new WebSiteEvent(datas, WebSiteEvent.TYPE_BASELIST);
        getBus().post(webSiteEvent);
    }

    //============edit
    public void edit(WebSiteModel nm,int pos){
        Log.d(TAG, "edit:" + nm.getId());
        Object[] params = new Object[]{nm,pos};
        Class[] clzs = new Class[]{WebSiteModel.class,int.class};
        doInAsyncTask(this, "realdoEdit", clzs, params, "afterEdit");
    }

    public List realdoEdit(WebSiteModel nm,int pos){
        BaseLocalModelService localService = this.mNetModelService.getLocalService();
        int result = localService.updateData(nm);
        if(result==1){
            List datas = new ArrayList(2);
            datas.add(pos);
            datas.add(nm);
            return datas;
        }
        return null;
    }
    public void afterEdit(ArrayList datas){
        WebSiteEvent webSiteEvent = new WebSiteEvent(datas, WebSiteEvent.TYPE_EDIT);
        getBus().post(webSiteEvent);
    }

    //============delete
    public void delete(WebSiteModel nm,int pos){
        Log.d(TAG,"delete:"+nm.getId());
        Object[] params = new Object[]{nm,pos};
        Class[] clzs = new Class[]{WebSiteModel.class,int.class};
        doInAsyncTask(this, "realDelete", clzs, params, "afterDelete");
    }

    public int realDelete(WebSiteModel nm,int pos){
        BaseLocalModelService localService = this.mNetModelService.getLocalService();
        int result = localService.delData(nm);
        if(result==1){
            return pos;
        }
        return -1;
    }
    public void afterDelete(Integer pos){
        List list = new LinkedList();
        list.add(pos);
        WebSiteEvent webSiteEvent = new WebSiteEvent(list, WebSiteEvent.TYPE_DELETE);
        getBus().post(webSiteEvent);
    }


    //========add
    public void add(WebSiteModel nm){
        Log.d(TAG, "add:" + nm.getId());
        WebSiteModel[] params = new WebSiteModel[]{nm};
        Class[] clzs = new Class[]{WebSiteModel.class};
        doInAsyncTask(this, "realAdd", clzs, params, "afterAdd");
    }

    public BaseModel realAdd(WebSiteModel nm){
        BaseLocalModelService localService = this.mNetModelService.getLocalService();
        int result = localService.createData(nm);
        if(result==1){
            return nm;
        }else{
            return null;
        }
    }
    public void afterAdd(WebSiteModel model){
        List datas = new LinkedList();
        datas.add(model);
        WebSiteEvent webSiteEvent = new WebSiteEvent(datas, WebSiteEvent.TYPE_AFTERADD);
        getBus().post(webSiteEvent);
    }


    public List<BaseModel> createSystemDatas(){
        List<BaseModel> models = new ArrayList<BaseModel>();
        String[] names = {"百度","百度网址大全","腾讯","QQ空间",
                "凤凰网","搜狐","网易","163邮箱",
                "新浪体育","优酷","爱奇艺"
        };
        String[] urls = {"http://www.baidu.com/","http://site.baidu.com","http://www.qq.com","http://qzone.qq.com/",
                "http://www.ifeng.com/","http://www.sohu.com/","http://www.163.com","http://mail.163.com",
                "http://sports.sina.com.cn/","http://www.youku.com","http://iqiyi.com"
        };
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

    public void reqImage(ImageView view,String url,ImageView.ScaleType scaleType,int maxWidth,int maxHeight){
        ImageVolleyModelService service = new ImageVolleyModelService(this.q);
        service.setImageParams(view,scaleType,maxWidth,maxHeight);
        service.referData(url,null);
    }

    public RequestQueue getQ() {
        return q;
    }

    public void setQ(RequestQueue q) {
        this.q = q;
    }
}
