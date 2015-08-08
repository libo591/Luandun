package com.boboeye.luandun.base;

import java.util.List;

/**
 * Created by libo_591 on 15/7/25.
 */
public class BaseEvent {
    public static final int TYPE_BASELIST=0;
    public static final int TYPE_EDIT    =1;
    public static final int TYPE_DELETE  =2;
    public static final int TYPE_ADD     =3;

    private int type=-1;
    private List eventData;

    public List getEventData() {
        return eventData;
    }

    public void setEventData(List eventData) {
        this.eventData = eventData;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
