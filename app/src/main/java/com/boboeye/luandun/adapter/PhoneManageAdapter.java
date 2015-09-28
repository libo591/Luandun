package com.boboeye.luandun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.model.impl.PhoneManageModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by libo_591 on 15/8/19.
 */
public class PhoneManageAdapter extends BaseListAdapter {
    public PhoneManageAdapter(Context context) {
        super(context);
    }

    public View getItemView(int position, View convertView, ViewGroup parent){
        PhoneViewHolder vh = null;
        if(convertView==null){
            vh = new PhoneViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.phonemain_item,null);
            ButterKnife.inject(vh,convertView);
            convertView.setTag(vh);
        }else{
            vh = (PhoneViewHolder)convertView.getTag();
        }
        PhoneManageModel pmm = (PhoneManageModel) getItem(position);
        vh.icon.setTypeface(AppConfig.getInst().getTypeFace());
        vh.icon.setText(pmm.getIcon());
        vh.text.setText(pmm.getName());
        return convertView;
    }
    class PhoneViewHolder{
        @InjectView(R.id.pm_mainitem_icon)
        public TextView icon;
        @InjectView(R.id.pm_mainitem_text)
        public TextView text;
    }

}
