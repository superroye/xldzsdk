package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.common.bean.EventRet;
import com.xiaolu.dzsdk.net.bean.Tick;

/**
 * Created by Roye on 2018/4/4.
 *  * 踢人事件
 */

public class TickHandle extends AbsHandle<Tick> {

    @Override
    public void onSuccess(Tick data) {
        EventRet ret = new EventRet();
        ret.data = data;

        XLDzSdk.getCallback().onRoomTick(ret);
    }

    @Override
    public void onFail(Tick data) {
        EventRet ret = new EventRet();
        ret.data = data;
        ret.result = 0;

        XLDzSdk.getCallback().onRoomTick(ret);
    }
}
