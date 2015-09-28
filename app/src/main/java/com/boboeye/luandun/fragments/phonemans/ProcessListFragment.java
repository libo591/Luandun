package com.boboeye.luandun.fragments.phonemans;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.LuanApplication;
import com.boboeye.luandun.R;
import com.boboeye.luandun.adapter.ProcessAdapter;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;
import com.boboeye.luandun.controller.ProcessController;
import com.boboeye.luandun.event.ProcessEvent;
import com.boboeye.luandun.model.impl.ProcessModel;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/8/22.
 */
public class ProcessListFragment extends BaseListFragment implements SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.process_refreshlayout)
    public SwipeRefreshLayout process_refresh;
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

    @Override
    public void initViews(View view) {
        super.initViews(view);
        ButterKnife.inject(this, view);
        process_refresh.setColorSchemeResources(R.color.day_colorPrimary);
        process_refresh.setOnRefreshListener(this);
    }

    @Subscribe
    public void onEventMainThread(ProcessEvent event){
        if(event.getType()==ProcessEvent.TYPE_DELETE){
            onRefresh();
        }else{
            super.onEventBasic(event);
            process_refresh.setRefreshing(false);
        }
    }


    @Override
    public void onDestroy() {
        ButterKnife.reset(this);
        super.onDestroy();
        RefWatcher watcher = LuanApplication.getLeak(this.getActivity());
        watcher.watch(this);
    }
}
