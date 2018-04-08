package com.xiaolu.dzsdk.net.msg;

import com.xiaolu.dzsdk.base.util.L;
import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.net.base.NetRouter;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Roye on 2018/4/3.
 */

public class ConnectMgr {

    private Socket socket;

    public void init() {
        if (socket != null) {
            return;
        }

        String[] ip = NetRouter.getInstance().getIpPort();
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        opts.reconnection = true;
        //opts.timeout = 10_000;

        opts.query = String.format("appKey=%s&appSecret=%s", "5abb106890c6092e08a87666", "4ea71d395cd91573c3880697f6e570cd");
        opts.transports = new String[]{"websocket"};

        try {
            String url = String.format("http://%s:%s", ip[0], ip[1]);
            L.i("connect url " + url);

            socket = IO.socket(url, opts);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                if (args != null && args.length > 0) {
                    for (Object arg : args) {
                        onConnected(String.valueOf(arg));
                    }
                } else {
                    onConnected("");
                }
                mMessageHanlde.consumeMessage();
            }

        }).on(Socket.EVENT_RECONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                if (args != null && args.length > 0) {
                    for (Object arg : args) {
                        L.i("reconnect: " + arg);
                    }
                } else {
                    L.i("reconnect");
                }
                mMessageHanlde.consumeMessage();
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                if (args != null && args.length > 0) {
                    for (Object arg : args) {
                        onDisconnected(String.valueOf(arg));
                    }
                } else {
                    onDisconnected("");
                }
                mMessageHanlde.messageFinished();
            }
        }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                if (args != null && args.length > 0) {
                    for (Object arg : args) {
                        onError(String.valueOf(arg));
                    }
                } else {
                    onError("");
                }

                mMessageHanlde.messageFinished();
            }
        }).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                if (args != null && args.length > 0) {
                    for (Object arg : args) {
                        L.i("connect_timeout: " + arg);
                    }
                } else {
                    L.i("connect_timeout");
                }
                mMessageHanlde.messageFinished();
            }
        }).on(Socket.EVENT_ERROR, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                if (args != null && args.length > 0) {
                    for (Object arg : args) {
                        onError(String.valueOf(arg));
                    }
                } else {
                    onError("");
                }

                mMessageHanlde.messageFinished();
            }
        });
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean isConnected() {
        return socket.connected();
    }

    public void connect() {
        socket.connect();
    }

    IMessageHandle mMessageHanlde;

    public void setMessageHanlde(IMessageHandle messageHanlde) {
        mMessageHanlde = messageHanlde;
    }

    public void close() {
        mMessageHanlde = null;
        socket.disconnect();
        socket = null;
    }

    public void onConnected(String desc) {
        L.d("onConnected msg: " + String.valueOf(desc));
        if (XLDzSdk.getSysCallback() != null) {
            XLDzSdk.getSysCallback().onConnected(desc);
        }
    }

    public void onDisconnected(String desc) {
        L.d("onDisconnected msg: " + String.valueOf(desc));
        if (XLDzSdk.getSysCallback() != null) {
            XLDzSdk.getSysCallback().onDisconnected(desc);
        }
    }

    public void onError(String desc) {
        L.d("onError msg: " + String.valueOf(desc));
        if (XLDzSdk.getSysCallback() != null) {
            XLDzSdk.getSysCallback().onError(desc);
        }
    }
}
