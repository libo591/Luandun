package com.boboeye.luandun.controller;

import android.util.Log;

import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.base.BaseLocalModelService;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.event.AccountEvent;
import com.boboeye.luandun.model.impl.AccountModel;
import com.boboeye.luandun.model.service.impl.AccountModelService;

import org.kymjs.kjframe.utils.CipherUtils;

import java.security.Key;
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
        BaseLocalModelService localss = pms.getLocalService();
        List<BaseModel> result = localss.referDatas();
        Log.d(TAG,"获取数据成功，返回数据！");
        AccountEvent pe = new AccountEvent(AccountEvent.TYPE_BASELIST,result);
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
        }
        return key;
    }

    public void delete(AccountModel pm){
        BaseLocalModelService ls = pms.getLocalService();
        ls.delData(pm);

        getBus().post(new AccountEvent(AccountEvent.TYPE_DELETE,null));
    }

    public void edit(AccountModel pm){
        BaseLocalModelService ls = pms.getLocalService();
        ls.updateData(pm);

        getBus().post(new AccountEvent(AccountEvent.TYPE_EDIT, null));
    }

    public void add(AccountModel pm){
        BaseLocalModelService ls = pms.getLocalService();
        ls.createData(pm);
    }
}
