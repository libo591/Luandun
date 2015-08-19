package com.boboeye.luandun.base;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by libo_591 on 15/7/25.
 */
public class BaseFragment extends Fragment implements BaseBuild {
    //=============must implements=============
    public int getContentLayout(){
        return 0;
    }
    //=============must implements=============

    //=============option implements=============
    public BaseController getController(){return null;}
    @Override
    public void initViews(View view) {}

    @Override
    public void initDatas() {
        BaseController _control = getController();
        if(_control!=null) {
            _control.registFragment(this);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){return false;}
    //=============option implements=============


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayout(),null,false);
        initViews(view);
        initDatas();
        return view;
    }


    @Override
    public void onDestroy() {
        BaseController _control = getController();
        if(_control!=null) {
            _control.unregistFragment(this);
        }
        super.onDestroy();
    }
}
