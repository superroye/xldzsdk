package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.InnerSDKData;
import com.xiaolu.dzsdk.common.XLData;
import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.common.bean.EventRet;
import com.xiaolu.dzsdk.net.bean.Plist;

/**
 * Created by Roye on 2018/4/4.
 */

public class PlistHandle extends AbsHandle<Plist> {


    @Override
    public void onSuccess(Plist data) {
        EventRet ret = new EventRet();
        ret.data = data.plist;

        InnerSDKData sdkdata = XLDzSdk.getInstance().getSdkData();
        sdkdata.setRoomerId(data.roomer);
        sdkdata.setUserCount(data.roomCnt);
        sdkdata.setRoomStatus(data.status);

        XLData.setRoomId(data.roomId);

        XLDzSdk.getCallback().onRoomRefresh(ret);
    }

    @Override
    public void onFail(Plist data) {
        EventRet ret = new EventRet();
        ret.data = data;
        ret.result = 0;

        XLDzSdk.getCallback().onRoomRefresh(ret);
    }
}
