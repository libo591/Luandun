package com.boboeye.luandun.fragments;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseFragment;
import com.boboeye.luandun.controller.SettingsController;
import com.boboeye.luandun.event.SettingsEvent;

import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/9/7.
 */
public class SettingsFragment extends BaseFragment {
    @Override
    public int getContentLayout() {
        return R.layout.settings_list;
    }

    @Override
    public BaseController getController() {
        return SettingsController.getInst();
    }

    @Subscribe
    public void onEventMainThread(SettingsEvent event){

    }
}
