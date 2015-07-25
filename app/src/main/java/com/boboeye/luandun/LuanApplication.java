package com.boboeye.luandun;

import com.boboeye.luandun.base.BaseApplication;

/**
 * Created by libo_591 on 15/7/25.
 */
public class LuanApplication extends BaseApplication{
    private static LuanApplication _inst = new LuanApplication();
    public static LuanApplication getInst(){
        return _inst;
    }
}
