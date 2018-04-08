package com.xiaolu.dzsdk.net;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xiaolu.dzsdk.base.util.L;
import com.xiaolu.dzsdk.net.msg.SendMessage;

/**
 * Created by Roye on 2018/4/2.
 */

public class XLDzService extends Service {

    private XLWSPresenter presenter;

    private LocalBinder localBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        if (presenter == null) {
            presenter = new XLWSPresenter();
            presenter.init();
        }

        presenter.start();

        return localBinder;
    }

    public synchronized void sendMessage(SendMessage msg) {
        presenter.sendMessage(msg);
    }

    public class LocalBinder extends Binder {
        public XLDzService getService() {
            return XLDzService.this;
        }
    }

    void closeServer() {
        L.d("ws closeServer ");

        presenter.close();
        presenter = null;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        closeServer();
    }

}
