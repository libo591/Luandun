package com.boboeye.luandun.event;

import com.boboeye.luandun.base.BaseEvent;
import com.boboeye.luandun.model.impl.PhoneManageModel;

import java.util.List;

/**
 * Created by libo_591 on 15/8/19.
 */
public class PhoneManageEvent extends BaseEvent {
    public PhoneManageEvent(int type, List<PhoneManageModel> datas){
        this.setType(type);
        this.setEventData(datas);
    }
}
