package com.base.base.utilcode.util;

import android.os.Handler;
import android.os.Looper;

public class MainHandlerUtils {
    private static Handler mHandler;

    public static Handler getMainHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }
}
