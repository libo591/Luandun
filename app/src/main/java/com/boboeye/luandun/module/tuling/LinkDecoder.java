package com.boboeye.luandun.module.tuling;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by libo_591 on 15/9/30.
 */
public class LinkDecoder implements TulingDecoder {
    @Override
    public String decode(JSONObject tulingAnswer) {
        String text = tulingAnswer.getString("text");
        String url = tulingAnswer.getString("url");
        //boboeye://webview/?url=
        return text+"   <a href='"+url+"'><font color='red'>详情</font></a>";
    }
}
