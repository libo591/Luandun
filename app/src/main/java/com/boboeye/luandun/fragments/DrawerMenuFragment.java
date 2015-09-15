package com.boboeye.luandun.fragments;

import android.content.Intent;
import android.view.View;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.activitys.SettingsActivity;
import com.boboeye.luandun.activitys.WebViewActivity;
import com.boboeye.luandun.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class DrawerMenuFragment extends BaseFragment {
    private static final String TAG = "DrawerMenuFragment";

    @Override
    public int getContentLayout() {
        return R.layout.fragment_drawer_menu;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        ButterKnife.inject(this, view);

    }
    @OnClick(R.id.menu_dis)
    public void disClick(){
        Intent intent = new Intent(this.getActivity(), WebViewActivity.class);
        intent.putExtra("url", "http://girl-atlas.com");
        getActivity().startActivity(intent);
    }
    @OnClick(R.id.menu_set)
    public void setClick(){
        Intent intent = new Intent(this.getActivity(), SettingsActivity.class);
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.menu_theme)
    public void setTheme(){
        String theme = AppConfig.getInst().getPrefer("apptheme","day");
        if("day".equals(theme)){
            AppConfig.getInst().setPrefer("apptheme","night");
        }else{
            AppConfig.getInst().setPrefer("apptheme","day");
        }
        getActivity().recreate();
    }
}
