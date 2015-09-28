package com.boboeye.luandun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.model.impl.ThemeModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by libo_591 on 15/9/27.
 */
public class ThemeAdapter extends BaseListAdapter {

    public ThemeAdapter(Context context) {
        super(context);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ThemeViewHolder vh = null;
        if(convertView==null){
            vh = new ThemeViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.themelist_item,null);
            ButterKnife.inject(vh,convertView);
        }else{
            vh = (ThemeViewHolder)convertView.getTag();
        }
        ThemeModel tm = (ThemeModel)getItem(position);
        vh.themetext.setText(tm.getName());
        vh.themetext.setTextColor(tm.getTextpreview());
        vh.themetext.setBackgroundColor(tm.getPreview());
        String currtheme = AppConfig.getInst().getPrefer("apptheme",R.style.Theme_Default+"");
        if(currtheme.equals(tm.getThemeRes()+"")){
            vh.themeborder.setVisibility(View.VISIBLE);
        }else{
            vh.themeborder.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ThemeViewHolder{
        @InjectView(R.id.themeitem_text)
        public TextView themetext;
        @InjectView(R.id.themeitem_border)
        public RelativeLayout themeborder;
    }
}
