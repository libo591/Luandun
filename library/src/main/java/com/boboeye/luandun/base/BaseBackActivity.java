package com.boboeye.luandun.base;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.boboeye.library.R;

/**
 * Created by libo_591 on 15/9/5.
 */
public class BaseBackActivity extends BaseActivity {
    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        onBackPress();
        return true;
    }
    protected void onBackPress(){
        BaseActivityStack.getInst().finishActivity(this);
        overridePendingTransition(R.anim.activity_fadein,R.anim.activity_fadeout);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid = item.getItemId();
        switch(itemid){
            case android.R.id.home:
                onBackPress();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
