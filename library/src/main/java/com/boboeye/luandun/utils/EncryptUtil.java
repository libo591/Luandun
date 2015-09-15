package com.boboeye.luandun.utils;


import org.kymjs.kjframe.utils.CipherUtils;

import java.security.Key;

/**
 * Created by libo_591 on 15/7/27.
 */
public class EncryptUtil {
    private static byte[] keys = "".getBytes();
    public static String encodeUrlParam(String param){
        String value="";
        try {
            Key key = CipherUtils.getDESKey(keys);
            value = CipherUtils.encrypt(param,key,"DES");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
