package com.xiaolu.dzsdk.net;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.xiaolu.dzsdk.base.util.L;
import com.xiaolu.dzsdk.common.SDKContext;
import com.xiaolu.dzsdk.net.msg.SendMessage;

/**
 * Created by Roye on 2018/4/2.
 */

public class XLWsClient {

    private static XLWsClient mInstance;
    XLDzService mService;
    boolean isBinding;

    public static XLWsClient getInstance() {
        if (mInstance == null) {
            synchronized (XLWsClient.class) {
                if (mInstance == null) {
                    mInstance = new XLWsClient();
                }
            }
        }

        mInstance.bindService();

        return mInstance;
    }

    ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            isBinding = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isBinding = false;
            XLDzService.LocalBinder localBinder = (XLDzService.LocalBinder) service;
            mService = localBinder.getService();
        }

    };

    public void bindService() {
        if (mService != null || isBinding) {
            return;
        }
        isBinding = true;

        SDKContext.getApp().bindService(new Intent(SDKContext.getApp(), XLDzService.class), connection, Service.BIND_AUTO_CREATE);
    }

    public void sendMassage(SendMessage message) {
        if (mService == null) {
            L.d("ready to bindService, please wait...");
            mInstance.bindService();
            return;
        }
        mService.sendMessage(message);
    }

}
