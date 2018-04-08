package com.xiaolu.dzsdk.net.msg;

/**
 * Created by Roye on 2018/4/2.
 */

public class SendMessage extends AbsMsg {

    public SendMessage(EmitType event) {
        setEvent(event);
    }

    String params;

    public void setMessageParams(String params) {
        this.params = params;
    }

    public String getMessageParams() {
        return params;
    }


    public String getMessage() {
        return event.name() + " " + params;
    }




}
