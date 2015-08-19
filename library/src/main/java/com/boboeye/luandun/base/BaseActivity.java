package com.boboeye.luandun.base;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.boboeye.luandun.AppConfig;
import com.boboeye.library.R;

public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppConfig.getInst().setContext(this);
        initLayout();
        initViews();
        initData();
    }

    public void initLayout() {
        int contentLayout = getContentLayout();
        if(contentLayout>0){
            setContentView(contentLayout);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        int ml = getMenuLayout();
        if(ml>0){
            getMenuInflater().inflate(ml, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    //==========must implement===========
    public int getContentLayout(){
        return 0;
    }
    //==========must implement===========
    //==========option implement===========
    public int getMenuLayout(){return 0;}
    public void initViews() {

    }

    public void initData(){

    }
    //==========must implement===========
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        BaseFragment bf = (BaseFragment)(getSupportFragmentManager().findFragmentByTag("browseweb"));
        if(bf!=null){
            if(bf!=null&&bf.onKeyDown(keyCode,event)){
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
