package com.boboeye.luandun.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/9/5.
 */
public class BaseActivityStack {
    private List<BaseActivity> stack = new ArrayList<BaseActivity>();
    private static BaseActivityStack astack;
    private BaseActivityStack(){}

    public static BaseActivityStack getInst(){
        if(astack==null){
            astack = new BaseActivityStack();
        }
        return astack;
    }

    public int indexActivity(BaseActivity ac){
        int len = stack.size();
        for(int i=0;i<len;i++){
            BaseActivity curr = stack.get(i);
            if(curr==ac){
                return i;
            }
        }
        return -1;
    }

    public void addActivity(BaseActivity ac){
        int index = indexActivity(ac);
        if(index<0){
            stack.add(ac);
        }
    }
    public void finishActivity(BaseActivity activity){
        int index = indexActivity(activity);
        if(index>=0){
            stack.remove(index);
            activity.finish();
        }
    }

    public void finishLast(){
        int index = stack.size()-1;
        if(index>0){
            BaseActivity ac = stack.remove(index);
            ac.finish();
        }
    }

    public void removeActivity(BaseActivity activity) {
        int index = indexActivity(activity);
        if(index>=0){
            stack.remove(index);
        }
    }

    public void finishAll(){
        int len = stack.size();
        for(int i=0;i<len;i++){
            BaseActivity base = stack.get(i);
            base.finish();
        }
        stack.clear();
    }
    public void finishAllButFirst(){
        int len = stack.size();
        for(int i=0;i<len;i++){
            if(i==0){
                continue;
            }
            BaseActivity base = stack.get(i);
            base.finish();
        }
        BaseActivity first = stack.get(0);
        stack.clear();
        stack.add(first);
    }
}
