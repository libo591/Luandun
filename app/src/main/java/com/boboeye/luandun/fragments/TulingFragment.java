package com.boboeye.luandun.fragments;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.adapter.TulingListAdapter;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.controller.Tuling123Controller;
import com.boboeye.luandun.event.TulingEvent;
import com.boboeye.luandun.model.impl.TulingModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/9/26.
 */
public class TulingFragment extends BaseListFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = TulingFragment.class.getSimpleName();

    @Override
    public BaseController getController() {
        return Tuling123Controller.getInst();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_tuling;
    }

    @Override
    public int getListView() {
        return R.id.tulinglistview;
    }

    @Override
    public BaseListAdapter getAdapter() {
        return new TulingListAdapter(this.getActivity());
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        ButterKnife.inject(this, view);
        reqPagelayout.setOnRefreshListener(this);
        submit.setTypeface(AppConfig.getInst().getTypeFace());
        submit.setText(R.string.submit_icon);
    }

    @Subscribe
    public void onEventMainThread(TulingEvent event){
        if(event.getType()==TulingEvent.TYPE_QUERY){
            List<BaseModel> list = event.getEventData();
            TulingModel tm = (TulingModel) list.get(0);
            mAdapter.addData(tm);
            listview.setSelection(listview.getBottom());
        }else{
            super.onEventBasic(event);
            listview.setSelection(listview.getBottom());
            reqPagelayout.setRefreshing(false);
        }
    }

    private void hideSoftInput(){
        InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(),0);
    }

    @InjectView(R.id.tulingitem_input)
    public EditText input;
    @InjectView(R.id.tulinglistview)
    public ListView listview;
    @InjectView(R.id.tuling_refreshlayout)
    public SwipeRefreshLayout reqPagelayout;
    @InjectView(R.id.tulingitem_submit)
    public TextView submit;

    @OnClick(R.id.tulingitem_submit)
    public void submitClick(){
        Log.d(TAG, "ask tuling some q");
        Tuling123Controller.getInst().addMessage(input.getText().toString(), TulingModel.FROM_USER);
        Tuling123Controller.getInst().queryAnswer(input.getText().toString());
        input.setText("");
        hideSoftInput();
    }
    @OnClick(R.id.tulingitem_input)
    public void inputClick(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listview.setSelection(listview.getBottom());
            }
        }, 500);
    }

    @Override
    public void onRefresh() {
        mAdapter.clearData();
        Tuling123Controller.getInst().requestNextPage();
    }

    @Override
    public void onDestroy() {
        Tuling123Controller.getInst().destroySomeMessage();
        super.onDestroy();
    }
}
