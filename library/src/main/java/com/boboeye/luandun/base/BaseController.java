package com.boboeye.luandun.base;


import de.greenrobot.event.EventBus;

/**
 * 所有controller的父类，子类必须重写getController，返回单例
 */
public class BaseController {
    public void regist(Object object){
        if(!EventBus.getDefault().isRegistered(object)){
            EventBus.getDefault().register(object);
        }
    }
    public void unregist(Object object){
        if(EventBus.getDefault().isRegistered(object)){
            EventBus.getDefault().unregister(object);
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
