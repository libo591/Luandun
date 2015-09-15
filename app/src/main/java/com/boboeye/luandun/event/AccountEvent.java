package com.boboeye.luandun.event;

import com.boboeye.luandun.base.BaseEvent;

import java.util.List;

/**
 * Created by libo_591 on 15/9/5.
 */
public class AccountEvent extends BaseEvent {
    public static final int SHOW_EDIT = 1000;
    public AccountEvent(int type, List datas){
        this.setEventData(datas);
        this.setType(type);
    }
}
