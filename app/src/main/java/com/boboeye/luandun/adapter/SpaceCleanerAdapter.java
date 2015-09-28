package com.boboeye.luandun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.model.impl.SpaceCleanerModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by libo_591 on 15/9/17.
 */
public class SpaceCleanerAdapter extends BaseListAdapter {
    private static final String TAG = SpaceCleanerAdapter.class.getSimpleName();

    public SpaceCleanerAdapter(Context context) {
        super(context);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        SpaceCleanerModel am = (SpaceCleanerModel)getItem(position);
        if(convertView==null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cleancache_list_item,null);
            ButterKnife.inject(vh,convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }

        vh.icon.setBackgroundDrawable(am.getIcon());
        vh.title.setText(am.getLabel());
        vh.size.setText(am.getSize() + "K");
        return convertView;
    }

    class ViewHolder{
        @InjectView(R.id.autostart_item_icon)
        public ImageView icon;
        @InjectView(R.id.autostart_item_textview)
        public TextView title;
        @InjectView(R.id.autostart_item_size)
        public TextView size;
    }
}
