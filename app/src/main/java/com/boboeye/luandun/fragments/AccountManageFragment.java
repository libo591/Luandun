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
import android.widget.Toast;

import com.boboeye.luandun.R;
import com.boboeye.luandun.adapter.AccountAdapter;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseListAdapter;
import com.boboeye.luandun.base.BaseListFragment;
import com.boboeye.luandun.base.BasePopupManager;
import com.boboeye.luandun.controller.AccountController;
import com.boboeye.luandun.event.AccountEvent;
import com.boboeye.luandun.model.impl.AccountModel;

import org.kymjs.kjframe.utils.CipherUtils;

import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/8/2.
 */
public class AccountManageFragment extends BaseListFragment implements View.OnClickListener {
    private static final String TAG = AccountManageFragment.class.getSimpleName();
    private View popInputView;
    private View popInfoView;
    private EditText popInfoView_Name;
    private EditText popInfoView_Pass;
    private Button popInfoView_Submit;
    private Button popInfoView_Cancel;

    private AccountModel pm;
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
    }

    @Subscribe
    public void onEventMainThread(AccountEvent accountEvent) {
        int et = accountEvent.getType();
        if(et== AccountEvent.TYPE_BASELIST) {
            super.onEventBasic(accountEvent);
        }else if(et== AccountEvent.TYPE_DELETE){
            onRefresh();
            Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_SHORT).show();
        }else if(et== AccountEvent.TYPE_EDIT){
            onRefresh();
            Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
        }else if(et== AccountEvent.TYPE_ADD){
            if(!mView.isShown()){
                return;
            }
            pm = null;
            showOrEditItem(true);
        }else if(et== AccountEvent.SHOW_EDIT){
            int pos = (int) accountEvent.getEventData().get(0);
            pm = (AccountModel)mAdapter.getItem(pos);
            showOrEditItem(true);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick");
        super.onItemClick(parent, view, position, id);
        pm = (AccountModel) mAdapter.getItem(position);
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
            popInfoView_Name = (EditText) popInfoView.findViewById(R.id.pass_popview_name);
            popInfoView_Pass = (EditText) popInfoView.findViewById(R.id.pass_popview_pass);
        }

        if(pm!=null) {
            EditText typeET = (EditText) popInfoView.findViewById(R.id.pass_popview_type);
            //typeET.setText(pm.getType());
            popInfoView_Name.setText(pm.getName());
            try {
                popInfoView_Pass.setText(CipherUtils.decrypt(pm.getPassword(), CipherUtils.getDESKey(AccountController.getInst().getCipherKey().getBytes()), "DES"));
            }catch(Exception e){e.printStackTrace();}
        }else{
            popInfoView_Name.setText("");
            popInfoView_Pass.setText("");
        }
        if(inEdit){
            //typeET.setEnabled(true);
            popInfoView_Name.setEnabled(true);
            popInfoView_Pass.setEnabled(true);
            popInfoView_Submit.setVisibility(View.VISIBLE);
            popInfoView_Cancel.setVisibility(View.VISIBLE);
        }else{
            //typeET.setEnabled(false);
            popInfoView_Name.setEnabled(false);
            popInfoView_Pass.setEnabled(false);
            popInfoView_Submit.setVisibility(View.GONE);
            popInfoView_Cancel.setVisibility(View.GONE);
        }
        BasePopupManager.addPopup(popInfoView, getActivity().getWindow(), Gravity.BOTTOM,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void showPopInputView() {
        if(popInputView==null) {
            popInputView = LayoutInflater.from(getActivity()).inflate(R.layout.passmanage_popinputkey, null);
        }
        ((EditText)popInputView.findViewById(R.id.pass_inputkey)).setText("");
        popInputView.findViewById(R.id.pass_inputkey_submit).setOnClickListener(this);
        popInputView.findViewById(R.id.pass_inputkey_cancel).setOnClickListener(this);
        BasePopupManager.addPopup(popInputView, getActivity().getWindow(), Gravity.BOTTOM,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.pass_inputkey_submit){
            String inputKey = ((EditText)popInputView.findViewById(R.id.pass_inputkey)).getText().toString();
            AccountController.getInst().setCipherKey(inputKey);
            BasePopupManager.removePop(popInputView);
            showInputView();
        }else if(v.getId()==R.id.pass_inputkey_cancel){
            BasePopupManager.removePop(popInputView);
        }else if(v.getId()==R.id.pass_popview_submit){
            String pass = popInfoView_Pass.getText().toString();
            try {
                pass = CipherUtils.encrypt(pass, AccountController.getInst().getKey(),"DES");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(pm!=null) {
                pm.setName(popInfoView_Name.getText().toString());
                pm.setPassword(pass);
                AccountController.getInst().edit(pm);
            }else{
                pm = new AccountModel();
                pm.setId(System.currentTimeMillis()+"");
                pm.setName(popInfoView_Name.getText().toString());
                pm.setPassword(pass);
                pm.setType(0);
                AccountController.getInst().add(pm);
            }
            BasePopupManager.removePop(popInfoView);
            this.onRefresh();
        }else if(v.getId()==R.id.pass_popview_cancel) {
            BasePopupManager.removePop(popInfoView);
        }
    }
}
