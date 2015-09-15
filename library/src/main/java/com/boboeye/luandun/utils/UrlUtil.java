package com.boboeye.luandun.utils;

import android.net.Uri;

/**
 * Created by libo_591 on 15/9/15.
 */
public class UrlUtil {
    public static String getFaviconIconUrl(String url){
        Uri uri = Uri.parse(url);
        String result = uri.getScheme()+"://"+uri.getHost();
        if(uri.getPort()>0){
            result+=":"+uri.getPort();
        }
        result+="/favicon.ico";
        return result;

    }
}
