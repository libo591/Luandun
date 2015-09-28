package com.boboeye.luandun.activitys;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseActivity;
import com.boboeye.luandun.controller.HomeViewPagerController;
import com.boboeye.luandun.proxy.DrawerLayoutProxy;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private DrawerLayoutProxy mDrawerLayoutProxy;
    @InjectView(R.id.home_drawerlayout)
    public DrawerLayout mDrawerLayout;

    public TextView menuAdd;

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        ButterKnife.inject(this);
        String title = AppConfig.getInst().getPrefer("main_title",getString(R.string.main_title));
        this.getSupportActionBar().setTitle(title);
        mDrawerLayoutProxy = new DrawerLayoutProxy(this,mDrawerLayout);
        mDrawerLayoutProxy.setupDrawerLayout();
        mDrawerLayout.closeDrawers();
        this.getSupportActionBar().setDisplayShowCustomEnabled(true);
        menuAdd = (TextView)LayoutInflater.from(this).inflate(R.layout.actionbar_views, null);
        menuAdd.setTypeface(AppConfig.getInst().getTypeFace());
        menuAdd.setText(R.string.menu_add);
        menuAdd.setOnClickListener(this);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        this.getSupportActionBar().setCustomView(menuAdd,lp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerLayoutProxy.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerLayoutProxy.onConfigurationChanged(newConfig);
    }


    @Override
    public int getContentLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void onClick(View v) {
        if (v==menuAdd) {
            HomeViewPagerController.getInst().dispatchAddEvent();
        }
    }
}
