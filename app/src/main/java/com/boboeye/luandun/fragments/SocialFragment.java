package com.boboeye.luandun.fragments;

import android.view.View;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.adapter.SocialAdapter;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;

/**
 * Created by libo_591 on 15/7/26.
 */
public class SocialFragment extends BaseListFragment {
    @Override
    public void initViews(View view) {
        super.initViews(view);
    }

    @Override
    public void initDatas() {
        super.initDatas();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_social;
    }

    @Override
    public int getListView() {
        return R.id.social_listview;
    }

    @Override
    public BaseListAdapter getAdapter() {
        return new SocialAdapter(AppConfig.getInst().getContext());
    }
}
