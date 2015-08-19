package com.boboeye.luandun.fragments;

import android.view.View;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseFragment;
import com.boboeye.luandun.controller.NetController;

/**
 * Created by libo_591 on 15/8/17.
 */
public class WebsiteContainer extends BaseFragment {
    private MyNetManageFragment _netFragment;
    @Override
    public int getContentLayout() {
        return R.layout.fragment_netmanage;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        if(_netFragment==null) {
            _netFragment = MyNetManageFragment.getInst();
            getFragmentManager().beginTransaction().add(R.id.netmanage_fragment, _netFragment).addToBackStack("mynetmanage").commit();
        }
    }
}
