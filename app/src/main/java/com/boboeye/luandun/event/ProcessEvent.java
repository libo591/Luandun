package com.boboeye.luandun.event;

import com.boboeye.luandun.base.BaseEvent;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.model.impl.ProcessModel;

import java.util.List;

/**
 * Created by libo_591 on 15/8/22.
 */
public class ProcessEvent extends BaseEvent {
    public ProcessEvent(int type,List<BaseModel> datas){
        this.setType(type);
        this.setEventData(datas);
    }
}
