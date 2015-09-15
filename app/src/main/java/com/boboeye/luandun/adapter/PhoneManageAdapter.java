package com.boboeye.luandun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.model.impl.PhoneManageModel;

/**
 * Created by libo_591 on 15/8/19.
 */
public class PhoneManageAdapter extends BaseListAdapter {
    public PhoneManageAdapter(Context context) {
        super(context);
    }

    public View getItemView(int position, View convertView, ViewGroup parent){
        View view = LayoutInflater.from(mContext).inflate(R.layout.phonemain_item,null);
        if(getDataSize()>position){
            PhoneManageModel pmm = (PhoneManageModel) getItem(position);
            TextView textview = (TextView) view.findViewById(R.id.pm_mainitem_text);
            textview.setText(pmm.getName());
        }
        return view;
    }

}
