package com.boboeye.luandun.event;

import com.boboeye.luandun.base.BaseEvent;
import com.boboeye.luandun.base.BaseModel;

import java.util.List;

/**
 * Created by libo_591 on 15/9/17.
 */
public class SpaceCleanerEvent extends BaseEvent {
    public SpaceCleanerEvent(int type, List<BaseModel> models){
        this.setType(type);
        this.setEventData(models);
    }
}
