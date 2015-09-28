package com.boboeye.luandun.controller;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.base.BaseLocalModelService;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.base.BaseNetWorkModelService;
import com.boboeye.luandun.event.TulingEvent;
import com.boboeye.luandun.model.impl.TulingModel;
import com.boboeye.luandun.model.service.impl.TulingModelService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.kymjs.kjframe.utils.DensityUtils;
import org.kymjs.kjframe.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by libo_591 on 15/9/19.
 */
public class Tuling123Controller extends BaseListController {
    private static final String TAG = Tuling123Controller.class.getSimpleName();
    private static Tuling123Controller _inst;
    private Tuling123Controller(){
        Context ctx = AppConfig.getInst().getContext();
        int[] dispSize = AppConfig.getInst().getDisplaySize(ctx);
        int count = dispSize[1]/DensityUtils.dip2px(ctx,48);
        count = count-1;
        setmPageIndex(1);
        setCountPerPage(count);
    }
    public static Tuling123Controller getInst(){if(_inst==null){_inst=new Tuling123Controller();}return _inst;}

    private String apiUrl = "http://apis.baidu.com/turing/turing/turing";
    private String[] headname = {"apikey"};
    private String[] headvalue = {"da828686fa9a16c3e1af2cc377ce8b10"};

    private String[] paramname = {"key","info","userid"};
    private String[] paramvalue = {"a0ddf90bf2695f43b0b5d0b1f9556327",null,"133855"};

    private TulingModelService service = new TulingModelService();

    @Override
    protected void requestSomePage(int index, int count) {
        Class[] clz = {int.class,int.class};
        Object[] objs = {index,count};
        doInAsyncTask(this, "doReqPage", clz, objs, "afterReqPage");
    }

    public List<BaseModel> doReqPage(int index,int count){
        BaseLocalModelService local = service.getLocalService();
        List<BaseModel> list = local.referDatas();
        List<BaseModel> result = new ArrayList<BaseModel>(index*count);
        int size = list.size();
        int start = size-1;
        int end = start-index*count+1;
        end = end<0?0:end;
        Log.d(TAG,start+"----"+end);
        if(start>0&&end>=0) {
            for (int i = end; i <=start; i++) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    public void afterReqPage(ArrayList models){
        getBus().post(new TulingEvent(TulingEvent.TYPE_BASELIST, models));
    }

    public void addMessage(String message,int from){
        Class[] clz = {String.class,int.class};
        Object[] objs = {message,from};
        doInAsyncTask(this, "doAddMsg", clz, objs, "afterAddMsg");
    }

    public TulingModel doAddMsg(String message,int from){
        TulingModel tm = new TulingModel();
        tm.setId(System.currentTimeMillis() + "");
        tm.setFrom(from);
        tm.setMsg(message);
        tm.setMsgDate(new Date());
        BaseLocalModelService local = service.getLocalService();
        local.createData(tm);
        return tm;
    }

    public void afterAddMsg(TulingModel tm){
        List<BaseModel> list = new ArrayList<BaseModel>(1);
        list.add(tm);
        getBus().post(new TulingEvent(TulingEvent.TYPE_QUERY, list));
    }

    public void queryAnswer(String question){
        BaseNetWorkModelService net = service.getNetService();
        paramvalue[1] = question;
        List<NameValuePair> params = new ArrayList<>(paramname.length);
        for(int i=0;i<paramname.length;i++){
            params.add(new BasicNameValuePair(paramname[i], paramvalue[i]));
        }
        SimpleArrayMap<String,String> map = new SimpleArrayMap<String,String>(headname.length);
        for(int i=0;i<headname.length;i++){
            map.put(headname[i],headvalue[i]);
        }
        net.referDatas(apiUrl, params, map);
    }

    public void destroySomeMessage(){
        Class[] clz = {};
        Object[] objs = {};
        doInAsyncTask(this,"doDestroySomeMessage",clz,objs,null);
    }
    public void doDestroySomeMessage(){
        BaseLocalModelService local = service.getLocalService();
        List<BaseModel> datas = local.referDatas();
        if(datas.size()>100){
            List<BaseModel> newlist = new ArrayList<BaseModel>(100);
            int size = datas.size();
            for(int i=size-1;i>=size-100;i++){
                newlist.add(datas.get(i));
            }
            local.updateDataList(newlist);
        }
    }
}
