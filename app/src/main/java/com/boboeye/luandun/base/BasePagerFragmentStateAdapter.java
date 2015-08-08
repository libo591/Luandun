package com.boboeye.luandun.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by libo_591 on 15/7/26.
 */
public class BasePagerFragmentStateAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> mDatas;

    public BasePagerFragmentStateAdapter(FragmentManager fm) {
        super(fm);
        mDatas = getFragments();
    }

    @Override
    public Fragment getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    public List<BaseFragment> getFragments(){
        return null;
    }
}
