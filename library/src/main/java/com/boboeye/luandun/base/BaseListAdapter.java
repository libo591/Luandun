package com.boboeye.luandun.base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boboeye.library.R;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

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
    public static final int FOOTERSTATE_LOADING=0;
    public static final int FOOTERSTATE_ERROR  =1;
    public static final int FOOTERSTATE_LOADED =2;
    public static final int FOOTERSTATE_LESSTHANONEPAGE=3;

    private int mState = FOOTERSTATE_LOADING;
    protected Context mContext;
    private View mFooterView;
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
        if(mState==FOOTERSTATE_ERROR
                ||mState==FOOTERSTATE_LOADING){
            return mDatas.size()+1;
        }
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
        int len = mDatas.size();
        if(len>0&&len==position+1){
            if(this.mFooterView==null) {
                this.mFooterView = LayoutInflater.from(mContext).inflate(R.layout.list_item_footer, null);
            }
            if(mState==FOOTERSTATE_LOADED){
                mState = FOOTERSTATE_LOADING;
            }
            ProgressBar pb = (ProgressBar) mFooterView.findViewById(R.id.listfooter_progressbar);
            TextView tv = (TextView)mFooterView.findViewById(R.id.listfooter_loadtext);
            if(mState==FOOTERSTATE_LOADING||mState==FOOTERSTATE_ERROR){
                if(mState==FOOTERSTATE_ERROR){
                    tv.setText(mContext.getResources().getString(R.string.error));
                    pb.setVisibility(View.GONE);
                }else{
                    tv.setText(mContext.getResources().getString(R.string.loading));
                    pb.setVisibility(View.VISIBLE);
                }
                return mFooterView;
            }
        }
        return getItemView(position,convertView,parent);
    }

    public int getState(){
        return mState;
    }

    public void setState(int state){
        mState = state;
    }

    public View getFooterView(){
        return mFooterView;
    }

    public int getDataSize(){
        return mDatas.size();
    }
}
