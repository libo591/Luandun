package com.boboeye.luandun.activitys;

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.boboeye.luandun.LuanApplication;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseBackActivity;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BasePopupManager;
import com.boboeye.luandun.controller.WebSiteController;
import com.boboeye.luandun.event.WebSiteEvent;
import com.boboeye.luandun.model.impl.WebSiteModel;
import com.squareup.leakcanary.RefWatcher;

import net.i2p.android.ext.floatingactionbutton.FloatingActionButton;

import org.kymjs.kjframe.utils.CipherUtils;

import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/9/5.
 */
public class WebViewActivity extends BaseBackActivity implements View.OnClickListener {
    private WebView browseweb_webview;

    private View popView;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_browseweb;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        browseweb_webview = (WebView)findViewById(R.id.browseweb_webview);
        browseweb_webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        browseweb_webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                WebViewActivity.this.getSupportActionBar().setTitle(title);
            }
        });
        FloatingActionButton backBtn = (FloatingActionButton)findViewById(R.id.browseweb_fab_back);
        backBtn.setOnClickListener(this);
        FloatingActionButton nextBtn = (FloatingActionButton)findViewById(R.id.browseweb_fab_next);
        nextBtn.setOnClickListener(this);
        FloatingActionButton addBtn = (FloatingActionButton)findViewById(R.id.browseweb_fab_add);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        browseweb_webview.loadUrl(url);
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
            String title = browseweb_webview.getTitle();
            String url = browseweb_webview.getUrl();
            if(popView==null) {
                popView = LayoutInflater.from(this).inflate(R.layout.popup_netitemview, null);
                ((Button)popView.findViewById(R.id.netmodelview_edit)).setOnClickListener(this);
                ((Button)popView.findViewById(R.id.netmodelview_cancel)).setOnClickListener(this);
            }
            ((EditText)popView.findViewById(R.id.netmodelview_title)).setText(title);
            ((EditText)popView.findViewById(R.id.netmodelview_url)).setText(url);
            BasePopupManager.addPopup(popView, this.getWindow(), Gravity.BOTTOM, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }else if(v.getId()==R.id.netmodelview_cancel){
            BasePopupManager.removeAllPops();
        }else if(v.getId()==R.id.netmodelview_edit){
            String url = browseweb_webview.getUrl();
            WebSiteModel netmodel = new WebSiteModel();
            netmodel.setIcon("");//browseweb_webview.getFavicon());
            netmodel.setSort(1);
            netmodel.setUrl(url);
            netmodel.setTitle(browseweb_webview.getTitle());
            netmodel.setId(CipherUtils.md5(url));
            WebSiteController.getInst().add(netmodel);
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
        return super.onKeyDown(keyCode,event);
    }

    @Subscribe
    public void onEventMainThread(WebSiteEvent ne){
        if(ne.getType()== WebSiteEvent.TYPE_ADD){
            String msg = "添加成功";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public BaseController getController() {
        return WebSiteController.getInst();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = LuanApplication.getLeak(this);
        watcher.watch(this);
    }
}
