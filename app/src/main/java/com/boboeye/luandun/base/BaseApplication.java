package com.boboeye.luandun.base;

import android.app.Application;

import de.greenrobot.event.EventBus;

/**
 * Created by libo_591 on 15/7/25.
 */
public class BaseApplication extends Application {
    public EventBus getBus() {
        return mBus;
    }

    public void setBus(EventBus mBus) {
        this.mBus = mBus;
    }

    private EventBus mBus;


}
