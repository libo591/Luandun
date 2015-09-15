package com.boboeye.luandun.adapter;

import android.app.ActivityManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.model.impl.ProcessModel;

/**
 * Created by libo_591 on 15/8/22.
 */
public class ProcessAdapter extends BaseListAdapter {
    private static final String TAG = "ProcessAdapter";

    public ProcessAdapter(Context context) {
        super(context);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.phoneman_process_item,null);
            vh.name = (TextView)convertView.findViewById(R.id.process_name);
            vh.state = (TextView)convertView.findViewById(R.id.process_state);
            vh.memory = (TextView)convertView.findViewById(R.id.process_memory);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }
        if(getDataSize()>position) {
            ProcessModel item = (ProcessModel) getItem(position);
            String state = getStateText(item.getImportance());
            vh.state.setText(state);
            vh.name.setText(item.getName());
            vh.memory.setText(item.getMemory()+"K");
        }
        return convertView;
    }

    private String getStateText(int importance){
        String state = "";
        if(importance==ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND){
            state = "后台";
        }else if(importance==ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
            state = "前台";
        }else if(importance==ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE){
            state = "service";
        }else if(importance==ActivityManager.RunningAppProcessInfo.IMPORTANCE_EMPTY){
            state = "EMPTY";
        }else if(importance==ActivityManager.RunningAppProcessInfo.IMPORTANCE_GONE){
            state = "GONE";
        }else if(importance==ActivityManager.RunningAppProcessInfo.IMPORTANCE_PERCEPTIBLE){
            state = "PERCEPTIBLE";
        }else if(importance==ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE){
            state = "VISIBLE";
        }
        return state;
    }

    class ViewHolder{
        public TextView name;
        public TextView state;
        public TextView memory;
    }
}
