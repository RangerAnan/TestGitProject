package com.test.mi.testproject;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by fcl on 2017/11/18
 * desc:
 */

public class GlobalApplication extends Application {

    private static GlobalApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        Stetho.initializeWithDefaults(this);


    }


    public static GlobalApplication getContext() {
        return context;
    }
}
