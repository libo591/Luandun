package com.boboeye.luandun.controller;

import com.boboeye.luandun.base.BaseController;

/**
 * Created by libo_591 on 15/9/7.
 */
public class SettingsController extends BaseController {
    static class PreferenceControllerHolder{
        private static SettingsController mController = new SettingsController();
    }
    private SettingsController(){}
    public static SettingsController getInst(){
        return PreferenceControllerHolder.mController;
    }
}
