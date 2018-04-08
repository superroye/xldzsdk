package com.xiaolu.dzsdk.net.msg;

/**
 * Created by Roye on 2018/4/2.
 */

public abstract class AbsMsg {

    public EmitType getEvent() {
        return event;
    }

    public void setEvent(EmitType event) {
        this.event = event;
    }

    EmitType event;

    /**
     * 请求的接口表示
     */
    public enum EmitType {
        register, login, create, join, leave, echo, stop_join, tick, rejoin
    }

    /**
     * 响应的事件类型
     */
    public enum RespEvent {
        connect, disconnect, error, register, login, tick, room_rst, online, offline, plist, room_enough, echo, leave_rst, tick_rst, tick_room, stop_rst, rejoin_rst
    }
}
