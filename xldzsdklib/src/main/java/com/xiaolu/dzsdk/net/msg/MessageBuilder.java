package com.xiaolu.dzsdk.net.msg;

import com.xiaolu.dzsdk.common.XLData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Roye on 2018/4/2.
 */

public class MessageBuilder {

    public static SendMessage login() {
        SendMessage message = new SendMessage(AbsMsg.EmitType.login);

        String paramsStr = "";
        try {
            JSONObject object = new JSONObject();
            object.put("userId", String.valueOf(XLData.getUid()));
            object.put("token", String.valueOf(XLData.getToken()));
            paramsStr = object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        message.setMessageParams(paramsStr);
        return message;
    }

    public static SendMessage create(String params) {
        SendMessage message = new SendMessage(AbsMsg.EmitType.create);
        message.setMessageParams(params);
        return message;
    }

    public static SendMessage echo(String params) {
        SendMessage message = new SendMessage(AbsMsg.EmitType.echo);
        message.setMessageParams(params);
        return message;
    }

    public static SendMessage join(String params) {
        SendMessage message = new SendMessage(AbsMsg.EmitType.join);
        message.setMessageParams(params);
        return message;
    }

    public static SendMessage leave(String params) {
        SendMessage message = new SendMessage(AbsMsg.EmitType.leave);
        message.setMessageParams(params);
        return message;
    }

    public static SendMessage register(String params) {
        SendMessage message = new SendMessage(AbsMsg.EmitType.register);
        message.setMessageParams(params);
        return message;
    }

    public static SendMessage tick(String params) {
        SendMessage message = new SendMessage(AbsMsg.EmitType.tick);
        message.setMessageParams(params);
        return message;
    }

    public static SendMessage stopJoin(String params) {
        SendMessage message = new SendMessage(AbsMsg.EmitType.stop_join);
        message.setMessageParams(params);
        return message;
    }

    public static SendMessage rejoin(String params) {
        SendMessage message = new SendMessage(AbsMsg.EmitType.rejoin);
        message.setMessageParams(params);
        return message;
    }
}
