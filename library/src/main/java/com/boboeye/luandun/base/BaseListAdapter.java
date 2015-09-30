package com.boboeye.luandun.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * need override methods:
 * requestPage
 * getItemView
 */
public class BaseListAdapter extends BaseAdapter {
    private static final String TAG ="BaseListAdapter";
    //===========must implements===============
    public View getItemView(int position, View convertView, ViewGroup parent){
        return null;
    }
    //===========must implements===============
    protected Context mContext;
    private List mDatas = new ArrayList();

    public BaseListAdapter(Context context){
        this.mContext = context;
    }

    public void addDatas(List data){
        mDatas.addAll(data);
        notifyDataSetChanged();
    }
    public void addData(BaseModel data){
        mDatas.add(data);
        notifyDataSetChanged();
    }
    public void updateData(BaseModel data,int position){
        mDatas.set(position,data);
        notifyDataSetChanged();
    }

    public void removeData(int index){
        mDatas.remove(index);
        notifyDataSetChanged();
    }

    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position,convertView,parent);
    }

    public int getDataSize(){
        return mDatas.size();
    }
}
