package com.boboeye.luandun.model.service;

import android.graphics.Bitmap;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import org.apache.http.NameValuePair;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by libo_591 on 15/9/30.
 */
public class ImageVolleyModelService extends VolleyModelService
        implements Response.Listener<Bitmap>,Response.ErrorListener {
    private WeakReference<ImageView> ref;
    private ImageView.ScaleType scaleType;
    private int maxWidth;
    private int maxHeight;

    public ImageVolleyModelService(RequestQueue q) {
        super(q);
    }

    public void setImageParams(ImageView view,
                               ImageView.ScaleType scaleType,int maxWidth,int maxHeight){
        this.ref = new WeakReference<ImageView>(view);
        this.scaleType = scaleType;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }
    @Override
    public void reqByVolley(String url, List<NameValuePair> pairs, SimpleArrayMap<String, String> heads) {
        super.reqByVolley(url,pairs,heads);
        ImageRequest imageReq = new ImageRequest(url,getResponseLis(),maxWidth,maxHeight,scaleType,Bitmap.Config.ARGB_8888,getResponseErrorLis());
        mQueue.add(imageReq);
    }

    @Override
    public Response.Listener getResponseLis() {
        return this;
    }

    @Override
    public Response.ErrorListener getResponseErrorLis() {
        return this;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        ImageView image = ref.get();
        if(image!=null) {
            image.setImageBitmap(null);
        }
    }

    @Override
    public void onResponse(Bitmap bitmap) {
        ImageView image = ref.get();
        if(image!=null) {
            image.setImageBitmap(bitmap);
        }
    }
}
