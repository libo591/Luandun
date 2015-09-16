package com.boboeye.luandun.activitys;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseActivity;
import com.boboeye.luandun.controller.HomeViewPagerController;
import com.boboeye.luandun.proxy.DrawerLayoutProxy;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends BaseActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private DrawerLayoutProxy mDrawerLayoutProxy;
    @InjectView(R.id.home_drawerlayout)
    public DrawerLayout mDrawerLayout;

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        ButterKnife.inject(this);
        this.getSupportActionBar().setTitle(getString(R.string.main_title));
        mDrawerLayoutProxy = new DrawerLayoutProxy(this,mDrawerLayout);
        mDrawerLayoutProxy.setupDrawerLayout();
        mDrawerLayout.closeDrawers();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerLayoutProxy.onOptionsItemSelected(item)) {
            return true;
        }
        int itemid = item.getItemId();
        if(itemid==R.id.actionbar_add){
            HomeViewPagerController.getInst().dispatchAddEvent();
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
