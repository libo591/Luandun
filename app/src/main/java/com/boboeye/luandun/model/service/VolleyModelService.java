package com.boboeye.luandun.model.service;

import android.support.v4.util.SimpleArrayMap;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.base.BaseNetWorkModelService;
import com.boboeye.luandun.utils.SystemTool;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by libo_591 on 15/9/30.
 */
public class VolleyModelService extends BaseNetWorkModelService {
    //===============must implements=========================
    public Response.Listener getResponseLis(){
        return new Response.Listener() {
            @Override
            public void onResponse(Object response) {

            }
        };
    }
    public Response.ErrorListener getResponseErrorLis(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
    }

    public void reqByVolley(String url,List<NameValuePair> pairs,SimpleArrayMap<String,String> heads){
        if(!SystemTool.checkNet(AppConfig.getInst().getContext())){
            return;
        }
    }
    protected RequestQueue mQueue;
    //===============must implements=========================
    public VolleyModelService(RequestQueue q){
        this.mQueue = q;
    }
    //=======create==
    public int createData(List<NameValuePair> pairs){
        createData(getCreateUrl(), pairs);
        return 0;
    }
    public int createData(String url,List<NameValuePair> pairs){
        reqByVolley(url, pairs, null);
        return 0;
    }
    public int createDataList(List<NameValuePair> pairs){
        createDataList(getCreateListUrl(), pairs);
        return 0;
    }
    public int createDataList(String url,List<NameValuePair> pairs){
        reqByVolley(url, pairs, null);
        return 0;
    }
    //=======create==
    //=======delete==
    public int delData(List<NameValuePair> pairs){
        delData(getDeleteUrl(), pairs);
        return 0;
    }
    public int delData(String url,List<NameValuePair> pairs){
        reqByVolley(url, pairs, null);
        return 0;
    }
    public int delDataList(List<NameValuePair> pairs){
        delDataList(getDeleteListUrl(), pairs);
        return 0;
    }
    public int delDataList(String url,List<NameValuePair> pairs){
        reqByVolley(getDeleteListUrl(), pairs, null);
        return 0;
    }
    //=======delete==
    //=======update==
    public int updateData(List<NameValuePair> pairs){
        updateData(getUpdateUrl(), pairs);
        return 0;
    }
    public int updateData(String url,List<NameValuePair> pairs){
        reqByVolley(url, pairs, null);
        return 0;
    }
    public int updateDataList(List<NameValuePair> pairs){
        updateDataList(getUpdateListUrl(), pairs);
        return 0;
    }
    public int updateDataList(String url,List<NameValuePair> pairs){
        reqByVolley(url, pairs, null);
        return 0;
    }
    //=======update==
    //=======refer===
    public BaseModel referData(List<NameValuePair> pairs){
        referData(getReferUrl(), pairs);
        return null;
    }
    public BaseModel referData(String url,List<NameValuePair> pairs){
        reqByVolley(url, pairs, null);
        return null;
    }
    public List<BaseModel> referDatas(List<NameValuePair> pairs){
        referDatas(getReferListUrl(), pairs, null);
        return null;
    }
    public List<BaseModel> referDatas(String url,List<NameValuePair> pairs){
        reqByVolley(url, pairs, null);
        return null;
    }
    public List<BaseModel> referDatas(String url,List<NameValuePair> pairs,SimpleArrayMap<String,String> heads){
        reqByVolley(url, pairs, heads);
        return null;
    }
    //=======refer===
}
