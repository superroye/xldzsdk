package com.xiaolu.dzsdk.net;

import com.xiaolu.dzsdk.base.util.L;
import com.xiaolu.dzsdk.net.msg.ConnectMgr;
import com.xiaolu.dzsdk.net.msg.IMessageHandle;
import com.xiaolu.dzsdk.net.msg.MsgHandler;
import com.xiaolu.dzsdk.net.msg.SendMessage;

import io.socket.client.Socket;

/**
 * Created by Roye on 2018/4/3.
 */

public class XLWSPresenter {

    ConnectMgr connectMgr;
    IMessageHandle msgHandler;

    public XLWSPresenter() {
        connectMgr = new ConnectMgr();
        msgHandler = new MsgHandler();
        connectMgr.setMessageHanlde(msgHandler);

        ((MsgHandler) msgHandler).setContext(this);
    }

    public Socket getWebSocket() {
        return connectMgr.getSocket();
    }

    public boolean checkAsyncConnect() {
        boolean isConnected = connectMgr.isConnected();
        if (!isConnected) {
            connectMgr.connect();
        }
        return isConnected;
    }

    public void sendMessage(SendMessage sendMessage){
        msgHandler.sendMessage(sendMessage);
    }

    public void init() {
        connectMgr.init();
        ((MsgHandler) msgHandler).regMessageOn();
    }

    public void start() {
        L.i("websocket connect status is "+connectMgr.isConnected());
        if (!connectMgr.isConnected()) {
            L.i("websocket connect ... ");
            connectMgr.connect();
        }
    }


    public void close() {
        connectMgr.close();
        connectMgr = null;
        msgHandler = null;
    }

}
