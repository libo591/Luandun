package com.boboeye.luandun.model.service.impl;

import com.boboeye.luandun.base.BaseLocalModelService;
import com.boboeye.luandun.base.BaseMemModelService;
import com.boboeye.luandun.base.BaseModelService;
import com.boboeye.luandun.model.service.LruMemModelService;

/**
 * Created by libo_591 on 15/8/7.
 */
public class WebSiteModelService extends BaseModelService {
    @Override
    public BaseLocalModelService getLocalService() {
        return new WebSiteLocalCacheModelService();
    }
}
