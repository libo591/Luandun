package com.boboeye.luandun.event;

import com.boboeye.luandun.base.BaseEvent;

import java.util.ArrayList;

/**
 * Created by libo_591 on 15/8/5.
 */
public class WebSiteEvent extends BaseEvent {
    public static final int SHOW_EDIT = 1000;
    public WebSiteEvent(Object datas, int eventType){
        this.setEventData((ArrayList)datas);
        this.setType(eventType);
    }
}
