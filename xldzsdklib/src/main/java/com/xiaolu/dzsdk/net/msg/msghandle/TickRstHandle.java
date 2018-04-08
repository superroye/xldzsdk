package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.common.bean.EventRet;
import com.xiaolu.dzsdk.net.bean.TickRst;

/**
 * Created by Roye on 2018/4/4.
 *  房主踢人的结果
 */

public class TickRstHandle extends AbsHandle<TickRst> {

    @Override
    public void onSuccess(TickRst data) {
        EventRet ret = new EventRet();
        ret.data = data;

        XLDzSdk.getCallback().onRoomTick(ret);
    }

    @Override
    public void onFail(TickRst data) {
        EventRet ret = new EventRet();
        ret.data = data;
        ret.result = 0;

        XLDzSdk.getCallback().onRoomTick(ret);
    }


}
