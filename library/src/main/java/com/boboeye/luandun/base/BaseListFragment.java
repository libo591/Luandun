package com.boboeye.luandun.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.List;

import de.greenrobot.event.Subscribe;

/**
 * need implements methods:
 * initViews
 * initDatas
 * getContentLayout
 * getListView
 * getAdapter
 *
 */
public class BaseListFragment extends BaseFragment implements AbsListView.OnScrollListener,SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "BaseListFragment";

    //=============must implements=============
    public int getListView(){
        return 0;
    }

    public BaseListAdapter getAdapter(){
        return null;
    }
    //=============must implements=============

    //=============option implements=============
    public AdapterView.OnItemClickListener getOnitemClickListener(){
        return null;
    }
    public AdapterView.OnItemLongClickListener getOnitemLongClickListener(){return null;}
    //=============option implements=============

    protected BaseListAdapter mAdapter;
    protected ListView mListView;

    @Override
    public void initViews(View view) {
        if(mListView==null){
            mListView = (ListView) view.findViewById(getListView());
        }
        if(mAdapter==null){
            mAdapter = getAdapter();
            mListView.setAdapter(mAdapter);
        }
        AdapterView.OnItemClickListener itemClickLis = getOnitemClickListener();
        if(itemClickLis!=null) {
            mListView.setOnItemClickListener(itemClickLis);
        }
        AdapterView.OnItemLongClickListener itemLongClick = getOnitemLongClickListener();
        if(itemLongClick!=null){
            mListView.setOnItemLongClickListener(itemLongClick);
        }
    }

    @Override
    public void initDatas() {
        super.initDatas();
        getController().requestPage();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(mAdapter==null||mAdapter.getCount()<=0){
            return;
        }
        if(mAdapter.getState()==BaseListAdapter.FOOTERSTATE_LOADING){
            return;
        }
        if(mAdapter.getFooterView()==null){
            return;
        }

        boolean isInEnd = false;
        int position = view.getPositionForView(mAdapter.getFooterView());
        if(position==mAdapter.getCount()-1){isInEnd=true;}

        getController().requestNextPage();
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onRefresh() {
        mAdapter.clearData();
        getController().refresh();
    }

    @Subscribe
    public void onEventMainThread(BaseEvent baseEvent){
        if(baseEvent.getType()==BaseEvent.TYPE_BASELIST){
            int state = (Integer)baseEvent.getEventData().get(0);
            List<BaseModel> models = (List<BaseModel>)baseEvent.getEventData().get(1);
            Log.d(TAG, "获取的数据大小:>" + models.size());
            mAdapter.addDatas(models);
            mAdapter.setState(state);
            mAdapter.notifyDataSetChanged();
        }
    }
}
