package com.boboeye.luandun.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.List;


/**
 * need implements methods:
 * initViews
 * initDatas
 * getContentLayout
 * getListView
 * getAdapter
 *
 */
public class BaseListFragment extends BaseFragment implements AbsListView.OnScrollListener,
        SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {
    private static final String TAG = "BaseListFragment";

    //=============must implements=============
    public int getListView(){
        return 0;
    }

    public BaseListAdapter getAdapter(){
        return null;
    }
    //=============must implements=============

    protected BaseListAdapter mAdapter;
    protected ListView mListView;

    @Override
    public void initViews(View view) {
        super.initViews(view);
        mListView = (ListView) view.findViewById(getListView());
        mAdapter = getAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }

    @Override
    public void initDatas() {
        super.initDatas();
        BaseController _controler = getController();
        if(_controler!=null){
            Log.d(TAG,"requestPage===============");
            _controler.requestPage();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(mAdapter==null||mAdapter.getCount()<=0){
            return;
        }
        if(scrollState==SCROLL_STATE_IDLE){
            if(view.getLastVisiblePosition()==mAdapter.getCount()-1){
                getController().requestNextPage();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    @Override
    public void onRefresh() {
        mAdapter.clearData();
        getController().refresh();
    }

    public void onEventBasic(BaseEvent baseEvent){
        if(baseEvent.getType()==BaseEvent.TYPE_BASELIST){
            List<BaseModel> models = (List<BaseModel>)baseEvent.getEventData();
            Log.d(TAG, "获取的数据大小:>" + models.size());
            mAdapter.addDatas(models);
        }else if(baseEvent.getType()==BaseEvent.TYPE_REFRESH){
            ((BaseListController)getController()).setmPageIndex(1);
            onRefresh();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    @Override
    public void onDestroy() {
        mListView.setOnItemClickListener(null);
        mListView.setOnItemLongClickListener(null);
        super.onDestroy();
    }
}
