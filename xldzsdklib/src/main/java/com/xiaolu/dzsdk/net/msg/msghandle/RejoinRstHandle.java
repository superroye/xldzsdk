package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.common.bean.EventRet;
import com.xiaolu.dzsdk.net.bean.ReJoinRst;

/**
 * Created by Roye on 2018/4/4.
 */

public class RejoinRstHandle extends AbsHandle<ReJoinRst> {

    @Override
    public void onSuccess(ReJoinRst data) {
        EventRet ret = new EventRet();
        ret.data = data;

        XLDzSdk.getCallback().onRoomJoin(ret);
    }

    @Override
    public void onFail(ReJoinRst data) {
        EventRet ret = new EventRet();
        ret.data = data;
        ret.result = 0;

        XLDzSdk.getCallback().onRoomJoin(ret);
    }
}
