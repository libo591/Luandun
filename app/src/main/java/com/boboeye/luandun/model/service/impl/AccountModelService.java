package com.boboeye.luandun.model.service.impl;

import com.boboeye.luandun.base.BaseLocalModelService;
import com.boboeye.luandun.base.BaseModelService;

/**
 * Created by libo_591 on 15/9/5.
 */
public class AccountModelService extends BaseModelService {
    @Override
    public BaseLocalModelService getLocalService() {
        return new AccountLocalService();
    }
}
