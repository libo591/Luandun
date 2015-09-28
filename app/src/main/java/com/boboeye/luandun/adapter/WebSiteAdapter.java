package com.boboeye.luandun.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.controller.WebSiteController;
import com.boboeye.luandun.event.WebSiteEvent;
import com.boboeye.luandun.model.impl.WebSiteModel;

import org.kymjs.kjframe.KJBitmap;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by libo_591 on 15/8/4.
 */
public class WebSiteAdapter extends BaseListAdapter {
    private static  final String TAG = "WebSiteAdapter";

    public WebSiteAdapter(Context context) {
        super(context);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        //初始化View，得到ViewHolder对象
        convertView = initViewHolder(convertView);
        ViewHolder vh = (ViewHolder)convertView.getTag();
        //显示当前的标题和icon
        dispCurrTitleAndIcon(position, vh);
        //设置事件
        initDispEvent(position, vh);
        return convertView;
    }

    private void initDispEvent(int position, ViewHolder vh) {
        final LinearLayout editor = vh.optionbox;
        final TextView edit = vh.edit;
        final TextView delete = vh.delete;
        final WebSiteModel netModel = (WebSiteModel)getItem(position);
        final int opPos = position;

        TextView option = vh.option;
        option.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(editor.isShown()) {
                    editor.setVisibility(View.GONE);
                    Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.listitem_hide_scale);
                    editor.startAnimation(anim);
                }else{
                    editor.setVisibility(View.VISIBLE);
                    Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.listitem_show_scale);
                    editor.startAnimation(anim);

                }
            }
        });
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                List datas = new ArrayList();
                datas.add(opPos);
                WebSiteEvent ne = new WebSiteEvent(datas, WebSiteEvent.SHOW_EDIT);
                WebSiteController.getInst().getBus().post(ne);
                editor.setVisibility(View.GONE);
            }
        });
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                WebSiteController.getInst().delete(netModel);
                editor.setVisibility(View.GONE);
            }
        });
    }

    private void dispCurrTitleAndIcon(int position, ViewHolder vh) {
        WebSiteModel nm = (WebSiteModel) getItem(position);
        Log.d(TAG, "net list title=" + nm.getTitle());
        vh.title.setText(nm.getTitle());
        KJBitmap kb = new KJBitmap();
        if (nm.getIcon() instanceof String) {
            kb.display(vh.icon, nm.getIcon().toString());
        } else {
            vh.icon.setImageBitmap((Bitmap) nm.getIcon());
        }
    }

    private View initViewHolder(View convertView){
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.netmanage_item, null);
            ViewHolder vh = new ViewHolder();
            ButterKnife.inject(vh,convertView);
            vh.option.setTypeface(AppConfig.getInst().getTypeFace());
            vh.edit.setTypeface(AppConfig.getInst().getTypeFace());
            vh.edit.setText(R.string.edit_icon);
            vh.delete.setTypeface(AppConfig.getInst().getTypeFace());
            vh.delete.setText(R.string.delete_icon);
            convertView.setTag(vh);
        }
        return convertView;
    }

    class ViewHolder{
        @InjectView(R.id.netitem_icon)
        public ImageView icon;
        @InjectView(R.id.netitem_title)
        public TextView title;
        @InjectView(R.id.netitem_optionbox)
        public LinearLayout optionbox;
        @InjectView(R.id.netitem_edit)
        public TextView edit;
        @InjectView(R.id.netitem_delete)
        public TextView delete;
        @InjectView(R.id.netitem_option)
        public TextView option;
    }
}
