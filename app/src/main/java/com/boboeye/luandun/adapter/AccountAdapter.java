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

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.controller.AccountController;
import com.boboeye.luandun.event.AccountEvent;
import com.boboeye.luandun.model.impl.AccountModel;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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
            ButterKnife.inject(vh,convertView);
            vh.typeIconTextView.setTypeface(AppConfig.getInst().getTypeFace());
            vh.typeIconTextView.setText(R.string.title_icon);
            vh.nameIconTextView.setTypeface(AppConfig.getInst().getTypeFace());
            vh.nameIconTextView.setText(R.string.account_icon);
            vh.passIconTextView.setTypeface(AppConfig.getInst().getTypeFace());
            vh.passIconTextView.setText(R.string.password_icon);
            vh.passitem_option.setTypeface(AppConfig.getInst().getTypeFace());
            vh.edit.setTypeface(AppConfig.getInst().getTypeFace());
            vh.edit.setText(R.string.edit_icon);
            vh.delete.setTypeface(AppConfig.getInst().getTypeFace());
            vh.delete.setText(R.string.delete_icon);
            vh.mPosition = position;
            vh.am = (AccountModel)getItem(position);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }
        AccountModel pm = (AccountModel) getItem(position);
        vh.typeTextView.setText(pm.getType());
        vh.nameTextView.setText(pm.getName());
        vh.passTextView.setText(pm.getPassword());
        return convertView;
    }

    class ViewHolder{
        public int mPosition;
        public AccountModel am;
        @InjectView(R.id.passitem_type_icon)
        public TextView typeIconTextView;
        @InjectView(R.id.passitem_name_icon)
        public TextView nameIconTextView;
        @InjectView(R.id.passitem_pass_icon)
        public TextView passIconTextView;
        @InjectView(R.id.passitem_type)
        public TextView typeTextView;
        @InjectView(R.id.passitem_name)
        public TextView nameTextView;
        @InjectView(R.id.passitem_pass)
        public TextView passTextView;

        @InjectView(R.id.passitem_option)
        public TextView passitem_option;
        @InjectView(R.id.passitem_optionbox)
        public LinearLayout optionbox;
        @InjectView(R.id.passitem_edit)
        public TextView edit;
        @InjectView(R.id.passitem_delete)
        public TextView delete;

        @OnClick(R.id.passitem_option)
        public void optionClick(){
            if(optionbox.isShown()) {
                optionbox.setVisibility(View.GONE);
                Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.listitem_hide_scale);
                optionbox.startAnimation(anim);
            }else{
                optionbox.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.listitem_show_scale);
                optionbox.startAnimation(anim);
            }
        }

        @OnClick(R.id.passitem_edit)
        public void editClick(){
            AccountEvent pe = new AccountEvent(AccountEvent.SHOW_EDIT, Arrays.asList(mPosition));
            AccountController.getInst().getBus().post(pe);
        }

        @OnClick(R.id.passitem_delete)
        public void deleteClick(){
            AccountController.getInst().delete(am);
        }
    }
}
