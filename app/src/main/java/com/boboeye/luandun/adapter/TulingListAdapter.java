package com.boboeye.luandun.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.controller.Tuling123Controller;
import com.boboeye.luandun.model.impl.TulingModel;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by libo_591 on 15/9/26.
 */
public class TulingListAdapter extends BaseListAdapter {
    private static final String TAG = TulingListAdapter.class.getSimpleName();
    public TulingListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView==null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tulinglist_item,null);
            convertView.setTag(vh);
            ButterKnife.inject(vh, convertView);
            vh.image.setTypeface(AppConfig.getInst().getTypeFace());
        }else{
            vh = (ViewHolder)convertView.getTag();
        }
        TulingModel tm = (TulingModel)getItem(position);
        if(tm.getFrom()==TulingModel.FROM_TULING){
            vh.image.setText(R.string.service_icon);
        }else{
            vh.image.setText(R.string.my_icon);
        }
        vh.message.setText(tm.getMsg());
        return convertView;
    }
    class ViewHolder{
        @InjectView(R.id.tulingitem_text)
        public TextView message;
        @InjectView(R.id.tulingitem_image)
        public TextView image;
    }
}
