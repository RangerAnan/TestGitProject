package com.test.mi.testproject;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by fcl on 2017/11/18
 * desc:
 */

public class GloablApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

    }
}
