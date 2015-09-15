package com.boboeye.luandun.event;

import com.boboeye.luandun.base.BaseEvent;
import com.boboeye.luandun.base.BaseModel;

import java.util.List;

/**
 * Created by libo_591 on 15/9/7.
 */
public class SettingsEvent extends BaseEvent {
    public SettingsEvent(int type, List<BaseModel> datas){
        this.setType(type);
        this.setEventData(datas);
    }
}
