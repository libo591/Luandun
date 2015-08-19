package com.boboeye.luandun.base;

import android.app.Fragment;
import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 所有controller的父类，子类必须重写getController，返回单例
 */
public class BaseController {
    public void registFragment(BaseFragment fragment){
        if(!EventBus.getDefault().isRegistered(fragment)){
            EventBus.getDefault().register(fragment);
        }
    }
    public void unregistFragment(BaseFragment fragment){
        if(EventBus.getDefault().isRegistered(fragment)){
            EventBus.getDefault().unregister(fragment);
        }
    }

    public EventBus getBus(){return EventBus.getDefault();}
    private BaseControllerAsynTaskProxy taskProxy = new BaseControllerAsynTaskProxy();


    public void cancelAll(){
        taskProxy.cancelAll();
    }
    private void cancelTask(int taskid){
        taskProxy.cancelTask(taskid);
    }

    public void doInAsyncTask(BaseController controller,String methodNameInvoke,
                              Class[] invokeParamTypes,
                              Object[] invokeMethodParams,
                              String methodNameCallBack){
        taskProxy.doInAsyncTask(controller,methodNameInvoke,invokeParamTypes,invokeMethodParams,methodNameCallBack);
    }

    public void requestNextPage(){
    }
    public void refresh(){
        requestPage();
    }
    public void requestPage(){
    }

}
