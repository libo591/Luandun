package com.boboeye.luandun.fragments;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.boboeye.luandun.LuanApplication;
import com.boboeye.luandun.R;
import com.boboeye.luandun.activitys.WebViewActivity;
import com.boboeye.luandun.adapter.WebSiteAdapter;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.base.BasePopupManager;
import com.boboeye.luandun.controller.WebSiteController;
import com.boboeye.luandun.event.WebSiteEvent;
import com.boboeye.luandun.model.impl.WebSiteModel;
import com.boboeye.luandun.utils.CipherUtils;
import com.boboeye.luandun.utils.SystemTool;
import com.boboeye.luandun.utils.UrlUtil;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/8/2.
 */
public class WebSiteListFragment extends BaseListFragment
        implements View.OnClickListener {
    private static final String TAG = "WebSiteListFragment";
    private int operPosition;

    private View modelView;
    private EditText modelViewTitle;
    private EditText modelViewUrl;

    private WebSiteController mWebSiteController = WebSiteController.getInst();
    @Override
    public int getContentLayout() {
        return R.layout.fragment_netmanage_1;
    }

    @Override
    public int getListView() {
        return R.id.netmanage_listview;
    }

    @Override
    public BaseListAdapter getAdapter() {
        return new WebSiteAdapter(getActivity());
    }

    @Override
    public BaseController getController() {
        return WebSiteController.getInst();
    }


    @Override
    public void initViews(View view) {
        super.initViews(view);

        modelView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_netitemview, null,false);
        modelView.findViewById(R.id.netmodelview_edit).setOnClickListener(this);
        modelView.findViewById(R.id.netmodelview_cancel).setOnClickListener(this);
        modelViewTitle = ((EditText)modelView.findViewById(R.id.netmodelview_title));
        modelViewUrl = ((EditText)modelView.findViewById(R.id.netmodelview_url));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //navigate to webactivity
        if(!SystemTool.checkNet(this.getActivity())){
            Toast.makeText(this.getActivity(),"网络连接问题",Toast.LENGTH_SHORT).show();
            return;
        }
        WebSiteModel netModel = (WebSiteModel) mAdapter.getItem(position);
        Intent intent = new Intent(this.getActivity(), WebViewActivity.class);
        intent.putExtra("url",netModel.getUrl());
        this.getActivity().startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        WebSiteModel nm= null;
        if(operPosition>=0){
            nm = (WebSiteModel)mAdapter.getItem(operPosition);
        }
        if(viewid==R.id.netmodelview_edit){
            String url = modelViewUrl.getText().toString();
            if(nm==null){
                nm = new WebSiteModel();
                nm.setId(CipherUtils.md5(url));
                nm.setTitle(modelViewTitle.getText().toString());
                nm.setUrl(url);
                nm.setIcon(UrlUtil.getFaviconIconUrl(url));
                BasePopupManager.getInst().removePop(modelView);
                mWebSiteController.add(nm);
            }else{
                nm.setTitle(modelViewTitle.getText().toString());
                nm.setUrl(url);
                nm.setIcon(UrlUtil.getFaviconIconUrl(url));
                BasePopupManager.getInst().removePop(modelView);
                mWebSiteController.edit(nm,operPosition);
            }
        }else if(viewid==R.id.netmodelview_cancel){
            BasePopupManager.getInst().removePop(modelView);
        }
    }

    @Subscribe
    public void onEventMainThread(WebSiteEvent ne){
        if(ne.getType()== WebSiteEvent.TYPE_DELETE){
            List datas = ne.getEventData();
            int pos = (Integer)datas.get(0);
            String msg = "删除成功";
            if(pos==-1){
                msg = "删除失败";
            }else{
                this.mAdapter.removeData(pos);
            }
            Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
        }else if(ne.getType()== WebSiteEvent.TYPE_EDIT){
            List datas = ne.getEventData();
            int pos = (Integer)datas.get(0);
            BaseModel model = (BaseModel)datas.get(1);
            String msg = "修改成功";
            if(pos==-1){
                msg = "修改失败";
            }else{
                this.mAdapter.updateData(model,pos);
            }
            Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
        }else if(ne.getType()== WebSiteEvent.TYPE_ADD){
            if(mView.isShown()) {
                operPosition = -1;
                modelViewTitle.setText("");
                modelViewUrl.setText("");
                BasePopupManager.getInst().addPopup(modelView, getActivity().getWindow(), Gravity.BOTTOM,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        }else if(ne.getType()== WebSiteEvent.TYPE_AFTERADD){
            List datas = ne.getEventData();
            Object model = datas.get(0);
            String msg = "添加成功";
            if(model==null) {
                msg = "添加失败";
            }else{
                this.mAdapter.addData((BaseModel)model);
            }
            Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
        }else if(ne.getType()== WebSiteEvent.SHOW_EDIT){
            operPosition = (int)ne.getEventData().get(0);
            WebSiteModel netModel = (WebSiteModel)mAdapter.getItem(operPosition);
            modelViewTitle.setText(netModel.getTitle());
            modelViewUrl.setText(netModel.getUrl());
            BasePopupManager.getInst().addPopup(modelView, getActivity().getWindow(), Gravity.BOTTOM,
                    ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }else{
            super.onEventBasic(ne);
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"=================destroy======================================");
        if(WebSiteController.getInst().getQ()!=null) {
            WebSiteController.getInst().getQ().cancelAll("websiteimage");
        }
        super.onDestroy();
        RefWatcher watcher = LuanApplication.getLeak(this.getActivity());
        watcher.watch(this);
    }
}
