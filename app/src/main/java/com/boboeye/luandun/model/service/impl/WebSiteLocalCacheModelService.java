package com.boboeye.luandun.model.service.impl;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.controller.WebSiteController;
import com.boboeye.luandun.model.impl.WebSiteModel;
import com.boboeye.luandun.model.service.CacheLocalModelService;

/**
 * Created by libo_591 on 15/8/5.
 */
public class WebSiteLocalCacheModelService extends CacheLocalModelService {

    private static final String TAG = "NetManageModelService";
    private WebSiteController _controller = WebSiteController.getInst();

    /**
     * 缓存的key值，一般比如列表用缓存保存最后一次请求页面数据，会存到一个缓存文件中，此key用于得到此缓存文件
     * @return
     */
    public String getCacheKey(){
        String pkName = AppConfig.getInst().getContext().getPackageName();
        return pkName.hashCode()+"_netmanager";
    }

    /**
     * 获取Model实体类的类对象，用于缓存文件到对象的解析映射
     * @return
     */
    public Class getModelClass(){
        return WebSiteModel.class;
    }

}
