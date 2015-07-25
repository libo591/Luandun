package com.boboeye.luandun.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.boboeye.luandun.LuanApplication;

import java.util.List;

/**
 * Created by libo_591 on 15/7/25.
 */
public class BaseListFragment extends BaseFragment implements AbsListView.OnScrollListener,SwipeRefreshLayout.OnRefreshListener {
    private BaseListAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayout(),null,false);
        ListView listview = (ListView) view.findViewById(getListView());
        mAdapter = getAdapter();
        listview.setAdapter(mAdapter);
        return view;
    }

    public int getContentLayout(){
        return 0;
    }

    public int getListView(){
        return 0;
    }

    public BaseListAdapter getAdapter(){
        return null;
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

        mAdapter.requestNextPage();
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onRefresh() {
        mAdapter.refresh();
    }
}
