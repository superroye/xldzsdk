package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.common.bean.EventRet;

/**
 * Created by Roye on 2018/4/4.
 */

public class EchoHandle extends AbsHandle<String> {

    @Override
    public void onSuccess(String data) {
        EventRet ret = new EventRet();
        ret.data = data;

        XLDzSdk.getCallback().onRoomEcho(ret);
    }

    @Override
    public void onFail(String data) {
        EventRet ret = new EventRet();
        ret.data = data;
        ret.result = 0;

        XLDzSdk.getCallback().onRoomEcho(ret);
    }
}
