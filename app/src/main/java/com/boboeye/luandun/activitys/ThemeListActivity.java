package com.boboeye.luandun.activitys;

import android.os.Bundle;

import com.boboeye.luandun.LuanApplication;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseBackActivity;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by libo_591 on 15/9/27.
 */
public class ThemeListActivity extends BaseBackActivity {
    @Override
    public int getContentLayout() {
        return R.layout.activity_themelist;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        getSupportActionBar().setTitle("样式设置");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = LuanApplication.getLeak(this);
        watcher.watch(this);
    }
}
