package com.boboeye.luandun.activitys;

import android.os.Bundle;

import com.boboeye.luandun.LuanApplication;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseBackActivity;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by libo_591 on 15/9/5.
 */
public class ProcessActivity extends BaseBackActivity {
    @Override
    public int getContentLayout() {
        return R.layout.activity_process;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        this.setTitle("进程管理");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = LuanApplication.getLeak(this);
        watcher.watch(this);
    }
}
