package com.xiaolu.dzsdk.sdk.listener;

import com.xiaolu.dzsdk.common.bean.EventRet;
import com.xiaolu.dzsdk.net.bean.RoomRst;

/**
 * Created by Roye on 2018/4/2.
 */

public interface IRoomAction {

    /**
     * 创建房间反馈
     */
    public void onRoomCreate(EventRet<RoomRst> ret);

    /**
     * 用户发送消息（游戏操作，IM聊天）
     */
    public void onRoomEcho(EventRet ret);

    /**
     * 房间满人了
     */
    public void onRoomLimit(EventRet ret);

    /**
     * 房主踢人反馈
     */
    public void onRoomTick(EventRet ret);

    /**
     * 诸如用户变动之类的，触发房间信息变动
     */
    public void onRoomRefresh(EventRet ret);

    /**
     * 用户被踢
     */
    public void onRoomTicked(EventRet ret);

    /**
     * 用户离开
     */
    public void onRoomLeave(EventRet ret);

    /**
     * 新用户上线
     */
    public void onRoomUserOnline(EventRet ret);

    /**
     * 加入房间结果
     */
    public void onRoomJoin(EventRet ret);
}
