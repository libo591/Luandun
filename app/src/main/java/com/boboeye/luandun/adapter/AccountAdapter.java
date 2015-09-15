package com.boboeye.luandun.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.controller.AccountController;
import com.boboeye.luandun.event.AccountEvent;
import com.boboeye.luandun.model.impl.AccountModel;

import java.util.Arrays;

/**
 * Created by libo_591 on 15/9/5.
 */
public class AccountAdapter extends BaseListAdapter {

    private static final String TAG = "AccountAdapter";

    public AccountAdapter(Context context) {
        super(context);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        final int opPos = position;
        if(convertView==null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.passmanage_item, null);
            vh.nameTextView = (TextView)convertView.findViewById(R.id.passitem_name);
            vh.passTextView = (TextView)convertView.findViewById(R.id.passitem_pass);
            vh.passIcon = (ImageView)convertView.findViewById(R.id.passitem_icon);
            vh.passitem_option = (ImageView)convertView.findViewById(R.id.passitem_option);
            vh.optionbox = (LinearLayout)convertView.findViewById(R.id.passitem_optionbox);
            vh.edit = (TextView)convertView.findViewById(R.id.passitem_edit);
            vh.delete = (TextView)convertView.findViewById(R.id.passitem_delete);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }
        AccountModel pm = (AccountModel) getItem(position);
        Log.d(TAG, pm.getName() + "==" + pm.getPassword());
        vh.nameTextView.setText(pm.getName());
        vh.passTextView.setText(pm.getPassword());
        //vh.passIcon.setBackgroundResource(pm.getType());
        final LinearLayout editor = vh.optionbox;
        vh.passitem_option.setOnClickListener(new View.OnClickListener(){
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
        vh.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountEvent pe = new AccountEvent(AccountEvent.SHOW_EDIT, Arrays.asList(opPos));
                AccountController.getInst().getBus().post(pe);
            }
        });
        vh.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountModel pm = (AccountModel) getItem(opPos);
                AccountController.getInst().delete(pm);
            }
        });
        return convertView;
    }

    class ViewHolder{
        public TextView nameTextView;
        public TextView passTextView;
        public ImageView passIcon;
        public ImageView passitem_option;
        public LinearLayout optionbox;
        public TextView edit;
        public TextView delete;
    }
}
