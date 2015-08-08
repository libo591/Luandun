package com.boboeye.luandun.base;

import org.apache.http.NameValuePair;

import java.io.File;
import java.util.List;

/**
 * Created by libo_591 on 15/7/26.
 */
public class BaseNetWorkModelService {
    //===========must implements==============

    /**
     * 基本的url，最少需具备一个基本的url
     * @return
     */
    public String getBaseUrl(){return null;}
    //===========must implements==============

    public String getCreateUrl(){return getBaseUrl();}
    public String getCreateListUrl(){return getCreateUrl();}

    public String getDeleteUrl(){return getBaseUrl();}
    public String getDeleteListUrl(){return getDeleteUrl();}

    public String getUpdateUrl(){return getBaseUrl();}
    public String getUpdateListUrl(){return getUpdateUrl();}

    public String getReferUrl(){return getBaseUrl();}
    public String getReferListUrl(){return getReferUrl();}

    public String getUploadUrl(){return getBaseUrl();}

    public int createData(List<NameValuePair> pairs){return 0;}
    public int createData(String url,List<NameValuePair> pairs){return 0;}
    public int createDataList(List<NameValuePair> pairs){return 0;}
    public int createDataList(String url,List<NameValuePair> pairs){return 0;}

    public int delData(List<NameValuePair> pairs){return 0;}
    public int delData(String url,List<NameValuePair> pairs){return 0;}
    public int delDataList(List<NameValuePair> pairs){return 0;}
    public int delDataList(String url,List<NameValuePair> pairs){return 0;}

    public int updateData(List<NameValuePair> pairs){return 0;}
    public int updateData(String url,List<NameValuePair> pairs){return 0;}
    public int updateDataList(List<NameValuePair> pairs){return 0;}
    public int updateDataList(String url,List<NameValuePair> pairs){return 0;}

    public BaseModel referData(List<NameValuePair> pairs){return null;}
    public BaseModel referData(String url,List<NameValuePair> pairs){return null;}
    public List<BaseModel> referDatas(List<NameValuePair> pairs){return null;}
    public List<BaseModel> referDatas(String url,List<NameValuePair> pairs){return null;}

    public int uploadFiles(List<NameValuePair> pairs,List<File> files){return 0;}
}
