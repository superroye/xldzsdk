package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.net.bean.Online;
import com.xiaolu.dzsdk.common.bean.EventRet;

/**
 * Created by Roye on 2018/4/4.
 */

public class OnlineHandle extends AbsHandle<Online> {

    @Override
    public void onSuccess(Online data) {
        EventRet ret = new EventRet();
        ret.data = data;

        XLDzSdk.getCallback().onRoomUserOnline(ret);
    }

    @Override
    public void onFail(Online data) {
        EventRet ret = new EventRet();
        ret.data = data;
        ret.result = 0;
        XLDzSdk.getCallback().onRoomUserOnline(ret);
    }
}
