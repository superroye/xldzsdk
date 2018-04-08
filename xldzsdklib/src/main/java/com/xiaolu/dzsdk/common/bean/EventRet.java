package com.xiaolu.dzsdk.common.bean;

/**
 * Created by Roye on 2018/4/2.
 */

public class EventRet<T> {

    public EventRet() {
        this.result = 1;
    }

    public int code;
    public int result;
    public String msg;
    public T data;
}
