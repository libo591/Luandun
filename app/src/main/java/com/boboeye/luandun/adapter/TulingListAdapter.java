package com.boboeye.luandun.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.model.impl.TulingModel;
import com.boboeye.luandun.utils.DensityUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;


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
            GradientDrawable tulinggd = createGradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM);
            if(Build.VERSION.SDK_INT>=16) {
                vh.talkitem.setBackground(tulinggd);
            }else{
                vh.talkitem.setBackgroundDrawable(tulinggd);
            }
        }else{
            GradientDrawable usergd = createGradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP);
            vh.image.setText(R.string.my_icon);
            if(Build.VERSION.SDK_INT>=16) {
                vh.talkitem.setBackground(usergd);
            }else{
                vh.talkitem.setBackgroundDrawable(usergd);
            }
        }
        vh.message.setText(tm.getMsg());
        return convertView;
    }
    class ViewHolder{
        @InjectView(R.id.talkitem)
        public LinearLayout talkitem;
        @InjectView(R.id.tulingitem_text)
        public TextView message;
        @InjectView(R.id.tulingitem_image)
        public TextView image;
    }

    private GradientDrawable createGradientDrawable(GradientDrawable.Orientation ori){
        Context ctx = AppConfig.getInst().getContext();
        TypedArray typearray = ctx.obtainStyledAttributes(new int[]{
                com.boboeye.library.R.attr.colorPrimary, com.boboeye.library.R.attr.window_bg
        });
        int colorPrimary = typearray.getColor(0, Color.WHITE);
        int colorBack = typearray.getColor(1, Color.WHITE);
        typearray.recycle();
        GradientDrawable gd = new GradientDrawable(ori,new int[]{colorPrimary, colorBack});
        int onedp = DensityUtils.dip2px(ctx, 1);
        gd.setStroke(onedp,colorPrimary);
        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gd.setGradientCenter(0.5f, 0.5f);
        gd.setShape(GradientDrawable.RECTANGLE);
        return gd;
    }
}
