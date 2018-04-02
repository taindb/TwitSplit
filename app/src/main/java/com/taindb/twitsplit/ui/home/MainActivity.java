package com.taindb.twitsplit.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.taindb.twitsplit.R;
import com.taindb.twitsplit.ui.message.MessageActivity;

/**
 * Copyright (C) 2017, Taindb.
 *
 * @author taindb
 * @since 4/2/18
 */

public class MainActivity extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;

    private final Runnable mStartActivityRunnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.this, MessageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler(getMainLooper()).postDelayed(mStartActivityRunnable, SPLASH_TIME_OUT);
    }
}
