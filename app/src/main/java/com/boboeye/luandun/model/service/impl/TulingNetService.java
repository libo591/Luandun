package com.boboeye.luandun.model.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.boboeye.luandun.controller.Tuling123Controller;
import com.boboeye.luandun.model.impl.TulingModel;
import com.boboeye.luandun.model.service.AsyncHttpClientModelService;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

import org.apache.http.Header;

/**
 * Created by libo_591 on 15/9/22.
 */
public class TulingNetService extends AsyncHttpClientModelService {
    @Override
    protected AsyncHttpResponseHandler getHttpResponseHandler() {
        return new BaseJsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                JSONObject obj = JSONObject.parseObject(rawJsonResponse);
                String resp = obj.getString("text");
                Tuling123Controller.getInst().addMessage(resp,TulingModel.FROM_TULING);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {

            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return null;
            }
        };
    }

}
