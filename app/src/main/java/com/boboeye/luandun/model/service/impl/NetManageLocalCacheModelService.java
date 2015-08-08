package com.boboeye.luandun.model.service.impl;

import android.util.Log;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.LuanApplication;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.controller.NetController;
import com.boboeye.luandun.event.NetEvent;
import com.boboeye.luandun.model.impl.NetModel;
import com.boboeye.luandun.model.service.CacheLocalModelService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/8/5.
 */
public class NetManageLocalCacheModelService extends CacheLocalModelService {

    private static final String TAG = "NetManageModelService";
    private NetController _controller = NetController.getInst();

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
        return NetModel.class;
    }

}
