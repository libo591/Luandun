package com.boboeye.luandun.activitys;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TabHost;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseActivity;
import com.boboeye.luandun.proxy.DrawerLayoutProxy;
import com.boboeye.luandun.view.HomeFragmentTabHost;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends BaseActivity {
    private DrawerLayoutProxy mDrawerLayoutProxy;
    @InjectView(R.id.home_drawerlayout)
    public DrawerLayout mDrawerLayout;
    @InjectView(R.id.home_tabHost)
    public HomeFragmentTabHost mTabHost;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.inject(this);
        mDrawerLayoutProxy = new DrawerLayoutProxy(this,mDrawerLayout);
        mDrawerLayoutProxy.setupDrawerLayout();

        mTabHost.setup(this, getSupportFragmentManager(), R.id.home_tabrealcontent);
        String tab1 = getResources().getString(R.string.home_tabhost_often);
        String tab2 = getResources().getString(R.string.home_tabhost_discover);
        mTabHost.addTab(mTabHost.newTabSpec(tab1).setIndicator(tab1), null, null);
        mTabHost.addTab(mTabHost.newTabSpec(tab2).setIndicator(tab2),null,null);
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
    public int getMenuLayout() {
        return R.menu.menu_home;
    }
}
