package com.xiaolu.dzsdk.base.util;

import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by Roye on 2018/4/3.
 */

public class L {

    static final String TAG = "DZSDK";
    static boolean isDebug;

    public static void setDebug(boolean isDebug) {
        L.isDebug = isDebug;
    }

    public static int print(String methodName, Object... params) {
        if (!isDebug) {
            return -1;
        }
        Class[] cls = new Class[params.length];
        for (int idx = 0; idx < params.length; idx++) {
            cls[idx] = params[idx].getClass();
            if (Throwable.class.isAssignableFrom(cls[idx])) {
                cls[idx] = Throwable.class;
            }
        }
        try {
            Method method = Log.class.getMethod(methodName, cls);
            return (int) method.invoke(L.class, params);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getMethodName() {
        String method = "";
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (int i = 2; i < elements.length; i++) {
            if (elements[i].getMethodName().length() < 2) {
                method = elements[i].getMethodName();
                break;
            }
        }
        return method;
    }

    public static int v(String tag, String msg) {
        return print(getMethodName(), tag, msg);
    }

    public static int v(String tag, String msg, Throwable tr) {
        return print(getMethodName(), tag, msg, tr);
    }

    public static int d(String tag, String msg) {
        return print(getMethodName(), tag, msg);
    }

    public static int d(String tag, String msg, Throwable tr) {
        return print(getMethodName(), tag, msg, tr);
    }

    public static int i(String tag, String msg) {
        return print(getMethodName(), tag, msg);
    }

    public static int i(String tag, String msg, Throwable tr) {
        return print(getMethodName(), tag, msg, tr);
    }

    public static int w(String tag, String msg) {
        return print(getMethodName(), tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return print(getMethodName(), tag, msg, tr);
    }

    /**
     * default tag
     */
    public static int v(String msg) {
        return print(getMethodName(), TAG, msg);
    }

    public static int v(String msg, Throwable tr) {
        return print(getMethodName(), TAG, msg, tr);
    }

    public static int d(String msg) {
        return print(getMethodName(), TAG, msg);
    }

    public static int d(String msg, Throwable tr) {
        return print(getMethodName(), TAG, msg, tr);
    }

    public static int i(String msg) {
        return print(getMethodName(), TAG, msg);
    }

    public static int i(String msg, Throwable tr) {
        return print(getMethodName(), TAG, msg, tr);
    }

    public static int w(String msg) {
        return print(getMethodName(), TAG, msg);
    }

    public static int w(String msg, Throwable tr) {
        return print(getMethodName(), TAG, msg, tr);
    }

    public static int e(String msg) {
        return print(getMethodName(), TAG, msg);
    }

    public static int e(String msg, Throwable tr) {
        return print(getMethodName(), TAG, msg, tr);
    }

}
