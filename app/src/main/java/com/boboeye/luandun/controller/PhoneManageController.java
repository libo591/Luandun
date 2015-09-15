package com.boboeye.luandun.controller;

import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.event.PhoneManageEvent;
import com.boboeye.luandun.model.impl.PhoneManageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/8/19.
 */
public class PhoneManageController extends BaseListController {
    public static final String MAN_PROCESS= "0";
    private static PhoneManageController _inst = new PhoneManageController();
    private PhoneManageController(){}
    public static PhoneManageController getInst(){
        return _inst;
    }
    @Override
    protected void requestSomePage(int index, int count) {
        String[] strs = {"进程监控"};
        List<PhoneManageModel> models = new ArrayList<PhoneManageModel>();
        int len = strs.length;
        for(int i=0;i<len;i++){
            PhoneManageModel pmm = new PhoneManageModel();
            pmm.setId(i+"");
            pmm.setName(strs[i]);
            models.add(pmm);
        }
        PhoneManageEvent pe = new PhoneManageEvent(PhoneManageEvent.TYPE_BASELIST,models);
        getBus().post(pe);
    }
}
