package com.xiaolu.dzsdk.base.util;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.xiaolu.dzsdk.common.SDKContext;

/**
 * Created by Roye on 2016-2-23.
 */
public class ToastUtils {

    private static long time = 0;
    private static String last = "";

    public static boolean isTooFast() {
        return isTooFast(600);
    }

    public static boolean isTooFast(int delay) {
        long curTime = System.currentTimeMillis();
        long span = curTime - time;
        time = curTime;
        if (span < delay) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSame(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            if (msg.equals(last))
                return true;
        }
        last = msg;
        return false;
    }

    public static void showToast(String msg) {
        if (!isTooFast() || !isSame(msg)) {
            Toast.makeText(SDKContext.getApp(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(int resId) {
        String msg = SDKContext.getApp().getString(resId);
        showToast(msg);
    }

    public static void showToastAtCenter(String msg) {
        if (isTooFast())
            return;
        if (SDKContext.getApp() == null)
            return;

        Toast toast = Toast.makeText(SDKContext.getApp(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToastAtCenter(int resId) {
        if (isTooFast())
            return;
        if (SDKContext.getApp() == null)
            return;

        Toast toast = Toast.makeText(SDKContext.getApp(), resId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToastAtTop(String msg) {
        if (isTooFast())
            return;
        if (SDKContext.getApp() == null)
            return;
        Toast toast = Toast.makeText(SDKContext.getApp(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();
    }

    public static void showToastAtTop(int resId) {
        if (isTooFast())
            return;
        if (SDKContext.getApp() == null)
            return;
        Toast toast = Toast.makeText(SDKContext.getApp(), resId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();
    }
}
