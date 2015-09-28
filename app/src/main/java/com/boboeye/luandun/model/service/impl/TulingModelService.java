package com.boboeye.luandun.model.service.impl;

import com.boboeye.luandun.base.BaseLocalModelService;
import com.boboeye.luandun.base.BaseModelService;
import com.boboeye.luandun.base.BaseNetWorkModelService;

/**
 * Created by libo_591 on 15/9/22.
 */
public class TulingModelService extends BaseModelService {
    private TulingLocalService local = new TulingLocalService();
    private TulingNetService net = new TulingNetService();
    public BaseLocalModelService getLocalService(){
        return local;
    }
    public BaseNetWorkModelService getNetService(){return net;}
}
