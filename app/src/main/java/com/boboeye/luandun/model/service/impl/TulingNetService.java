package com.boboeye.luandun.model.service.impl;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.boboeye.luandun.controller.Tuling123Controller;
import com.boboeye.luandun.model.impl.TulingModel;
import com.boboeye.luandun.model.service.AsyncHttpClientModelService;
import com.boboeye.luandun.module.tuling.TulingDecoder;
import com.boboeye.luandun.module.tuling.TulingDecoderFactory;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

import org.apache.http.Header;

/**
 * Created by libo_591 on 15/9/22.
 */
public class TulingNetService extends AsyncHttpClientModelService {
    private static final String TAG = TulingNetService.class.getSimpleName();

    @Override
    protected AsyncHttpResponseHandler getHttpResponseHandler() {
        return new BaseJsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                JSONObject obj = JSONObject.parseObject(rawJsonResponse);
                TulingDecoder decoder = TulingDecoderFactory.createDecoder(obj.getIntValue("code"));
                String resp = decoder.decode(obj);
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
