package com.boboeye.luandun.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boboeye.luandun.R;
import com.boboeye.luandun.activitys.WebViewActivity;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.model.impl.SettingsModel;


/**
 * Created by libo_591 on 15/9/17.
 */
public class SettingAdapter extends BaseListAdapter {

    private static final String TAG = SettingAdapter.class.getSimpleName();

    public SettingAdapter(Context context) {
        super(context);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView==null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.settings_listitem,null);
            vh.title = (TextView)convertView.findViewById(R.id.settings_item_title);
            vh.content = (TextView)convertView.findViewById(R.id.settings_item_content);
            vh.content.setMovementMethod(LinkMovementMethod.getInstance());
            vh.content.setAutoLinkMask(Linkify.ALL);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }
        SettingsModel sm = (SettingsModel) getItem(position);
        vh.title.setText(Html.fromHtml(sm.getTitle()));
        vh.content.setText(Html.fromHtml(sm.getContent()));
        final String url = sm.getLinkurl();
        if(!TextUtils.isEmpty(url)) {
            vh.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,WebViewActivity.class);
                    intent.putExtra("url",url);
                    mContext.startActivity(intent);
                }
            });
        }
        return convertView;
    }
    class ViewHolder{
        public TextView title;
        public TextView content;
    }
}
