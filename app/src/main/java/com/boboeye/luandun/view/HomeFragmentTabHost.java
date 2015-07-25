package com.boboeye.luandun.view;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

/**
 * Created by libo_591 on 15/7/25.
 */
public class HomeFragmentTabHost extends FragmentTabHost {
    public HomeFragmentTabHost(Context context) {
        this(context, null);
    }

    public HomeFragmentTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }
}
