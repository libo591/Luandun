package com.boboeye.luandun.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by libo_591 on 15/7/25.
 */
public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    protected View mView;

    //=============must implements=============
    public int getContentLayout(){
        return 0;
    }
    //=============must implements=============

    public BaseController getController(){return null;}

    public void initViews(View view) {
        mView = view;
    }


    public void initDatas() {
        BaseController _control = getController();
        if(_control!=null) {
            _control.regist(this);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){return false;}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView :"+this);
        View view = inflater.inflate(getContentLayout(),null,false);
        initViews(view);
        initDatas();
        return view;
    }


    @Override
    public void onDestroy() {
        BaseController _control = getController();
        if(_control!=null) {
            _control.unregist(this);
        }
        super.onDestroy();
    }
}
