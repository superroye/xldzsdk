package com.xiaolu.dzsdk.net.msg;

/**
 * Created by Roye on 2018/4/3.
 */

public interface IMessageHandle {

    public void sendMessage(SendMessage sendMessage);

    public void consumeMessage();

    public void messageFinished();

}
