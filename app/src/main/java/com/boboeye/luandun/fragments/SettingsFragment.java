package com.boboeye.luandun.fragments;

import android.app.Activity;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.adapter.SettingAdapter;
import com.boboeye.luandun.base.BaseActivityStack;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseFragment;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;
import com.boboeye.luandun.controller.SettingsController;
import com.boboeye.luandun.event.SettingsEvent;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/9/7.
 */
public class SettingsFragment extends BaseListFragment {
    @Override
    public int getContentLayout() {
        return R.layout.settings_list;
    }

    @Override
    public int getListView() {
        return R.id.settings_listview;
    }

    @Override
    public BaseListAdapter getAdapter() {
        return new SettingAdapter(this.getActivity());
    }

    @Override
    public BaseController getController() {
        return SettingsController.getInst();
    }

    @Subscribe
    public void onEventMainThread(SettingsEvent event){
        if(event.getType()==SettingsEvent.TYPE_BASELIST){
            super.onEventBasic(event);
        }
    }
    @InjectView(R.id.settings_maintitle_title)
    public TextView maintitle;
    @InjectView(R.id.settings_maintitle_edittext)
    public EditText maintitle_edittext;
    @InjectView(R.id.settings_maintitle_lock)
    public TextView maintitle_toggle;

    @Override
    public void initViews(View view) {
        super.initViews(view);
        ButterKnife.inject(this, view);
        maintitle.setText(Html.fromHtml(getActivity().getString(R.string.mainpage_title)));
        maintitle_edittext.setText(AppConfig.getInst().getPrefer("main_title", getActivity().getString(R.string.main_title)));
        maintitle_toggle.setTypeface(AppConfig.getInst().getTypeFace());
        maintitle_toggle.setText(R.string.lock_icon);
    }

    @OnClick(R.id.settings_maintitle_lock)
    public void toggleClick(){
        String lockString = getActivity().getResources().getString(R.string.lock_icon);
        if(maintitle_toggle.getText().equals(lockString)){
            maintitle_edittext.setEnabled(true);
            maintitle_toggle.setText(R.string.unlock_icon);
        }else{
            maintitle_toggle.setText(R.string.lock_icon);
            String title = maintitle_edittext.getText().toString();
            AppConfig.getInst().setPrefer("main_title", title);
            maintitle_edittext.setEnabled(false);
            Activity activity = BaseActivityStack.getInst().getFirst();
            if(activity!=null){
                activity.recreate();
            }
        }
    }
}
