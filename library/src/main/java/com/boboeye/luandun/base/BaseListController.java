package com.boboeye.luandun.base;


/**
 * Created by libo_591 on 15/8/17.
 */
public class BaseListController extends BaseController {
    private static final String TAG = "BaseListController";
    /** 当前所展示的页的索引 */
    protected int mPageIndex   = -1;
    protected int countPerPage = 15;

    //==================must implement
    protected void requestSomePage(int index,int count){

    }
    //==================must implement
    public void requestNextPage(){
        requestSomePage(++mPageIndex, countPerPage);
    }
    public void refresh(){
        requestPage();
    }
    public void requestPage(){
        requestSomePage(mPageIndex, countPerPage);
    }

    public int getmPageIndex() {
        return mPageIndex;
    }

    public void setmPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }
}
