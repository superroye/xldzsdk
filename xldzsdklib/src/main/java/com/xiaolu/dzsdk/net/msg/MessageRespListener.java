package com.xiaolu.dzsdk.net.msg;

import com.google.gson.Gson;
import com.xiaolu.dzsdk.base.util.L;
import com.xiaolu.dzsdk.net.bean.BaseResult;
import com.xiaolu.dzsdk.net.msg.msghandle.AbsHandle;

import java.lang.reflect.ParameterizedType;

import io.socket.emitter.Emitter;

public abstract class MessageRespListener<Handle extends AbsHandle> implements Emitter.Listener {

    AbsHandle myHandle;
    IMessageHandle messageHandle;

    public MessageRespListener(IMessageHandle messageHandle) {
        this.messageHandle = messageHandle;
        Class<Handle> cls = (Class<Handle>) (((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
        try {
            myHandle = cls.newInstance();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void call(Object... args) {
        messageHandle.messageFinished();
        if (args == null || args[0] == null) {
            L.w("msg result is empty");
            return;
        }
        String msg = args[0].toString();
        L.i("response: " + msg);

        try {
            Gson gson = new Gson();
            final Object data = gson.fromJson(msg, myHandle.getMessageClazz());
            if (data instanceof BaseResult) {
                if (((BaseResult) data).status != 0){
                    myHandle.onBadHandle(data);
                    return;
                }
            }
            myHandle.onSuccess(data);
        } catch (Throwable ex) {
            L.w("json error", ex);
        }
    }

}