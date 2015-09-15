package com.boboeye.luandun.model.service.impl;

import com.boboeye.luandun.model.service.AsyncHttpClientModelService;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

/**
 * Created by libo_591 on 15/7/27.
 */
public class SocialNetModelService extends AsyncHttpClientModelService {

    @Override
    protected AsyncHttpResponseHandler getHttpResponseHandler() {
        return super.getHttpResponseHandler();
    }

    class SocialJsonHttpResponse extends AsyncHttpResponseHandler{
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

        }
    }

}
