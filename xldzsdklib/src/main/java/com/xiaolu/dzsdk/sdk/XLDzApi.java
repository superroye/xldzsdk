package com.xiaolu.dzsdk.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.xiaolu.dzsdk.common.SDKContext;
import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.common.bean.RoomParams;
import com.xiaolu.dzsdk.sdk.listener.ISysEvent;
import com.xiaolu.dzsdk.sdk.listener.IXLCallback;

/**
 * Created by Roye on 2018/4/2.
 */

public class XLDzApi {

    private static SDKData mSdkData;

    public static void init(Application app, String appId) {
        SDKContext.setApp(app);
        XLDzSdk.getInstance().init(appId);
    }

    public static void setTest() {
        XLDzSdk.getInstance().setTest();
    }

    public static void setDebug() {
        XLDzSdk.getInstance().setDebug();
    }

    public static void setCallback(IXLCallback callback) {
        XLDzSdk.getInstance().setCallback(callback);
    }

    public static void setSysCallback(ISysEvent callback) {
        XLDzSdk.getInstance().setSysCallback(callback);
    }

    /**
     * 获取sdk房间或相关数据
     */
    public static SDKData getData() {
        if (mSdkData == null) {
            mSdkData = new SDKData();
        }
        return mSdkData;
    }

    /**
     * @param params 进入模式，设房间密码等参数
     */
    public static void openRoom(RoomParams params) {
        XLDzSdk.getInstance().openRoom(params);
    }

    /**
     * @param roomNo 房号
     * @param token  进房密码
     */
    public static void enterRoom(String roomNo, Object... token) {
        XLDzSdk.getInstance().enterRoom(roomNo, token);
    }

    /**
     * 退出房间
     * （玩家只能进入一个房间）
     */
    public static void exitRoom() {
        XLDzSdk.getInstance().exitRoom();
    }

    public static void login() {
        XLDzSdk.getInstance().login();
    }

    public static void register() {
        XLDzSdk.getInstance().register();
    }

    public static void roomEcho(String content) {
        XLDzSdk.getInstance().echo(content);
    }

    public static void tickRoom(long userId) {
        XLDzSdk.getInstance().tick(userId);
    }

    public static void stopJoinRoom() {
        XLDzSdk.getInstance().stopJoin();
    }

    public static void rejoinRoom() {
        XLDzSdk.getInstance().rejoin(XLDzSdk.getInstance().getSdkData().getRoomId());
    }

    /**
     * onResume方法，游戏在主activity的onResume调用
     *
     * @param activity 游戏的主activity的Context
     */
    public static void onResume(Activity activity) {

    }


    public static void onPause(Activity activity) {

    }

    public static void onStop(Activity activity) {

    }

    public static void onDestroy(Activity activity) {

    }

    public static void onRestart(Activity activity) {

    }

    public static void onStart(Activity activity) {

    }

    public static void onNewIntent(Intent intent) {

    }

    public static void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {

    }
}
