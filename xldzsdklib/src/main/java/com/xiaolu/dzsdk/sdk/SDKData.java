package com.xiaolu.dzsdk.sdk;

import com.xiaolu.dzsdk.common.XLDzSdk;

/**
 * Created by Roye on 2018/4/8.
 */

public class SDKData {

    // 房主ID
    public long getRoomerId() {
        return XLDzSdk.getInstance().getSdkData().getRoomerId();
    }

    // 房间内玩家人数
    public int getUserCount() {
        return XLDzSdk.getInstance().getSdkData().getUserCount();
    }

    // 房间唯一ID
    public long getRoomId() {
        return XLDzSdk.getInstance().getSdkData().getRoomId();
    }

    //当前用户ID
    public long getUserId() {
        return XLDzSdk.getInstance().getSdkData().getUserId();
    }

    // 房间状态：1：开始；2：游戏中；3：结束
    public int getRoomStatus() {
        return XLDzSdk.getInstance().getSdkData().getRoomStatus();
    }
}
