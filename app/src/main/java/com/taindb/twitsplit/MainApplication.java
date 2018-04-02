package com.taindb.twitsplit;

import android.app.Application;

/**
 * Copyright (C) 2017, Taindb.
 *
 * @author taindb
 * @since 4/1/18
 */

public class MainApplication extends Application {
    private static volatile MainApplication sInstance = null;

    public static MainApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
