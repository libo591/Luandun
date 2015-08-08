package com.boboeye.luandun;

import android.support.v4.app.FragmentManager;

import com.boboeye.luandun.base.BaseFragment;
import com.boboeye.luandun.base.BasePagerFragmentStateAdapter;
import com.boboeye.luandun.fragments.MyNetManageFragment;
import com.boboeye.luandun.fragments.PassManageFragment;
import com.boboeye.luandun.fragments.PhoneManageFragment;
import com.boboeye.luandun.fragments.ReaderFragment;
import com.boboeye.luandun.fragments.SocialFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/7/26.
 */
public class HomeTabContentViewPagerAdapter extends BasePagerFragmentStateAdapter {
    public HomeTabContentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public List<BaseFragment> getFragments() {
        List<BaseFragment> list = new ArrayList<>();
        //list.add(new SocialFragment());
        list.add(new MyNetManageFragment());
        list.add(new PhoneManageFragment());
        list.add(new PassManageFragment());
        list.add(new ReaderFragment());
        return list;
    }
}
