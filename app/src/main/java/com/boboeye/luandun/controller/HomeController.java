package com.boboeye.luandun.controller;

import com.boboeye.luandun.base.BaseController;

/**
 * Created by libo_591 on 15/8/17.
 */
public class HomeController extends BaseController {
    private static HomeController _inst = new HomeController();
    private HomeController(){}
    public static HomeController getInst(){
        return _inst;
    }

    public void add(){

    }
}
