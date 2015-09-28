package com.boboeye.luandun.model.service.impl;

import com.boboeye.luandun.model.impl.TulingModel;
import com.boboeye.luandun.model.service.CacheLocalModelService;

/**
 * Created by libo_591 on 15/9/22.
 */
public class TulingLocalService extends CacheLocalModelService {
    @Override
    public String getCacheKey() {
        return TulingLocalService.class.getSimpleName().toLowerCase();
    }

    @Override
    public Class getModelClass() {
        return TulingModel.class;
    }
}
