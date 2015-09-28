package com.boboeye.luandun.model.impl;

import com.boboeye.luandun.base.BaseModel;

/**
 * Created by libo_591 on 15/9/5.
 */
public class AccountModel extends BaseModel {
    public static final int TYPE_MAIL_GMAIL     = 1;
    public static final int TYPE_MAIL_NETSASE   = 2;
    public static final int TYPE_MAIL_TENCENT   = 3;
    public static final int TYPE_MAIL_SINA      = 4;
    public static final int TYPE_MAIL_OTHER     = 5;


    public static final int TYPE_BANK_CBC       = 6;
    public static final int TYPE_BANK_ABC       = 7;
    public static final int TYPE_BANK_ICBC      = 8;
    public static final int TYPE_BANK_PBC       = 9;
    public static final int TYPE_BANK_CEB       = 10;
    public static final int TYPE_BANK_CMB       = 11;
    public static final int TYPE_BANK_CTB       = 12;
    public static final int TYPE_BANK_CMBC      = 13;
    public static final int TYPE_BANK_OTHER     = 14;

    public static final int TYPE_NET_WEIBO      = 15;
    public static final int TYPE_NET_WEICHAT    = 16;
    public static final int TYPE_NET_MOMO       = 17;
    public static final int TYPE_NET_ZHIHU      = 18;
    public static final int TYPE_NET_OTHER      = 19;

    public static final int TYPE_OTHER          = 0;

    private String name;
    private String password;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
