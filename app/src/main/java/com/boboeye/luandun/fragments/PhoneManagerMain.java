package com.boboeye.luandun.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.boboeye.luandun.R;
import com.boboeye.luandun.activitys.ProcessActivity;
import com.boboeye.luandun.adapter.PhoneManageAdapter;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;
import com.boboeye.luandun.controller.PhoneManageController;
import com.boboeye.luandun.event.PhoneManageEvent;
import com.boboeye.luandun.model.impl.PhoneManageModel;

import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/8/19.
 */
public class PhoneManagerMain extends BaseListFragment{
    @Override
    public int getContentLayout() {
        return R.layout.fragment_phonemanage_main;
    }

    @Override
    public int getListView() {
        return R.id.pm_part1_list;
    }

    @Override
    public BaseListAdapter getAdapter() {
        return new PhoneManageAdapter(this.getActivity());
    }

    @Override
    public BaseController getController() {
        return PhoneManageController.getInst();
    }

    @Subscribe
    public void onEventMainThread(PhoneManageEvent phoneEvent){
        if(phoneEvent.getType()== PhoneManageEvent.TYPE_BASELIST) {
            super.onEventBasic(phoneEvent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PhoneManageModel pmm = (PhoneManageModel) mAdapter.getItem(position);
        String manID=pmm.getId();
        if(PhoneManageController.MAN_PROCESS.equals(manID)){
            Intent intent = new Intent(this.getActivity(), ProcessActivity.class);
            this.getActivity().startActivity(intent);
        }
    }
}
