package com.xiaolu.dzsdk.sdk.listener;

/**
 * Created by Roye on 2018/4/8.
 */

public interface ISysEvent {

    public void onConnected(String desc);

    public void onDisconnected(String desc);

    public void onError(String desc);
}
