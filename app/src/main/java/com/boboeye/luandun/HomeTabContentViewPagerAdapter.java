package com.boboeye.luandun;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;
import com.boboeye.luandun.fragments.WebSiteListFragment;
import com.boboeye.luandun.fragments.AccountManageFragment;
import com.boboeye.luandun.fragments.PhoneManagerMain;

/**
 * Created by libo_591 on 15/7/26.
 */
public class HomeTabContentViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = HomeTabContentViewPagerAdapter.class.getSimpleName();

    public HomeTabContentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment[] arr = {new WebSiteListFragment(),new PhoneManagerMain(),new AccountManageFragment()};
        return arr[position];
    }

    @Override
    public int getCount() {
        return 3;
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
