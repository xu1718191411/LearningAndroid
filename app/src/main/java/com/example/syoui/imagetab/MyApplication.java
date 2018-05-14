package com.example.syoui.imagetab;

import android.app.Application;

import com.twitter.sdk.android.core.Twitter;

/**
 * Created by syoui on 2018/04/20.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Twitter.initialize(this);

    }



}
