package com.boboeye.luandun;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * Created by libo_591 on 15/9/15.
 */
public class MainActivityTest extends android.test.ActivityInstrumentationTestCase2 {
    private static final String pkg = "com.boboeye.luandun";
    private static final String ac_class = "com.boboeye.luandun.activitys.HomeActivity";

    private Solo solo;
    private static Class clz;
    static{
        try {
            clz = Class.forName(ac_class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public MainActivityTest(){
        super(pkg, clz);
    }

    @Override
    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(),getActivity());
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void test1_animation(){
        solo.waitForActivity("HomeActivity");
        solo.assertCurrentActivity("动画跳转", "HomeActivity");
    }
    public void test2_mainpage(){
        boolean hasWebSite = solo.searchText("网站");
        boolean hasPhoneManage = solo.searchText("手机管理");
        assertTrue("主页面文字", hasWebSite && hasPhoneManage);
    }

    public void test3_menu(){
        View imagebutton = solo.getImageButton(0);
        solo.clickOnView(imagebutton);
        boolean hasDis = solo.searchText("发现");
        boolean hasSettings = solo.searchText("设置");
        assertTrue("菜单文字", hasDis && hasSettings);

        View disview = solo.getView(R.id.menu_dis);
        solo.clickOnView(disview);
        solo.waitForActivity("WebViewActivity");
        solo.assertCurrentActivity("发现点击异常", "WebViewActivity");
        View view = solo.getView(R.id.fab_expand_menu_button);
        assertNotNull("WebViewActivity没有找到浮动按钮", view);

        solo.goBackToActivity("HomeActivity");
        solo.waitForActivity("HomeActivity");
        solo.assertCurrentActivity("发现回退点击异常", "HomeActivity");

        View setiew = solo.getView(R.id.menu_set);
        solo.clickOnView(setiew);
        solo.waitForActivity("SettingsActivity");
        solo.assertCurrentActivity("设置点击异常", "SettingsActivity");
        boolean hasSet = solo.searchText("设置");
        assertTrue("SettingsActivity 没有找到设置文本", hasSet);

        View back2 = solo.getImageButton(0);
        solo.clickOnView(back2);
        solo.waitForActivity("HomeActivity");
        solo.assertCurrentActivity("设置回退点击异常", "HomeActivity");
    }

    public void test4_viewpager(){
        View phoneTextview = solo.getView(R.id.tabstrip_title, 1);
        solo.clickOnView(phoneTextview);
        boolean hasProcess = solo.searchText("进程监控");
        assertTrue("viewpager切换不正常1", hasProcess);

        View passTextview = solo.getView(R.id.tabstrip_title, 2);
        solo.clickOnView(passTextview);

        View websiteTextview = solo.getView(R.id.tabstrip_title, 0);
        solo.clickOnView(websiteTextview);
        boolean hasBaidu = solo.searchText("百度");

        assertTrue("viewpager切换不正常3",hasBaidu);
    }

    public void test5_websitecurd(){
//        View addview = solo.getView(R.id.actionbar_add);
//        solo.clickOnView(addview);
//
//        EditText title = solo.getEditText(0);
//        title.setText("aaaaa");
//
//        EditText urledittext = solo.getEditText(1);
//        urledittext.setText("http://www.baidu.com");
//
//        Button surebutton = solo.getButton("确定");
//        solo.clickOnView(surebutton);
//
//        boolean hasAdded = solo.searchText("aaaaa");
//        assertTrue("添加网站失败",hasAdded);
    }

    public void test6_processmanage(){

    }

    public void test7_accountcurd(){

    }
}
