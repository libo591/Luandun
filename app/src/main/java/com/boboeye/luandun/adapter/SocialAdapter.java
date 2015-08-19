package com.boboeye.luandun.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseEvent;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.controller.SocialController;
import com.boboeye.luandun.model.impl.SocialMessage;

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.bitmap.ImageDisplayer;
import org.kymjs.kjframe.utils.DensityUtils;

import java.util.Date;

import de.greenrobot.event.EventBus;

/**
 * Created by libo_591 on 15/7/26.
 */
public class SocialAdapter extends BaseListAdapter {
    public SocialAdapter(Context context) {
        super(context);
    }


    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView==null){
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.social_item,null,false);
            vh = new ViewHolder();
            vh.social_from = (TextView)convertView.findViewById(R.id.social_item_from);
            vh.social_time = (TextView)convertView.findViewById(R.id.social_item_time);
            vh.social_person = (TextView)convertView.findViewById(R.id.social_item_person);
            vh.social_message = (TextView)convertView.findViewById(R.id.social_item_message);
            vh.social_imagelist = (LinearLayout)convertView.findViewById(R.id.social_item_imagelist);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }
        SocialMessage sm = (SocialMessage) this.getItem(position);
        vh.social_from.setText(sm.getFrom());
        vh.social_time.setText(DateFormat.format("yyyy-MM-dd HH:mm:ss", sm.getSendTime()));
        vh.social_person.setText(sm.getName());
        vh.social_message.setText(sm.getMessage());
        String[] images = sm.getImages();
        int len = images.length;
        ImageView iv = null;
        for(int i=0;i<len;i++){
            iv = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int margin = DensityUtils.dip2px(mContext,mContext.getResources().getDimension(R.dimen.normal_h_margin));
            lp.setMargins(margin, margin, margin, margin);
            iv.setLayoutParams(lp);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            KJBitmap kjbitmap = new KJBitmap();
            kjbitmap.display(iv,images[i]);
            vh.social_imagelist.addView(iv);
        }
        return convertView;
    }

    class ViewHolder{
        public TextView social_from;
        public TextView social_time;
        public TextView social_person;
        public TextView social_message;
        public LinearLayout social_imagelist;

    }
}
