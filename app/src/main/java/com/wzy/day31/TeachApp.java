package com.wzy.day31;

import android.app.Application;

import com.wzy.day31.http.HttpUtil;

/**
 * Created by zy on 2016/3/28.
 * 创建一个类,继承自Application
 * 注册我们的Application
 */
public class TeachApp extends Application {
    /**
     * 创建回调,在这里面一般写一些全局工具类的初始化
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化HttpUtil
        HttpUtil.init(this);
    }
}
