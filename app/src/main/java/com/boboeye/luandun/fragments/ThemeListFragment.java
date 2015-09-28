package com.boboeye.luandun.fragments;

import android.view.View;
import android.widget.AdapterView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.LuanApplication;
import com.boboeye.luandun.R;
import com.boboeye.luandun.adapter.ThemeAdapter;
import com.boboeye.luandun.base.BaseActivityStack;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;
import com.boboeye.luandun.controller.ThemeController;
import com.boboeye.luandun.event.ThemeEvent;
import com.boboeye.luandun.model.impl.ThemeModel;
import com.squareup.leakcanary.RefWatcher;

import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/9/27.
 */
public class ThemeListFragment extends BaseListFragment {
    private boolean themeChanged = false;
    @Override
    public BaseController getController() {
        return ThemeController.getInst();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_themelist;
    }

    @Override
    public int getListView() {
        return R.id.themelistview;
    }

    @Override
    public BaseListAdapter getAdapter() {
        return new ThemeAdapter(this.getActivity());
    }

    @Subscribe
    public void onEventMainThread(ThemeEvent event){
        super.onEventBasic(event);
    }

    @Override
    public void onDestroy() {
        if(themeChanged) {
            BaseActivityStack.getInst().getFirst().recreate();
        }
        super.onDestroy();
        RefWatcher watcher = LuanApplication.getLeak(this.getActivity());
        watcher.watch(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        themeChanged = true;
        ThemeModel tm = (ThemeModel)mAdapter.getItem(position);
        AppConfig.getInst().setPrefer("apptheme", tm.getThemeRes() + "");
        this.getActivity().recreate();
    }
}
