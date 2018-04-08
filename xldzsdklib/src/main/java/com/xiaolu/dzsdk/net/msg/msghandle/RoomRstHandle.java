package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.XLData;
import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.common.bean.EventRet;
import com.xiaolu.dzsdk.net.bean.RoomRst;
import com.xiaolu.dzsdk.net.msg.AbsMsg;

/**
 * Created by Roye on 2018/4/4.
 */

public class RoomRstHandle extends AbsHandle<RoomRst> {

    @Override
    public void onSuccess(RoomRst data) {
        if (AbsMsg.EmitType.create.name().equals(data.type)) {
            EventRet ret = new EventRet();
            ret.data = data;

            XLData.setRoomId(data.roomId);

            XLDzSdk.getCallback().onRoomCreate(ret);
        } else if (AbsMsg.EmitType.join.name().equals(data.type)) {
            EventRet ret = new EventRet();
            ret.data = data;
            XLDzSdk.getCallback().onRoomJoin(ret);
        }
    }

    @Override
    public void onFail(RoomRst data) {
        if (AbsMsg.EmitType.create.name().equals(data.type)) {
            EventRet<RoomRst> ret = new EventRet<>();
            ret.data = data;
            ret.result = 0;

            XLDzSdk.getCallback().onRoomCreate(ret);
        }
    }
}
