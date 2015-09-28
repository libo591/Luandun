package com.boboeye.luandun.activitys;

import android.os.Bundle;
import android.view.WindowManager;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseBackActivity;

/**
 * Created by libo_591 on 15/9/7.
 */
public class SettingsActivity extends BaseBackActivity {
    @Override
    public int getContentLayout() {
        return R.layout.settings_activity;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        getSupportActionBar().setTitle("设置");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
