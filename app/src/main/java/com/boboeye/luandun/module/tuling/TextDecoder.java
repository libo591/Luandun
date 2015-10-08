package com.boboeye.luandun.module.tuling;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by libo_591 on 15/9/30.
 */
public class TextDecoder implements TulingDecoder {

    @Override
    public String decode(JSONObject tulingAnswer) {
        return tulingAnswer.getString("text");
    }
}
