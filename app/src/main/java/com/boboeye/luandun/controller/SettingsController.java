package com.boboeye.luandun.controller;

import android.content.Context;
import android.text.Html;
import android.util.Log;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListController;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.event.SettingsEvent;
import com.boboeye.luandun.model.impl.SettingsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/9/7.
 */
public class SettingsController extends BaseListController {
    private static final String TAG = SettingsController.class.getSimpleName();

    static class PreferenceControllerHolder{
        private static SettingsController mController = new SettingsController();
    }
    private SettingsController(){}
    public static SettingsController getInst(){
        return PreferenceControllerHolder.mController;
    }

    @Override
    protected void requestSomePage(int index, int count) {
        List<BaseModel> models = new ArrayList<BaseModel>();
        int[] titles = {R.string.mygithubtitle,R.string.myweibotitle,R.string.websitetitle,R.string.phonemantitle,R.string.passtitle};
        int[] contents = {R.string.mygithubdesc,R.string.myweibodesc,R.string.websitedesc,R.string.phonemandesc,R.string.passdesc};
        String[] urls = {"https://github.com/libo591/Luandun","http://weibo.com/1739663415/info",null,null,null};
        int size = titles.length;
        Context ctx = AppConfig.getInst().getContext();
        for(int i=0;i<size;i++){
            SettingsModel sm = new SettingsModel();
            sm.setTitle(ctx.getText(titles[i]).toString());
            sm.setContent(ctx.getText(contents[i]).toString());
            sm.setLinkurl(urls[i]);
            models.add(sm);
        }
        getBus().post(new SettingsEvent(SettingsEvent.TYPE_BASELIST,models));
    }
}
