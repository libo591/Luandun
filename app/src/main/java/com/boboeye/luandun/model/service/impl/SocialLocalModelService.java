package com.boboeye.luandun.model.service.impl;

import com.boboeye.luandun.model.impl.SocialMessage;
import com.boboeye.luandun.model.service.CacheLocalModelService;


/**
 * Created by libo_591 on 15/7/27.
 */
public class SocialLocalModelService extends CacheLocalModelService {

    @Override
    public String getCacheKey() {
        return super.getCacheKey();
    }

    @Override
    public Class getModelClass() {
        return SocialMessage.class;
    }
}
