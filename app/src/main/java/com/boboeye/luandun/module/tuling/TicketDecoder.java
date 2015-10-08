package com.boboeye.luandun.module.tuling;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by libo_591 on 15/10/1.
 */
public class TicketDecoder implements TulingDecoder {
    @Override
    public String decode(JSONObject tulingAnswer) {
        StringBuffer result = new StringBuffer();
        String title = tulingAnswer.getString("text");
        result.append(title+"<br />");
        JSONArray list = tulingAnswer.getJSONArray("list");
        int len = list.size();
        for(int i=0;i<len;i++){
            JSONObject newsitem = list.getJSONObject(i);
            result.append(newsitem.getString("trainnum"));
            result.append(" "+newsitem.getString("start"));
            result.append("-"+newsitem.getString("terminal"));
            result.append(" "+newsitem.getString("starttime"));
            result.append("-"+newsitem.getString("endtime"));
            result.append("   <a href='"+newsitem.getString("detailurl")+"'>详情</a>");
            result.append("<br />");
        }
        return result.toString();
    }
}
