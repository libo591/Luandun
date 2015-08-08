package com.boboeye.luandun.event;

import com.boboeye.luandun.base.BaseEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/8/5.
 */
public class NetEvent extends BaseEvent {
    public NetEvent(int state,Object datas,int eventType){
        List eventDatas = new ArrayList();
        eventDatas.add(state);
        eventDatas.add(datas);
        this.setEventData(eventDatas);
        this.setType(eventType);
    }
}
