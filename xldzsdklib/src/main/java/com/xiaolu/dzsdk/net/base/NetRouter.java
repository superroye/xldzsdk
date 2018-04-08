package com.xiaolu.dzsdk.net.base;

import android.util.Log;
import com.xiaolu.dzsdk.common.SDKContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roye on 2018/4/2.
 */

public class NetRouter {

    private static NetRouter mInstance;

    //支持多ip（端口）服务模式
    private List<String[]> iplist = new ArrayList<>();
    private int turnCounter;

    private NetRouter() {

    }

    public static NetRouter getInstance() {
        if (mInstance == null) {
            synchronized (NetRouter.class) {
                if (mInstance == null) {
                    mInstance = new NetRouter();
                }
            }
        }
        return mInstance;
    }

    public void requestIps() {
        Log.d("NetRouter","request ip list");

    }

    public String[] getIpPort() {
        return mInstance.getNote();
    }

    /**
     * 简易的负载均衡
     */
    private String[] getNote() {
        if (iplist.isEmpty()) {
            if ("test".equals(SDKContext.ENV)) {
                //return new String[]{"192.168.0.36", "3002"};
                return new String[]{"60.205.225.164", "80"};
                //return new String[]{"192.168.0.7", "3000"};
            } else {
                return new String[]{"60.205.225.164", "80"};
            }
        }
        int c = turnCounter;
        turnCounter++;
        turnCounter = turnCounter % iplist.size();
        return iplist.get(c);
    }
}
