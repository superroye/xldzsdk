package com.xiaolu.dzsdk.common;

import com.google.gson.Gson;
import com.xiaolu.dzsdk.base.util.L;
import com.xiaolu.dzsdk.net.XLWsClient;
import com.xiaolu.dzsdk.net.base.NetRouter;
import com.xiaolu.dzsdk.net.msg.MessageBuilder;
import com.xiaolu.dzsdk.common.bean.RoomParams;
import com.xiaolu.dzsdk.sdk.SDKData;
import com.xiaolu.dzsdk.sdk.listener.ISysEvent;
import com.xiaolu.dzsdk.sdk.listener.IXLCallback;

/**
 * Created by Roye on 2018/4/2.
 */

public class XLDzSdk {

    private static XLDzSdk mInstance;

    private IXLCallback mGlobalCallback;

    private ISysEvent mSysCallback;

    private InnerSDKData mSdkData;

    private XLDzSdk() {
    }

    public static XLDzSdk getInstance() {
        if (mInstance == null) {
            synchronized (XLDzSdk.class) {
                if (mInstance == null) {
                    mInstance = new XLDzSdk();
                }
            }
        }
        return mInstance;
    }

    public static IXLCallback getCallback() {
        return getInstance().mGlobalCallback;
    }

    public static ISysEvent getSysCallback() {
        return getInstance().mSysCallback;
    }

    public void init(String appId) {
        XLData.setAppId(appId);
        NetRouter.getInstance().requestIps();
    }

    public void setTest() {
        SDKContext.ENV = "test";
    }

    public void setDebug() {
        L.setDebug(SDKContext.DEBUG = true);
    }

    public void setCallback(IXLCallback callback) {
        mGlobalCallback = UIThreadIXLCallback.getProxy(callback);
    }

    public void setSysCallback(ISysEvent callback) {
        mSysCallback = callback;
    }

    public InnerSDKData getSdkData() {
        if (mSdkData == null) {
            mSdkData = new InnerSDKData();
        }
        return mSdkData;
    }

    /**
     * @param params 进入模式，设房间密码等参数
     */
    public void openRoom(RoomParams params) {
        Gson gson = new Gson();
        String str = gson.toJson(params);
        XLWsClient.getInstance().sendMassage(MessageBuilder.create(str));
    }

    /**
     * @param roomNo 房号
     * @param token  进房密码
     */
    public void enterRoom(String roomNo, Object... token) {
        XLWsClient.getInstance().sendMassage(MessageBuilder.join(String.valueOf(roomNo)));
    }

    /**
     * 退出房间
     * （玩家只能进入一个房间）
     */
    public void exitRoom() {
        XLWsClient.getInstance().sendMassage(MessageBuilder.leave(""));
    }

    public void login() {
        XLWsClient.getInstance().sendMassage(MessageBuilder.login());
    }

    public void register() {
        XLWsClient.getInstance().sendMassage(MessageBuilder.register(""));
    }

    public void echo(String content) {
        XLWsClient.getInstance().sendMassage(MessageBuilder.echo(content));
    }

    public void tick(long userId) {
        XLWsClient.getInstance().sendMassage(MessageBuilder.tick(String.valueOf(userId)));
    }

    public void stopJoin() {
        XLWsClient.getInstance().sendMassage(MessageBuilder.stopJoin(""));
    }

    public void rejoin(long roomId) {
        XLWsClient.getInstance().sendMassage(MessageBuilder.rejoin(String.valueOf(roomId)));
    }
}
