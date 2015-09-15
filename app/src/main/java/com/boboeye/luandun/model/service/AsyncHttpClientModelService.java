package com.boboeye.luandun.model.service;

import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.base.BaseNetWorkModelService;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

/**
 * Created by libo_591 on 15/7/26.
 */
public class AsyncHttpClientModelService extends BaseNetWorkModelService {
    //================must implements=================
    private AsyncHttpResponseHandler mAsynHttpResponseHandler;

    /**
     * 发出网络请求后，响应回调的 AsyncHttpResponseHandler 类的获取函数
     * @return AsyncHttpResponseHandler
     */
    protected AsyncHttpResponseHandler getHttpResponseHandler(){
        return null;
    }
    //================must implements=================
    private AsyncHttpClient mClient = new AsyncHttpClient();
    //=======create==
    public int createData(List<NameValuePair> pairs){
        createData(getCreateUrl(),pairs);
        return 0;
    }
    public int createData(String url,List<NameValuePair> pairs){
        reqByHttpClient(url, convertPairs2RequestParams(pairs));
        return 0;
    }
    public int createDataList(List<NameValuePair> pairs){
        createDataList(getCreateListUrl(), pairs);
        return 0;
    }
    public int createDataList(String url,List<NameValuePair> pairs){
        reqByHttpClient(url, convertPairs2RequestParams(pairs));
        return 0;
    }
    //=======create==
    //=======delete==
    public int delData(List<NameValuePair> pairs){
        delData(getDeleteUrl(), pairs);
        return 0;
    }
    public int delData(String url,List<NameValuePair> pairs){
        reqByHttpClient(url, convertPairs2RequestParams(pairs));
        return 0;
    }
    public int delDataList(List<NameValuePair> pairs){
        delDataList(getDeleteListUrl(), pairs);
        return 0;
    }
    public int delDataList(String url,List<NameValuePair> pairs){
        reqByHttpClient(getDeleteListUrl(), convertPairs2RequestParams(pairs));
        return 0;
    }
    //=======delete==
    //=======update==
    public int updateData(List<NameValuePair> pairs){
        updateData(getUpdateUrl(), pairs);
        return 0;
    }
    public int updateData(String url,List<NameValuePair> pairs){
        reqByHttpClient(url, convertPairs2RequestParams(pairs));
        return 0;
    }
    public int updateDataList(List<NameValuePair> pairs){
        updateDataList(getUpdateListUrl(), pairs);
        return 0;
    }
    public int updateDataList(String url,List<NameValuePair> pairs){
        reqByHttpClient(url, convertPairs2RequestParams(pairs));
        return 0;
    }
    //=======update==
    //=======refer===
    public BaseModel referData(List<NameValuePair> pairs){
        referData(getReferUrl(), pairs);
        return null;
    }
    public BaseModel referData(String url,List<NameValuePair> pairs){
        reqByHttpClient(url,convertPairs2RequestParams(pairs));
        return null;
    }
    public List<BaseModel> referDatas(List<NameValuePair> pairs){
        referDatas(getReferListUrl(),pairs);
        return null;
    }
    public List<BaseModel> referDatas(String url,List<NameValuePair> pairs){
        reqByHttpClient(url,convertPairs2RequestParams(pairs));
        return null;
    }
    //=======refer===
    private AsyncHttpResponseHandler fetchInstanceJsonHandler(){
        if(mAsynHttpResponseHandler==null){
            mAsynHttpResponseHandler = getHttpResponseHandler();
        }
        return mAsynHttpResponseHandler;
    }

    private void reqByHttpClient(String url,RequestParams params){
        if(params==null){
            params = new RequestParams();
        }
        mClient.post(url,params,fetchInstanceJsonHandler());
    }

    private RequestParams convertPairs2RequestParams(List<NameValuePair> pairs){
        RequestParams rp = new RequestParams();
        if(pairs!=null&&pairs.size()>0){
            int size = pairs.size();
            for(int i=0;i<size;i++){
                BasicNameValuePair pair = (BasicNameValuePair)pairs.get(i);
                rp.add(pair.getName(),pair.getValue());
            }
        }
        return rp;
    }
}
