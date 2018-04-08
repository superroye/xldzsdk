package com.xiaolu.dzsdk.net.msg;


import com.xiaolu.dzsdk.base.util.L;
import com.xiaolu.dzsdk.net.XLWSPresenter;
import com.xiaolu.dzsdk.net.msg.msghandle.EchoHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.LeaveRstHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.LoginHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.OfflineHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.OnlineHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.PlistHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.RegisterHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.RejoinRstHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.RoomEnoughHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.RoomRstHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.StopRstHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.TickHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.TickRoomHandle;
import com.xiaolu.dzsdk.net.msg.msghandle.TickRstHandle;

import java.util.concurrent.ArrayBlockingQueue;

import io.socket.client.Socket;

/**
 * Created by Roye on 2018/4/3.
 */

public class MsgHandler implements IMessageHandle {

    private boolean isMessageFinished = true;
    private long lastMessageTime;
    XLWSPresenter presenter;
    ArrayBlockingQueue<SendMessage> messagesQueue = new ArrayBlockingQueue<>(8);

    public void setContext(XLWSPresenter presenter) {
        this.presenter = presenter;
    }

    public void regMessageOn() {

        Socket socket = presenter.getWebSocket();

        socket.on(AbsMsg.RespEvent.echo.name(), new MessageRespListener<EchoHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.leave_rst.name(), new MessageRespListener<LeaveRstHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.login.name(), new MessageRespListener<LoginHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.offline.name(), new MessageRespListener<OfflineHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.register.name(), new MessageRespListener<RegisterHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.online.name(), new MessageRespListener<OnlineHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.plist.name(), new MessageRespListener<PlistHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.tick.name(), new MessageRespListener<TickHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.tick_room.name(), new MessageRespListener<TickRoomHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.tick_rst.name(), new MessageRespListener<TickRstHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.rejoin_rst.name(), new MessageRespListener<RejoinRstHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.room_enough.name(), new MessageRespListener<RoomEnoughHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.room_rst.name(), new MessageRespListener<RoomRstHandle>(this) {
        });
        socket.on(AbsMsg.RespEvent.stop_rst.name(), new MessageRespListener<StopRstHandle>(this) {
        });
    }

    @Override
    public void sendMessage(SendMessage sendMessage) {
        L.i("add message : " + sendMessage.getMessage());
        if (!presenter.checkAsyncConnect()) {
            return;
        }
        messagesQueue.add(sendMessage);
        consumeMessage();
    }

    @Override
    public void consumeMessage() {
        Socket socket = presenter.getWebSocket();
        if (isMessageFinished || System.currentTimeMillis() - lastMessageTime > 1000) {
            SendMessage msg = messagesQueue.poll();
            if (msg == null) {
                return;
            }
            lastMessageTime = System.currentTimeMillis();
            isMessageFinished = false;

            if (!socket.connected()) {
                L.w("remote service is not connected");
                return;
            }

            L.i("send message : " + msg.getMessage());
            socket.emit(msg.getEvent().name(), msg.getMessageParams());
        } else {
            L.i("the last message is sending, please wait..");
        }
    }

    @Override
    public void messageFinished() {
        isMessageFinished = true;
    }
}
