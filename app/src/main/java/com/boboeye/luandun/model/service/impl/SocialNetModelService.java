package com.boboeye.luandun.model.service.impl;

import com.boboeye.luandun.model.service.AsyncHttpClientModelService;
import com.boboeye.luandun.socialopen.WeiBoController;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.util.List;

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
