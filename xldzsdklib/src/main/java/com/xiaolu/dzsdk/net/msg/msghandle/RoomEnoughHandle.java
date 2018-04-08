package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.common.bean.EventRet;
import com.xiaolu.dzsdk.net.bean.RoomEnough;

/**
 * Created by Roye on 2018/4/4.
 */

public class RoomEnoughHandle extends AbsHandle<RoomEnough> {

    @Override
    public void onSuccess(RoomEnough data) {
        EventRet ret = new EventRet();
        ret.data = data;

        XLDzSdk.getCallback().onRoomLimit(ret);
    }

    @Override
    public void onFail(RoomEnough data) {
        EventRet ret = new EventRet();
        ret.data = data;
        ret.result = 0;

        XLDzSdk.getCallback().onRoomLimit(ret);
    }
}
