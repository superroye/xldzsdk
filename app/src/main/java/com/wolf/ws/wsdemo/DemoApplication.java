package com.wolf.ws.wsdemo;

import android.app.Application;
import com.xiaolu.dzsdk.sdk.XLDzApi;

/**
 * Created by Roye on 2018/4/3.
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        XLDzApi.init(this, "10000");
    }
}
