package com.boboeye.luandun.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.boboeye.luandun.R;
import com.boboeye.luandun.adapter.NetAdapter;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;
import com.boboeye.luandun.base.BasePopupManager;
import com.boboeye.luandun.controller.NetController;
import com.boboeye.luandun.event.NetEvent;
import com.boboeye.luandun.model.impl.NetModel;

import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/8/2.
 */
public class MyNetManageFragment extends BaseListFragment
        implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener,View.OnClickListener {
    private static MyNetManageFragment _inst = new MyNetManageFragment();
    public static MyNetManageFragment getInst(){
        return _inst;
    }
    private static final String TAG = "MyNetManageFragment";
    private int operPosition;
    private View operview;
    private int operviewkey;

    private View modelView;
    private EditText modelViewTitle;
    private EditText modelViewUrl;
    private NetController mNetController=NetController.getInst();
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
        return new NetAdapter(getActivity());
    }

    @Override
    public BaseController getController() {
        return NetController.getInst();
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        operview = LayoutInflater.from(getActivity()).inflate(R.layout.popup_editdelete, null,false);
        operview.findViewById(R.id.netmanage_itemedit).setOnClickListener(this);
        operview.findViewById(R.id.netmanage_itemdelete).setOnClickListener(this);

        modelView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_netitemview, null,false);
        modelView.findViewById(R.id.netmodelview_edit).setOnClickListener(this);
        modelView.findViewById(R.id.netmodelview_cancel).setOnClickListener(this);
        modelViewTitle = ((EditText)modelView.findViewById(R.id.netmodelview_title));
        modelViewUrl = ((EditText)modelView.findViewById(R.id.netmodelview_url));
    }

    @Override
    public AdapterView.OnItemClickListener getOnitemClickListener() {
        return this;
    }

    @Override
    public AdapterView.OnItemLongClickListener getOnitemLongClickListener() {
        return this;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //navigate to webactivity
        Log.d(TAG, "goto browseweb fragment");
        NetModel netModel = (NetModel) mAdapter.getItem(position);
        BrowseWebfragment bwf = BrowseWebfragment.getInst(netModel.getUrl());
        getFragmentManager().beginTransaction().add(R.id.netmanage_fragment, bwf).addToBackStack("browseweb").commit();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "itemlongclick--" + position);
        operPosition = position;
        operviewkey = BasePopupManager.addPopup(operview, getActivity().getWindow());
        return true;
    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        NetModel nm= (NetModel)mAdapter.getItem(operPosition);
        if(viewid==R.id.netmanage_itemdelete){
            BasePopupManager.removePop(operviewkey);
            mNetController.delete(nm);
        }else if(viewid==R.id.netmanage_itemedit){
            BasePopupManager.removePop(operview);
            modelViewTitle.setText(nm.getTitle());
            modelViewUrl.setText(nm.getUrl());
            BasePopupManager.addPopup(modelView, getActivity().getWindow());
        }else if(viewid==R.id.netmodelview_edit){
            nm.setTitle(modelViewTitle.getText().toString());
            nm.setUrl(modelViewUrl.getText().toString());
            BasePopupManager.removePop(modelView);
            mNetController.edit(nm);
        }else if(viewid==R.id.netmodelview_cancel){
            BasePopupManager.removePop(modelView);
        }
    }

    @Subscribe
    public void onEventMainThread(NetEvent ne){
        if(ne.getType()==NetEvent.TYPE_DELETE){
            String msg = "删除成功";
            Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
            this.onRefresh();
        }else if(ne.getType()==NetEvent.TYPE_EDIT){
            String msg = "修改成功";
            Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
            this.onRefresh();
        }
    }
}
