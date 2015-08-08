package com.boboeye.luandun.socialopen;

import android.content.SharedPreferences;

import com.boboeye.luandun.base.BaseInfoStore;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by libo_591 on 15/8/3.
 */
public class WeiBoController {
    private final String APPKEY = "925317683";
    private final String APPSECRET = "041e1b44a57b13ce1de961e930ca7566";
    private final String weiboBaseUrl = "https://api.weibo.com";
    private final String OAUTH2URL = "/oauth2";
    private final String WEIBOURL = "/2/statuses/public_timeline.json";

    private final String OAUTHREDIRECT = "https://api.weibo.com/oauth2/default.html";

    private String oauthCode = "";
    private String accessToken="";
    private String uid = "";
    private BaseInfoStore bis = new BaseInfoStore("Social");

    private static WeiBoController _inst = new WeiBoController();
    private WeiBoController(){
        oauthCode = bis.get("oauthCode","");
        accessToken = bis.get("accessToken","");
        uid = bis.get("oauthuid","");
    }
    public static WeiBoController getInst(){
        return _inst;
    }

    /**
     * 请求授权
     * @return
     */
    public String getAuthRequestUrl(){
        return OAUTH2URL+"/authorize";
    }

    public List<NameValuePair> referAuthRequestParams(){
        List<NameValuePair> pairs= new ArrayList<>(2);
        pairs.add(new BasicNameValuePair("client_id",APPKEY));
        pairs.add(new BasicNameValuePair("redirect_uri",OAUTHREDIRECT));
        return pairs;
    }

    /**
     * 获取授权后的token
     * @return
     */
    public String referAccessTokenUrl(){
        return OAUTH2URL+"/access_token";
    }

    public List<NameValuePair> referAccessTokenParams(){
        List<NameValuePair> pairs= new ArrayList<>(2);
        pairs.add(new BasicNameValuePair("client_id",APPKEY));
        pairs.add(new BasicNameValuePair("client_secret",APPSECRET));
        pairs.add(new BasicNameValuePair("grant_type","authorization_code"));
        pairs.add(new BasicNameValuePair("code",oauthCode));
        pairs.add(new BasicNameValuePair("redirect_uri",OAUTHREDIRECT));
        return pairs;
    }

    public String referWeibolistUrl(int page,int count){
        return weiboBaseUrl+WEIBOURL+"?access_token="+accessToken+"&page="+page+"&count="+count;
    }

    public List<NameValuePair> referWeibolistParams(){
        return null;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
        bis.put("oauthuid",uid);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        bis.put("accessToken",accessToken);
    }

    public String getOauthCode() {
        return oauthCode;
    }

    public void setOauthCode(String oauthCode) {
        this.oauthCode = oauthCode;
        bis.put("oauthCode",oauthCode);
    }

    public boolean hasToken(){
        return !"".equals(accessToken);
    }
}
