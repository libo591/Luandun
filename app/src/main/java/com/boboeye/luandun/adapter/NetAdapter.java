package com.boboeye.luandun.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseEvent;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.controller.NetController;
import com.boboeye.luandun.event.NetEvent;
import com.boboeye.luandun.model.impl.NetModel;

import org.kymjs.kjframe.KJBitmap;

import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/8/4.
 */
public class NetAdapter extends BaseListAdapter {
    private static  final String TAG = "NetAdapter";

    public NetAdapter(Context context) {
        super(context);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.netmanage_item, null);
        if(getDataSize()>0){
            NetModel nm = (NetModel) getItem(position);
            ImageView iconImage = (ImageView) view.findViewById(R.id.netitem_imageview);
            KJBitmap kb = new KJBitmap();
            if (nm.getIcon() instanceof String) {
                kb.display(iconImage, nm.getIcon().toString());
            } else {
                iconImage.setImageBitmap((Bitmap) nm.getIcon());
            }
            TextView title = (TextView) view.findViewById(R.id.netitem_title);
            Log.d(TAG, "net list title=" + nm.getTitle());
            title.setText(nm.getTitle());
        }
        return view;
    }
}
