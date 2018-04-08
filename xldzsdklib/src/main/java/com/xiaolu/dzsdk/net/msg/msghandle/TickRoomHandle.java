package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.common.bean.EventRet;
import com.xiaolu.dzsdk.net.bean.TickRoom;

/**
 * Created by Roye on 2018/4/4.
 * 被踢事件
 */

public class TickRoomHandle extends AbsHandle<TickRoom> {

    @Override
    public void onSuccess(TickRoom data) {
        EventRet ret = new EventRet();
        ret.data = data;

        XLDzSdk.getCallback().onRoomTicked(ret);
    }

    @Override
    public void onFail(TickRoom data) {
        EventRet ret = new EventRet();
        ret.data = data;
        ret.result = 0;

        XLDzSdk.getCallback().onRoomTicked(ret);
    }
}
