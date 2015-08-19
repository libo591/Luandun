package com.boboeye.luandun;

import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.boboeye.luandun.base.BaseFragment;
import com.boboeye.luandun.base.BasePagerFragmentStateAdapter;
import com.boboeye.luandun.fragments.MyNetManageFragment;
import com.boboeye.luandun.fragments.PassManageFragment;
import com.boboeye.luandun.fragments.PhoneManageFragment;
import com.boboeye.luandun.fragments.ReaderFragment;
import com.boboeye.luandun.fragments.SocialFragment;
import com.boboeye.luandun.fragments.WebsiteContainer;

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
        list.add(new WebsiteContainer());
        list.add(new PhoneManageFragment());
        list.add(new PassManageFragment());
        list.add(new ReaderFragment());
        return list;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            super.destroyItem(container, position, object);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
