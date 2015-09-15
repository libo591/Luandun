package com.boboeye.luandun.controller;

import android.support.v4.view.ViewPager;

import com.boboeye.luandun.base.BaseEvent;
import com.boboeye.luandun.event.WebSiteEvent;
import com.boboeye.luandun.event.AccountEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by libo_591 on 15/9/8.
 */
public class HomeViewPagerController {
    private static HomeViewPagerController mController;
    private HomeViewPagerController(){}
    public static HomeViewPagerController getInst(){
        if(mController==null){
            mController = new HomeViewPagerController();
        }
        return mController;
    }

    private ViewPager mViewPager;

    public void registViewPager(ViewPager viewPager){
        this.mViewPager = viewPager;
    }

    public int getViewPagerShowItem(){
        if(this.mViewPager!=null){
            return this.mViewPager.getCurrentItem();
        }
        return -1;
    }

    public void dispatchAddEvent(){
        BaseEvent event = null;
        int showitem = HomeViewPagerController.getInst().getViewPagerShowItem();
        if(showitem==0) {
            event = new WebSiteEvent(null, WebSiteEvent.TYPE_ADD);
        }else if(showitem==2){
            event = new AccountEvent(AccountEvent.TYPE_ADD,null);
        }
        if(event!=null) {
            EventBus.getDefault().post(event);
        }
    }
}
