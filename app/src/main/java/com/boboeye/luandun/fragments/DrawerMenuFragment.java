package com.boboeye.luandun.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.activitys.SettingsActivity;
import com.boboeye.luandun.activitys.ThemeListActivity;
import com.boboeye.luandun.activitys.TulingActivity;
import com.boboeye.luandun.activitys.WebViewActivity;
import com.boboeye.luandun.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 *
 */
public class DrawerMenuFragment extends BaseFragment {
    private static final String TAG = "DrawerMenuFragment";
    @InjectView(R.id.menu_discover_icon)
    public TextView discoverIcon;
    @InjectView(R.id.menu_set_icon)
    public TextView setIcon;
    @InjectView(R.id.menu_theme_icon)
    public TextView themeIcon;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_drawer_menu;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        ButterKnife.inject(this, view);
        discoverIcon.setTypeface(AppConfig.getInst().getTypeFace());
        setIcon.setTypeface(AppConfig.getInst().getTypeFace());
        themeIcon.setTypeface(AppConfig.getInst().getTypeFace());
    }
    @OnClick(R.id.menu_dis)
    public void disClick(){
        Intent intent = new Intent(this.getActivity(), TulingActivity.class);
        getActivity().startActivity(intent);
    }
    @OnClick(R.id.menu_set)
    public void setClick(){
        Intent intent = new Intent(this.getActivity(), SettingsActivity.class);
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.menu_theme)
    public void setTheme(){
        Intent intent = new Intent(this.getActivity(), ThemeListActivity.class);
        getActivity().startActivity(intent);
    }
}
