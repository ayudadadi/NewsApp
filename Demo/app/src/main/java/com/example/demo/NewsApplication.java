package com.example.demo;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;

import cn.bmob.v3.Bmob;

public class NewsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        QbSdk.initX5Environment(this,null);
        Bmob.initialize(this, "4edc969b617c81afe4b61e6a2890dec0");
    }
}
