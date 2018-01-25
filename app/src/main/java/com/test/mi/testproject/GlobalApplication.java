package com.test.mi.testproject;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.stetho.Stetho;
import com.qsmaxmin.qsbase.QsApplication;
import com.qsmaxmin.qsbase.common.http.HttpBuilder;

/**
 * Created by fcl on 2017/11/18
 * desc:
 */

public class GlobalApplication extends QsApplication {

    private static GlobalApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        Stetho.initializeWithDefaults(this);

        ARouter.init(this);
    }

    @Override
    public boolean isLogOpen() {
        return false;
    }

    @Override
    public void initHttpAdapter(HttpBuilder httpBuilder) {
        httpBuilder.setTerminal("https://www.baidu.com");
        httpBuilder.addHeader("Content-Type", "application/json");
        httpBuilder.addHeader("token", "123456");
    }


    public static GlobalApplication getContext() {
        return context;
    }
}
