package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.net.bean.LeaveRst;
import com.xiaolu.dzsdk.common.bean.EventRet;

/**
 * Created by Roye on 2018/4/4.
 */

public class LeaveRstHandle extends AbsHandle<LeaveRst> {

    @Override
    public void onSuccess(LeaveRst data) {
        EventRet ret = new EventRet();
        ret.data = data;

        XLDzSdk.getCallback().onRoomLeave(ret);
    }

    @Override
    public void onFail(LeaveRst data) {
        EventRet ret = new EventRet();
        ret.data = data;
        ret.result = 0;

        XLDzSdk.getCallback().onRoomLeave(ret);
    }
}
