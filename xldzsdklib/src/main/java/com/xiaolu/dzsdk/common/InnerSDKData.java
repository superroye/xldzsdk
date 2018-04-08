package com.xiaolu.dzsdk.common;

/**
 * Created by Roye on 2018/4/8.
 */

public class InnerSDKData {

    private int userCount;// 房间内玩家人数
    private long roomerId;// 房主ID
    private int roomStatus;// 房间状态：1：开始；2：游戏中；3：结束

    public long getRoomerId() {
        return roomerId;
    }

    public void setRoomerId(long roomerId) {
        this.roomerId = roomerId;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    // 房间唯一ID
    public long getRoomId() {
        return XLData.getRoomId();
    }

    public long getUserId() {
        return XLData.getUid();
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    public int getRoomStatus() {
        return roomStatus;
    }
}
