package com.boboeye.luandun.event;

import com.boboeye.luandun.base.BaseEvent;

import java.util.List;

/**
 * Created by libo_591 on 15/9/27.
 */
public class ThemeEvent extends BaseEvent {
    public ThemeEvent(int type,List datas){
        this.setType(type);
        this.setEventData(datas);
    }
}
