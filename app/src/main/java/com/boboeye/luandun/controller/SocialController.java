package com.boboeye.luandun.controller;

import android.os.AsyncTask;

import com.boboeye.luandun.base.BaseController;
import com.boboeye.luandun.base.BaseModel;
import com.boboeye.luandun.model.service.impl.SocialLocalModelService;
import com.boboeye.luandun.model.service.impl.SocialNetModelService;
import com.boboeye.luandun.socialopen.WeiBoController;

import org.apache.http.NameValuePair;
import java.util.List;

/**
 * Created by libo_591 on 15/7/26.
 */
public class SocialController extends BaseController {
    private static SocialController _inst = new SocialController();
    private SocialController(){}
    public static SocialController getController(){
        return _inst;
    }

    public void requestPageData(int pageIndex,int countPerPage){
        SocialAsynTask task = new SocialAsynTask();
        task.execute(pageIndex,countPerPage);
    }

    class SocialAsynTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            WeiBoController controller = WeiBoController.getInst();
            if(controller.hasToken()){
                referPage(params);
            }else{
                referToken();
            }
            return null;
        }

        private void referToken(){
            WeiBoController controller = WeiBoController.getInst();
            String url = controller.getAuthRequestUrl();
            List<NameValuePair> params = controller.referAuthRequestParams();
            SocialNetModelService snms = new SocialNetModelService();
            snms.referData(url,params);
        }

        private void referPage(Object[] params){
            int pageIndex = (Integer)params[0];
            int countPerPage = (Integer)params[1];
            SocialLocalModelService slms = new SocialLocalModelService();
            List<BaseModel> localData = slms.referDatas();
            if(localData==null){
                SocialNetModelService snms = new SocialNetModelService();
                String url = WeiBoController.getInst().referWeibolistUrl(pageIndex,countPerPage);
                snms.referDatas(url,null);
            }
        }
    }
}
