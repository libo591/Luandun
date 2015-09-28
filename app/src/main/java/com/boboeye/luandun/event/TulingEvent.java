package com.boboeye.luandun.event;

import com.boboeye.luandun.base.BaseEvent;
import com.boboeye.luandun.base.BaseModel;

import java.util.List;

/**
 * Created by libo_591 on 15/9/26.
 */
public class TulingEvent extends BaseEvent {
    public static final int TYPE_QUERY = 1001;

    public TulingEvent(int type,List datas){
        this.setType(type);
        this.setEventData(datas);
    }
}
