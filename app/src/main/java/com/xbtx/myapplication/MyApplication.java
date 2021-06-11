package com.xbtx.myapplication;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;


/**
 * @author: 宁
 * @className:
 * @date: 2021/6/9 15:55
 */
public class MyApplication extends Application {
    private boolean isDebugARouter = true;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebugARouter) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);

    }
}