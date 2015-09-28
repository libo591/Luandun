package com.boboeye.luandun.base;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.boboeye.luandun.AppConfig;
import com.boboeye.library.R;

public class BaseActivity extends ActionBarActivity {

    public BaseController getController(){return null;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppConfig.getInst().setContext(this);
        String theme = AppConfig.getInst().getPrefer("apptheme",R.style.Theme_Default+"");
        setTheme(Integer.parseInt(theme));
        initLayout();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowTitleEnabled(true);
        initViews(savedInstanceState);
        initData(savedInstanceState);
        BaseActivityStack.getInst().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        BaseController _control = getController();
        if(_control!=null) {
            _control.unregist(this);
        }
        BaseActivityStack.getInst().removeActivity(this);
        AppConfig.getInst().setContext(BaseActivityStack.getInst().getLast());
        super.onDestroy();
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
    public void initViews(Bundle savedInstanceState) {

    }

    public void initData(Bundle savedInstanceState){
        BaseController _control = getController();
        if(_control!=null) {
            _control.regist(this);
        }
    }
    //==========must implement===========
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_fadein, R.anim.activity_fadeout);
    }
}
