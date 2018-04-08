package com.xiaolu.dzsdk.base.util;

import android.os.Handler;
import android.os.Looper;

public class UIRunner {

    static Handler mHandler;

    public static void runOnUI(Runnable runnable) {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        mHandler.post(runnable);
    }

}