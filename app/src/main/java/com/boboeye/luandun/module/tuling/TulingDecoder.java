package com.boboeye.luandun.module.tuling;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by libo_591 on 15/9/30.
 */
public interface TulingDecoder {
    /**
     * 根据图灵的json返回信息，解析生成html信息
     * @param tulingAnswer
     * @return
     */
    String decode(JSONObject tulingAnswer);
}
