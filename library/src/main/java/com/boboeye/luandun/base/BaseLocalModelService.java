package com.boboeye.luandun.base;

import android.os.Environment;

import com.boboeye.luandun.AppConfig;
import com.boboeye.luandun.utils.FileUtils;


import java.util.List;

/**
 * Created by libo_591 on 15/7/26.
 */
public class BaseLocalModelService {
    public String getBaseFilePath(){
        if(FileUtils.checkSDcard()){
            return FileUtils.getSDCardPath()+"/luandun/userdata";
        }else{
            return Environment.getDataDirectory()
                    +"/data/"+AppConfig.getInst().getContext().getPackageName()+"/userdata";
        }
    }

    public String getCreateFilePath(){return getBaseFilePath();}
    public String getCreateListFilePath(){return getCreateFilePath();}

    public String getDeleteFilePath(){return getBaseFilePath();}
    public String getDeleteListFilePath(){return getDeleteFilePath();}

    public String getUpdateFilePath(){return getBaseFilePath();}
    public String getUpdateListFilePath(){return getUpdateFilePath();}

    public String getReferFilePath(){return getBaseFilePath();}
    public String getReferListFilePath(){return getReferFilePath();}

    public String getUploadFilePath(){return getBaseFilePath();}


    public int createData(BaseModel data){return 0;}
    public int createDataList(List<BaseModel> datas){return 0;}
    public int updateData(BaseModel data){return 0;}
    public int updateDataList(List<BaseModel> datas){return 0;}
    public int delData(BaseModel data){return 0;}
    public int delDataList(List<BaseModel> datas){return 0;}
    public List<BaseModel> referDatas(){return null;}


}
