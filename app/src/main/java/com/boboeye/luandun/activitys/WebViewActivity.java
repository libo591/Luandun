package com.boboeye.luandun.activitys;

import android.graphics.Bitmap;
import android.graphics.drawable.ClipDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.boboeye.luandun.LuanApplication;
import com.boboeye.luandun.R;
import com.boboeye.luandun.base.BaseBackActivity;
import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BasePopupManager;
import com.boboeye.luandun.controller.WebSiteController;
import com.boboeye.luandun.event.WebSiteEvent;
import com.boboeye.luandun.model.impl.WebSiteModel;
import com.boboeye.luandun.utils.CipherUtils;
import com.squareup.leakcanary.RefWatcher;

import net.i2p.android.ext.floatingactionbutton.FloatingActionButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.Subscribe;

/**
 * Created by libo_591 on 15/9/5.
 */
public class WebViewActivity extends BaseBackActivity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = WebViewActivity.class.getSimpleName();
    @InjectView(R.id.browseweb_webview)
    public WebView browseweb_webview;

    private View popView;
    @InjectView(R.id.webview_refresh)
    public SwipeRefreshLayout webview_refresh;

    @InjectView(R.id.process_bar)
    public View processBar;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_browseweb;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        ButterKnife.inject(this);
        //browseweb_webview.setInitialScale(100);
        browseweb_webview.getSettings().setJavaScriptEnabled(true);
        browseweb_webview.getSettings().setSupportZoom(true);
        browseweb_webview.getSettings().setBuiltInZoomControls(true);
        browseweb_webview.getSettings().setUseWideViewPort(true);
        browseweb_webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        browseweb_webview.getSettings().setLoadWithOverviewMode(true);
        browseweb_webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }



            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                processBar.setVisibility(View.VISIBLE);
                ClipDrawable clip = (ClipDrawable) processBar.getBackground();
                clip.setLevel(0);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webview_refresh.setRefreshing(false);
                    }
                }, 100);
                processBar.setVisibility(View.GONE);
            }
        });
        browseweb_webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                WebViewActivity.this.getSupportActionBar().setTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100) {
                    processBar.setVisibility(View.GONE);
                }else{
                    ClipDrawable clip = (ClipDrawable) processBar.getBackground();
                    clip.setLevel(newProgress*100);
                }
                super.onProgressChanged(view,newProgress);
            }
        });
        FloatingActionButton backBtn = (FloatingActionButton)findViewById(R.id.browseweb_fab_back);
        backBtn.setOnClickListener(this);
        FloatingActionButton nextBtn = (FloatingActionButton)findViewById(R.id.browseweb_fab_next);
        nextBtn.setOnClickListener(this);
        FloatingActionButton addBtn = (FloatingActionButton)findViewById(R.id.browseweb_fab_add);
        addBtn.setOnClickListener(this);

        webview_refresh.setColorSchemeColors(R.color.day_colorPrimary);
        webview_refresh.setOnRefreshListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String url = "";
        if(bundle!=null) {
            url = bundle.getString("url");
        }
        if(TextUtils.isEmpty(url)){
            Uri uri = getIntent().getData();
            url = uri.getQueryParameter("url");
        }

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
            BasePopupManager.getInst().addPopup(popView, this.getWindow(), Gravity.BOTTOM, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }else if(v.getId()==R.id.netmodelview_cancel){
            BasePopupManager.getInst().removeAllPops();
        }else if(v.getId()==R.id.netmodelview_edit){
            String url = browseweb_webview.getUrl();
            WebSiteModel netmodel = new WebSiteModel();
            netmodel.setIcon("");//browseweb_webview.getFavicon());
            netmodel.setSort(1);
            netmodel.setUrl(url);
            netmodel.setTitle(browseweb_webview.getTitle());
            netmodel.setId(CipherUtils.md5(url));
            WebSiteController.getInst().add(netmodel);
            BasePopupManager.getInst().removeAllPops();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if (browseweb_webview.canGoBack()) {
                browseweb_webview.goBack();
            } else {
                return super.onKeyDown(keyCode,event);
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Subscribe
    public void onEventMainThread(WebSiteEvent ne){
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

    @Override
    public void onRefresh() {
        browseweb_webview.reload();
    }
}
