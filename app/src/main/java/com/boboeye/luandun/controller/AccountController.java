package com.boboeye.luandun.controller;

import android.util.Log;

import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.base.BaseLocalModelService;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.event.AccountEvent;
import com.boboeye.luandun.model.impl.AccountModel;
import com.boboeye.luandun.model.service.impl.AccountModelService;
import com.boboeye.luandun.utils.CipherUtils;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/9/5.
 */
public class AccountController extends BaseListController {
    private static final String TAG = "AccountController";
    private static AccountController mAccountController;
    private String mKey;

    private AccountController(){}
    public static AccountController getInst(){
        if(mAccountController ==null){
            mAccountController = new AccountController();
        }
        return mAccountController;
    }

    private AccountModelService pms = new AccountModelService();

    @Override
    protected void requestSomePage(int index,int count){
        Log.d(TAG,"requestSomePage");
        Class[] clz = {int.class,int.class};
        Object[] obj = {index,count};
        doInAsyncTask(this,"doreqPage",clz,obj,"afterPage");
    }

    public List doreqPage(int index,int count){
        BaseLocalModelService localss = pms.getLocalService();
        List<BaseModel> result = localss.referDatas();
        if(result==null||result.size()<=0){
            //result = createMockDatas();
        }
        return result;
    }

    public void afterPage(ArrayList datas){
        Log.d(TAG, "获取数据成功，返回数据！");
        AccountEvent pe = new AccountEvent(AccountEvent.TYPE_BASELIST,datas);
        getBus().post(pe);
    }

    public String getCipherKey(){
        return mKey;
    }
    public void setCipherKey(String key){
        mKey = key;
    }

    public Key getKey(){
        if(mKey==null){
            return null;
        }
        Key key = null;
        try {
            key = CipherUtils.getDESKey(mKey.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            key = null;
        }
        return key;
    }

    public void delete(AccountModel pm,int pos){
        Class[] clz = {AccountModel.class,int.class};
        Object[] obj = {pm,pos};
        doInAsyncTask(this,"doDelete",clz,obj,"afterDelete");
    }
    public int doDelete(AccountModel pm,int pos){
        BaseLocalModelService ls = pms.getLocalService();
        int result = ls.delData(pm);
        if(result==1){
            return pos;
        }
        return -1;
    }
    public void afterDelete(Integer pos){
        List datas = new ArrayList(1);
        datas.add(pos);
        getBus().post(new AccountEvent(AccountEvent.TYPE_DELETE, datas));
    }

    public void edit(AccountModel pm,int pos){
        Class[] clz = {AccountModel.class,int.class};
        Object[] obj = {pm,pos};
        doInAsyncTask(this, "doEdit", clz, obj, "afterEdit");
    }

    public List doEdit(AccountModel pm,int pos){
        BaseLocalModelService ls = pms.getLocalService();
        int result = ls.updateData(pm);
        if(result==1){
            List datas = new ArrayList(2);
            datas.add(pm);
            datas.add(pos);
            return datas;
        }
        return null;
    }
    public void afterEdit(ArrayList datas){
        getBus().post(new AccountEvent(AccountEvent.TYPE_EDIT, datas));
    }

    public void add(AccountModel pm){
        Class[] clz = {AccountModel.class};
        Object[] obj = {pm};
        doInAsyncTask(this, "doAdd", clz, obj, "afterAdd");
    }

    public AccountModel doAdd(AccountModel pm){
        BaseLocalModelService ls = pms.getLocalService();
        int result = ls.createData(pm);
        if(result==1){
            return pm;
        }
        return null;
    }
    public void afterAdd(AccountModel pm){
        List datas = new ArrayList(1);
        datas.add(pm);
        getBus().post(new AccountEvent(AccountEvent.TYPE_AFTERADD, datas));
    }

    private List createMockDatas(){
        List result = new ArrayList();
        for(int i=0;i<100;i++){
            AccountModel am = new AccountModel();
            am.setId(i+"");
            am.setName("name--" + i);
            am.setType("type--" + i);
            am.setPassword("password--"+i);
            result.add(am);
        }
        return result;
    }
}
