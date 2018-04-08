package com.xiaolu.dzsdk.common;

import android.app.Application;

/**
 * Created by Roye on 2018/4/2.
 */

public class SDKContext {

    public static final String TAG ="XLSDK";

    private static Application app;

    public static String ENV;

    public static boolean DEBUG;

    public static void setApp(Application app) {
        SDKContext.app = app;
    }

    public static Application getApp() {
        if (app == null) {
            new NullPointerException("SDKContext.app is not inited!");
        }
        return app;
    }


}
