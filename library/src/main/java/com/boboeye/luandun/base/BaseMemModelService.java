package com.boboeye.luandun.base;

import java.util.List;

/**
 * Created by libo_591 on 15/9/30.
 */
public class BaseMemModelService {
    public String getCacheKey(){
        return this.getClass().getSimpleName();
    }

    public int getMemSize(){
        return (int)Runtime.getRuntime().totalMemory()/32;
    }

    public List<BaseModel> referDatas(){
        return null;
    }

    public void removeDatas(){

    }
    public void createDatas(List<BaseModel> datas){

    }
}
