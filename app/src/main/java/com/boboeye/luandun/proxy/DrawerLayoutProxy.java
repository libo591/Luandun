package com.boboeye.luandun.proxy;

import android.content.res.Configuration;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import com.boboeye.luandun.R;
import com.boboeye.luandun.fragments.DrawerMenuFragment;
/**
 * Created by libo_591 on 15/7/25.
 */
public class DrawerLayoutProxy {
    private ActionBarActivity mActionBarActivity;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    public DrawerLayoutProxy(ActionBarActivity homeActivity,DrawerLayout drawerLayout){
        this.mActionBarActivity = homeActivity;
        this.mDrawerLayout = drawerLayout;
    }

    public void setupDrawerLayout(){
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerToggle = new ActionBarDrawerToggle(mActionBarActivity,
                mDrawerLayout,null,R.string.drawerOpen,R.string.drawerClose){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    private ActionBar getActionBar(){
        return mActionBarActivity.getSupportActionBar();
    }

    private void invalidateOptionsMenu() {
        mActionBarActivity.invalidateOptionsMenu();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
