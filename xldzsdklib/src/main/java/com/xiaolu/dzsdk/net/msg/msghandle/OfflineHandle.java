package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.common.bean.EventRet;
import com.xiaolu.dzsdk.net.bean.Offline;

/**
 * Created by Roye on 2018/4/4.
 */

public class OfflineHandle extends AbsHandle<Offline> {

    @Override
    public void onSuccess(Offline data) {
        EventRet ret = new EventRet();
        ret.data = data;
        XLDzSdk.getCallback().onRoomLeave(ret);
    }

    @Override
    public void onFail(Offline data) {
        EventRet ret = new EventRet();
        ret.data = data;
        ret.result = 0;
        XLDzSdk.getCallback().onRoomLeave(ret);
    }
}
