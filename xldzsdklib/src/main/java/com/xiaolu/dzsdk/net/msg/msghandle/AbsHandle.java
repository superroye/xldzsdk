package com.xiaolu.dzsdk.net.msg.msghandle;

import android.widget.Toast;

import com.xiaolu.dzsdk.base.util.UIRunner;
import com.xiaolu.dzsdk.common.SDKContext;
import com.xiaolu.dzsdk.net.bean.BaseResult;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Roye on 2018/4/4.
 */

public abstract class AbsHandle<T> {

    public abstract void onSuccess(T data);

    public Class<T> getMessageClazz() {
        Class<T> cls = (Class<T>) (((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
        return cls;
    }

    public final void onBadHandle(final T data) {
        UIRunner.runOnUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SDKContext.getApp(), String.valueOf(((BaseResult) data).desc), Toast.LENGTH_SHORT).
                        show();
            }
        });
        onFail(data);
    }

    public abstract void onFail(final T data);
}
