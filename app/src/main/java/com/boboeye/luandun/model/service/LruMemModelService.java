package com.boboeye.luandun.model.service;

import android.support.v4.util.LruCache;

import com.boboeye.luandun.base.BaseMemModelService;
import com.boboeye.luandun.base.BaseModel;

import java.util.List;

/**
 * Created by libo_591 on 15/9/30.
 */
public class LruMemModelService extends BaseMemModelService {

    protected LruCache<String, List> lrucache = new LruCache<String, List>(getMemSize());

    @Override
    public List<BaseModel> referDatas() {
        return lrucache.get(getCacheKey());
    }

    @Override
    public void removeDatas() {
        lrucache.remove(getCacheKey());
    }

    @Override
    public void createDatas(List<BaseModel> datas) {
        lrucache.put(getCacheKey(),datas);
    }
}
