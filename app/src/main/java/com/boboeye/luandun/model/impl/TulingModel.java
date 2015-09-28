package com.boboeye.luandun.model.impl;

import com.boboeye.luandun.base.BaseModel;

import java.util.Date;

/**
 * Created by libo_591 on 15/9/22.
 */
public class TulingModel extends BaseModel {
    public static final int FROM_TULING = 1;
    public static final int FROM_USER = 2;
    private int from;
    private String msg;
    private Date msgDate;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }
}
