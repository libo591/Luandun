package com.boboeye.luandun.fragments;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseFragment;
import com.boboeye.luandun.base.BasePopupManager;
import com.boboeye.luandun.controller.NetController;
import com.boboeye.luandun.event.NetEvent;
import com.boboeye.luandun.model.impl.NetModel;

import net.i2p.android.ext.floatingactionbutton.FloatingActionButton;

import org.kymjs.kjframe.utils.CipherUtils;

import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/8/17.
 */
public class BrowseWebfragment extends BaseFragment implements View.OnClickListener {
    private static BrowseWebfragment _inst = new BrowseWebfragment();
    public static BrowseWebfragment getInst(String url){
        _inst.url = url;
        return _inst;
    }
    private WebView browseweb_webview;

    private String url;
    @Override
    public int getContentLayout() {
        return R.layout.fragment_browseweb;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);

        browseweb_webview = (WebView)view.findViewById(R.id.browseweb_webview);
        browseweb_webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        FloatingActionButton backBtn = (FloatingActionButton)view.findViewById(R.id.browseweb_fab_back);
        backBtn.setOnClickListener(this);
        FloatingActionButton nextBtn = (FloatingActionButton)view.findViewById(R.id.browseweb_fab_next);
        nextBtn.setOnClickListener(this);
        FloatingActionButton addBtn = (FloatingActionButton)view.findViewById(R.id.browseweb_fab_add);
        addBtn.setOnClickListener(this);
        FloatingActionButton returnmainBtn = (FloatingActionButton)view.findViewById(R.id.browseweb_fab_returnmain);
        returnmainBtn.setOnClickListener(this);
    }

    @Override
    public void initDatas() {
        super.initDatas();
        browseweb_webview.loadUrl(this.url);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.browseweb_fab_back){
            if(browseweb_webview.canGoBack()){
                browseweb_webview.goBack();
            }
        }else if(v.getId()==R.id.browseweb_fab_next){
            if(browseweb_webview.canGoForward()){
                browseweb_webview.goForward();
            }
        }else if(v.getId()==R.id.browseweb_fab_add){
            String url = browseweb_webview.getUrl();

            View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.popup_netitemview, null);
            BasePopupManager.addPopup(view,getActivity().getWindow());
            ((EditText)view.findViewById(R.id.netmodelview_title)).setText(browseweb_webview.getTitle());
            ((EditText)view.findViewById(R.id.netmodelview_url)).setText(url);
            ((Button)view.findViewById(R.id.netmodelview_edit)).setOnClickListener(this);
            ((Button)view.findViewById(R.id.netmodelview_cancel)).setOnClickListener(this);
        }else if(v.getId()==R.id.browseweb_fab_returnmain){
            getFragmentManager().popBackStack();
            MyNetManageFragment.getInst().onRefresh();
        }else if(v.getId()==R.id.netmodelview_cancel){
            BasePopupManager.removeAllPops();
        }else if(v.getId()==R.id.netmodelview_edit){
            String url = browseweb_webview.getUrl();
            NetModel netmodel = new NetModel();
            netmodel.setIcon("");//browseweb_webview.getFavicon());
            netmodel.setSort(1);
            netmodel.setUrl(url);
            netmodel.setTitle(browseweb_webview.getTitle());
            netmodel.setId(CipherUtils.md5(url));
            NetController.getInst().add(netmodel);
            BasePopupManager.removeAllPops();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if (browseweb_webview.canGoBack()) {
                browseweb_webview.goBack();
            } else {
                browseweb_webview.loadUrl("about:blank");
            }
            return true;
        }
        return false;
    }

    @Subscribe
    public void onEventMainThread(NetEvent ne){
        if(ne.getType()==NetEvent.TYPE_ADD){
            String msg = "添加成功";
            Toast.makeText(this.getActivity(),msg,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public BaseController getController() {
        return NetController.getInst();
    }
}
