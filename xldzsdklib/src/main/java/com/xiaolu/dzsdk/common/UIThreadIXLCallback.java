package com.xiaolu.dzsdk.common;

import com.xiaolu.dzsdk.base.util.UIRunner;
import com.xiaolu.dzsdk.sdk.listener.IXLCallback;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Roye on 2018/4/4.
 */

public abstract class UIThreadIXLCallback {

    public static IXLCallback getProxy(final IXLCallback xlCallback) {
        return (IXLCallback) Proxy.newProxyInstance(UIThreadIXLCallback.class
                        .getClassLoader(), new Class[]{IXLCallback.class},
                new InvocationHandler() {

                    @Override
                    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                        UIRunner.runOnUI(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    method.invoke(xlCallback, args);
                                } catch (Throwable e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        return null;
                    }
                });
    }
}
