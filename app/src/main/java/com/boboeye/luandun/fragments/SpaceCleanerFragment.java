package com.boboeye.luandun.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.boboeye.luandun.LuanApplication;
import com.boboeye.luandun.R;
import com.boboeye.luandun.adapter.SpaceCleanerAdapter;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;
import com.boboeye.luandun.controller.SpaceCleanerController;
import com.boboeye.luandun.event.SpaceCleanerEvent;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/9/17.
 */
public class SpaceCleanerFragment extends BaseListFragment implements SwipeRefreshLayout.OnRefreshListener{
    @InjectView(R.id.spaceclean_refreshlayout)
    public SwipeRefreshLayout spaceclean_refreshlayout;
    @Override
    public int getContentLayout() {
        return R.layout.fragment_cleancache;
    }

    @Override
    public int getListView() {
        return R.id.autostart_listview;
    }

    @Override
    public BaseController getController() {
        return SpaceCleanerController.getInst();
    }

    @Override
    public BaseListAdapter getAdapter() {
        return new SpaceCleanerAdapter(getActivity());
    }

    @Subscribe
    public void onEventMainThread(SpaceCleanerEvent event){
        if(event.getType()== SpaceCleanerEvent.TYPE_DELETE){
            onRefresh();
        }else{
            super.onEventBasic(event);
            spaceclean_refreshlayout.setRefreshing(false);
        }
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        ButterKnife.inject(this, view);
        spaceclean_refreshlayout.setColorSchemeResources(R.color.day_colorPrimary);
        spaceclean_refreshlayout.setOnRefreshListener(this);
    }

    @OnClick(R.id.cleaner_button)
    public void cleanButtonClick(){
        SpaceCleanerController.getInst().cleanSpace();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = LuanApplication.getLeak(this.getActivity());
        watcher.watch(this);
    }
}
