package com.boboeye.luandun.adapter;

import android.app.ActivityManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.controller.ProcessController;
import com.boboeye.luandun.model.impl.ProcessModel;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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
        ProcessModel item = (ProcessModel) getItem(position);
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.phoneman_process_item,null);
            ButterKnife.inject(vh,convertView);
            vh.pm = item;
            vh.position = position;
            convertView.setTag(vh);
            vh.kill.setTypeface(AppConfig.getInst().getTypeFace());
            vh.kill.setText(R.string.delete_icon);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }

        //String state = getStateText(item.getImportance());
        vh.name.setText(item.getName());
        vh.memory.setText(item.getMemory()+"K");
        vh.icon.setBackgroundDrawable(item.getIcon());
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
        public int position;
        public ProcessModel pm;
        @InjectView(R.id.process_name)
        public TextView name;
        @InjectView(R.id.process_memory)
        public TextView memory;
        @InjectView(R.id.process_icon)
        public ImageView icon;
        @InjectView(R.id.process_kill)
        public TextView kill;
        @OnClick(R.id.process_kill)
        public void killProcess(){
            if(pm!=null) {
                ProcessController.getInst().killProccess(pm,position);
            }
        }
    }
}
