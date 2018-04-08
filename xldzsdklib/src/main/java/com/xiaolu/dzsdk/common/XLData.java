package com.xiaolu.dzsdk.common;

import android.text.TextUtils;

import com.xiaolu.dzsdk.base.util.DeviceUtils;
import com.xiaolu.dzsdk.base.util.L;

/**
 * Created by Roye on 2018/4/4.
 */

public class XLData {

    private static CommonData data;

    public final static String PATH_ASSET = "file:///android_asset/";
    public final static String SP_FILE = "global_data";

    public final static String OS = "android";

    /**
     * 沙箱环境
     */
    public final static String ENV_SANDBOX = "sandbox";
    /**
     * 正式环境
     */
    public final static String ENV_PRODUCTION = "production";
    /**
     * 内部测试环境（不稳定）
     */
    public final static String ENV_DEV = "dev";
    /**
     * 新版本环境（不稳定）
     */
    public final static String ENV_NEW = "new";

    /**
     * 正式身份
     */
    public final static String STATUS_OFFICIAL = "official";
    /**
     * 游客身份
     */
    public final static String STATUS_ANON = "anon";

    /**
     * 普通渠道
     */
    public final static String CHANNEL_NORMAL = "normal";

    private static final String SP_KEY_GAMEID = "gameId";
    private static final String SP_KEY_APPID = "appId";
    private static final String SP_KEY_GAMEVERSION = "gameVersion";
    private static final String SP_KEY_USERSTATUS = "userStatus";
    private static final String SP_KEY_SDKVERSION = "sdkVersion";
    private static final String SP_KEY_CHANNEL = "channel";
    private static final String SP_KEY_ZONE = "zone";
    private static final String SP_KEY_UUID = "uuid";
    private static final String SP_KEY_UID = "uid";
    private static final String SP_KEY_TOKEN = "token";
    private static final String SP_KEY_USERNAME = "userName";
    private static final String SP_KEY_USERFACE = "userFace";
    private static final String SP_KEY_USERNICK = "userNick";
    private static final String SP_KEY_OPENID = "openId";
    private static final String SP_KEY_OPENTOKEN = "openToken";
    private static final String SP_KEY_ROOMID = "roomId";

    private static CommonData getData() {
        if (data == null) {
            data = new CommonData(SP_FILE);
        }
        return data;
    }

    public static String getGameId() {
        return getData().getValue(SP_KEY_GAMEID);
    }

    public static void setGameId(String gameId) {
        getData().setValue(SP_KEY_GAMEID, gameId);
    }

    public static String getAppId() {
        return getData().getValue(SP_KEY_APPID);
    }

    public static void setAppId(String appId) {
        getData().setValue(SP_KEY_APPID, appId);
    }

    public static int getSdkVersion() {
        return getData().getIntValue(SP_KEY_SDKVERSION);
    }

    public static void setSdkVersion(int sdkVersion) {
        getData().setValue(SP_KEY_SDKVERSION, sdkVersion);
    }

    public static int getGameVersion() {
        return getData().getIntValue(SP_KEY_GAMEVERSION);
    }

    public static void setGameVersion(int gameVersion) {
        getData().setValue(SP_KEY_GAMEVERSION, gameVersion);
    }

    public static void setChannel(String channel) {
        if (TextUtils.isEmpty(channel)) {
            L.e("Manifest configure error. channel is empty.");
            // 故意抛错
            channel.length();
            return;
        }
        getData().setValue(SP_KEY_CHANNEL, channel);
    }

    public static String getChannel() {
        return getData().getValue(SP_KEY_CHANNEL);
    }

    public static String getUuid() {
        String uuid = getData().getValue(SP_KEY_UUID);
        if (!DeviceUtils.isValidUuid(uuid)) {
            uuid = DeviceUtils.getDeviceId();
            getData().setValue(SP_KEY_UUID, uuid);
        }

        return uuid;
    }

    public static void setUid(long uid) {
        getData().setValue(SP_KEY_UID, uid);
    }

    public static long getUid() {
        return getData().getLongValue(SP_KEY_UID);
    }

    public static void setToken(String token) {
        getData().setValue(SP_KEY_TOKEN, token);
    }

    public static String getToken() {
        return getData().getValue(SP_KEY_TOKEN);
    }

    public static void setRoomId(long roomId) {
        getData().setValue(SP_KEY_ROOMID, roomId);
    }

    public static long getRoomId() {
        return getData().getLongValue(SP_KEY_ROOMID);
    }

    public static void clear() {
        getData().clear();
    }

}
