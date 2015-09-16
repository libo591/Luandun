package com.boboeye.luandun.fragments;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.boboeye.luandun.HomeTabContentViewPagerAdapter;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseFragment;
import com.boboeye.luandun.controller.HomeViewPagerController;
import com.boboeye.luandun.view.PagerSlidingTabStrip;

/**
 * Created by libo_591 on 15/7/26.
 */
public class HomeTabContentFragment extends BaseFragment {
    private static final String TAG = HomeTabContentFragment.class.getSimpleName();

    @Override
    public int getContentLayout() {
        return R.layout.fragment_hometabcontent;
    }

    @Override
    public void initViews(View view) {
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.hometabcontent_viewPager);
        HomeTabContentViewPagerAdapter adapter = new HomeTabContentViewPagerAdapter(getChildFragmentManager());
        viewPager.setOffscreenPageLimit(1);
        PagerSlidingTabStrip tabstrip = (PagerSlidingTabStrip) view.findViewById(R.id.hometabcontent_tabstrip);
        viewPager.setAdapter(adapter);
        tabstrip.setViewPager(viewPager);

        String[] arr = {"网站","手机管理","密码"};//,"文档"};//"",
        int len = arr.length;
        for(int i=0;i<len;i++){
            View tabview = LayoutInflater.from(getActivity()).inflate(R.layout.tabstrip_tabview,null,false);
            TextView tabstrip_title = (TextView)tabview.findViewById(R.id.tabstrip_title);
            tabstrip_title.setText(arr[i]);
            tabstrip.addTab(tabview);
        }

        viewPager.setCurrentItem(0);

        HomeViewPagerController.getInst().registViewPager(viewPager);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "=================destroy======================================");
        HomeViewPagerController.getInst().unRegistViewPager();
        super.onDestroy();
    }
}
