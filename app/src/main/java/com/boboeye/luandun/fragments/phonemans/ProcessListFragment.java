package com.boboeye.luandun.fragments.phonemans;

import android.app.ActivityManager;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.adapter.ProcessAdapter;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;
import com.boboeye.luandun.controller.ProcessController;
import com.boboeye.luandun.event.ProcessEvent;
import com.boboeye.luandun.model.impl.ProcessModel;

import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/8/22.
 */
public class ProcessListFragment extends BaseListFragment {
    @Override
    public int getContentLayout() {
        return R.layout.phoneman_process;
    }

    @Override
    public int getListView() {
        return R.id.pm_processlist;
    }

    @Override
    public BaseListAdapter getAdapter() {
        return new ProcessAdapter(this.getActivity());
    }

    @Override
    public BaseController getController() {
        return ProcessController.getInst();
    }

    @Subscribe
    public void onEventMainThread(ProcessEvent event){
        super.onEventBasic(event);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ProcessModel pm = (ProcessModel) mAdapter.getItem(position);
        Context ctx = AppConfig.getInst().getContext();
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        try {
            am.killBackgroundProcesses(pm.getPackageName());
        }catch(Throwable e){
            e.printStackTrace();
        }
        this.onRefresh();
        return true;
    }
}
