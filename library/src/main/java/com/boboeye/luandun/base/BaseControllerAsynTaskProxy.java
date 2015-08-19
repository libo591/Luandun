package com.boboeye.luandun.base;

import android.os.AsyncTask;
import android.util.SparseArray;

import java.lang.reflect.Method;

/**
 * Created by libo_591 on 15/8/17.
 */
public class BaseControllerAsynTaskProxy {
    private SparseArray<BaseControllerTask> mControllerTasks = new SparseArray<BaseControllerTask>();
    public void cancelAll(){
        if(mControllerTasks!=null){
            int size = mControllerTasks.size();
            for(int i=0;i<size;i++){
                this.cancelTask(mControllerTasks.keyAt(i));
            }
        }
    }
    public void cancelTask(int taskid){
        BaseControllerTask task = mControllerTasks.get(taskid);
        if(task!=null){
            task.cancel(true);
            mControllerTasks.remove(taskid);
            task = null;
        }
    }

    public void doInAsyncTask(BaseController controller,String methodNameInvoke,
                              Class[] invokeParamTypes,
                              Object[] invokeMethodParams,
                              String methodNameCallBack){
        int taskid = mControllerTasks.size();
        BaseControllerTask mNetTask = new BaseControllerTask(taskid,controller,
                methodNameInvoke,invokeParamTypes,invokeMethodParams
                ,methodNameCallBack);
        mNetTask.execute(invokeMethodParams);
        mControllerTasks.put(taskid,mNetTask);
    }

    class BaseControllerTask extends AsyncTask {
        private int mTaskid;
        private BaseController mController;
        private String methodNameInvoke;
        private Object[] methodParams;
        private String methodNameCallBack;
        private Class[] invokeParamTypes;

        public BaseControllerTask(int taskid,BaseController controller,
                                  String methodNameInvoke,Class[] invokeParamTypes,Object[] methodParams,
                                  String methodNameCallBack){
            this.mTaskid = taskid;
            this.mController = controller;
            this.methodNameInvoke = methodNameInvoke;
            this.methodParams = methodParams;
            this.methodNameCallBack = methodNameCallBack;
            this.invokeParamTypes = invokeParamTypes;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Object result = null;
            try {
                Method invokedmethod = mController.getClass().getDeclaredMethod(methodNameInvoke, invokeParamTypes);
                result = invokedmethod.invoke(mController,methodParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Object o) {
            try {
                if(o==null){
                    Method method = mController.getClass().getDeclaredMethod(methodNameCallBack);
                    method.invoke(mController);
                }else{
                    Method method = mController.getClass().getDeclaredMethod(methodNameCallBack, o.getClass());
                    method.invoke(mController,o);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            cancelTask(mTaskid);
        }
    }
}
