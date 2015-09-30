package com.boboeye.luandun.fragments;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.boboeye.luandun.R;
import com.boboeye.luandun.adapter.AccountAdapter;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.base.BasePopupManager;
import com.boboeye.luandun.controller.AccountController;
import com.boboeye.luandun.event.AccountEvent;
import com.boboeye.luandun.model.impl.AccountModel;
import com.boboeye.luandun.utils.CipherUtils;


import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/8/2.
 */
public class AccountManageFragment extends BaseListFragment implements View.OnClickListener {
    private static final String TAG = AccountManageFragment.class.getSimpleName();
    private View popInputView;
    private View popInfoView;
    private EditText popInfoView_type;
    private EditText popInfoView_Name;
    private EditText popInfoView_Pass;
    private Button popInfoView_Submit;
    private Button popInfoView_Cancel;
    @InjectView(R.id.nothing)
    public RelativeLayout nothing;

    private AccountModel pm;
    private int pmPos;
    private boolean inEdit;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_passmanage;
    }

    @Override
    public int getListView() {
        return  R.id.passlist;
    }

    @Override
    public BaseController getController() {
        return AccountController.getInst();
    }

    @Override
    public BaseListAdapter getAdapter() {
        return new AccountAdapter(this.getActivity());
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        ButterKnife.inject(this,view);
    }

    @Subscribe
    public void onEventMainThread(AccountEvent accountEvent) {
        int et = accountEvent.getType();
        if(et== AccountEvent.TYPE_BASELIST) {
            List datas = accountEvent.getEventData();
            if(datas!=null&&datas.size()>0) {
                listViewVisiChange(true);
                super.onEventBasic(accountEvent);
            }else{
                listViewVisiChange(false);
            }
        }else if(et== AccountEvent.TYPE_DELETE){
            List datas = accountEvent.getEventData();
            int pos = (Integer)datas.get(0);
            if(pos>=0) {
                mAdapter.removeData(pos);
                String msg = "删除成功";
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
            if(mListView.getChildCount()<=0){
                listViewVisiChange(false);
            }else{
                listViewVisiChange(true);
            }
        }else if(et== AccountEvent.TYPE_EDIT){
            List datas = accountEvent.getEventData();
            if(datas!=null) {
                AccountModel am = (AccountModel)datas.get(0);
                int pos = (Integer)datas.get(1);
                mAdapter.updateData(am,pos);
                String msg = "修改成功";
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        }else if(et== AccountEvent.TYPE_ADD){
            if(!mView.isShown()){
                return;
            }
            pm = null;
            pmPos = -1;
            showOrEditItem(true);
        }else if(et== AccountEvent.SHOW_EDIT){
            int pos = (int) accountEvent.getEventData().get(0);
            pm = (AccountModel)mAdapter.getItem(pos);
            pmPos = pos;
            showOrEditItem(true);
        }else if(et== AccountEvent.TYPE_AFTERADD){
            List datas = accountEvent.getEventData();
            Object am = datas.get(0);
            if(am!=null&&am instanceof BaseModel) {
                mAdapter.addData((BaseModel)am);
                String msg = "添加成功";
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                listViewVisiChange(true);
            }
        }
    }

    public void listViewVisiChange(boolean show){
        if(show) {
            nothing.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
        }else{
            nothing.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick");
        super.onItemClick(parent, view, position, id);
        pm = (AccountModel) mAdapter.getItem(position);
        pmPos = position;
        showOrEditItem(false);
    }
    private void showOrEditItem(boolean edit){
        AccountController controller = (AccountController) getController();
        inEdit = edit;
        if(TextUtils.isEmpty(controller.getCipherKey())){
            //输入key
            showPopInputView();
        }else{
            //弹出窗口，展现信息
            showInputView();
        }
    }

    private void showInputView() {
        if(popInfoView==null) {
            popInfoView = LayoutInflater.from(getActivity()).inflate(R.layout.passmanage_popwindow, null);
            popInfoView_Submit = (Button) popInfoView.findViewById(R.id.pass_popview_submit);
            popInfoView_Cancel = (Button) popInfoView.findViewById(R.id.pass_popview_cancel);
            popInfoView_Submit.setOnClickListener(this);
            popInfoView_Cancel.setOnClickListener(this);
            popInfoView_type = (EditText) popInfoView.findViewById(R.id.pass_popview_type);
            popInfoView_Name = (EditText) popInfoView.findViewById(R.id.pass_popview_name);
            popInfoView_Pass = (EditText) popInfoView.findViewById(R.id.pass_popview_pass);
        }

        if(pm!=null) {
            popInfoView_type.setText(pm.getType());
            popInfoView_Name.setText(pm.getName());
            try {
                popInfoView_Pass.setText(CipherUtils.decrypt(pm.getPassword(), CipherUtils.getDESKey(AccountController.getInst().getCipherKey().getBytes()), "DES"));
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity(),"密码解析错误",Toast.LENGTH_SHORT).show();
                return;
            }
        }else{
            popInfoView_type.setText("");
            popInfoView_Name.setText("");
            popInfoView_Pass.setText("");
        }
        if(inEdit){
            popInfoView_type.setEnabled(true);
            popInfoView_Name.setEnabled(true);
            popInfoView_Pass.setEnabled(true);
            popInfoView_Submit.setVisibility(View.VISIBLE);
            popInfoView_Cancel.setVisibility(View.VISIBLE);
        }else{
            popInfoView_type.setEnabled(false);
            popInfoView_Name.setEnabled(false);
            popInfoView_Pass.setEnabled(false);
            popInfoView_Submit.setVisibility(View.GONE);
            popInfoView_Cancel.setVisibility(View.GONE);
        }
        BasePopupManager.getInst().addPopup(popInfoView, getActivity().getWindow(), Gravity.BOTTOM,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void showPopInputView() {
        if(popInputView==null) {
            popInputView = LayoutInflater.from(getActivity()).inflate(R.layout.passmanage_popinputkey, null);
        }
        ((EditText)popInputView.findViewById(R.id.pass_inputkey)).setText("");
        popInputView.findViewById(R.id.pass_inputkey_submit).setOnClickListener(this);
        popInputView.findViewById(R.id.pass_inputkey_cancel).setOnClickListener(this);
        BasePopupManager.getInst().addPopup(popInputView, getActivity().getWindow(), Gravity.BOTTOM,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.pass_inputkey_submit){
            String inputKey = ((EditText)popInputView.findViewById(R.id.pass_inputkey)).getText().toString();
            if(TextUtils.isEmpty(inputKey)){
                Toast.makeText(getActivity(),"未输入任何密码",Toast.LENGTH_SHORT).show();
                return;
            }
            if(inputKey.length()<10){
                Toast.makeText(getActivity(),"口令须在10位以上",Toast.LENGTH_SHORT).show();
                return;
            }
            AccountController.getInst().setCipherKey(inputKey);
            BasePopupManager.getInst().removePop(popInputView);
            showInputView();
        }else if(v.getId()==R.id.pass_inputkey_cancel){
            BasePopupManager.getInst().removePop(popInputView);
        }else if(v.getId()==R.id.pass_popview_submit){
            String pass = popInfoView_Pass.getText().toString();
            try {
                pass = CipherUtils.encrypt(pass, AccountController.getInst().getKey(),"DES");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(pm!=null) {
                pm.setType(popInfoView_type.getText().toString());
                pm.setName(popInfoView_Name.getText().toString());
                pm.setPassword(pass);
                AccountController.getInst().edit(pm,pmPos);
            }else{
                pm = new AccountModel();
                pm.setId(System.currentTimeMillis()+"");
                pm.setName(popInfoView_Name.getText().toString());
                pm.setPassword(pass);
                pm.setType(popInfoView_type.getText().toString());
                AccountController.getInst().add(pm);
            }
            BasePopupManager.getInst().removePop(popInfoView);
        }else if(v.getId()==R.id.pass_popview_cancel) {
            BasePopupManager.getInst().removePop(popInfoView);
        }
    }
}
