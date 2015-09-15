package com.boboeye.luandun.model.service.impl;

import com.boboeye.luandun.model.impl.AccountModel;
import com.boboeye.luandun.model.service.CacheLocalModelService;

/**
 * Created by libo_591 on 15/9/5.
 */
public class AccountLocalService extends CacheLocalModelService {
    @Override
    public String getCacheKey() {
        return "passlocalservice";
    }

    @Override
    public Class getModelClass() {
        return AccountModel.class;
    }
}
