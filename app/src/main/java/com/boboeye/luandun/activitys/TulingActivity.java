package com.boboeye.luandun.activitys;

import android.os.Bundle;
import android.view.WindowManager;

import com.boboeye.luandun.LuanApplication;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseBackActivity;
import com.boboeye.luandun.controller.Tuling123Controller;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by libo_591 on 15/9/26.
 */
public class TulingActivity extends BaseBackActivity {
    @Override
    public int getContentLayout() {
        return R.layout.activity_tuling;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        getSupportActionBar().setTitle("图灵机器人");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = LuanApplication.getLeak(this);
        watcher.watch(this);
    }
}
