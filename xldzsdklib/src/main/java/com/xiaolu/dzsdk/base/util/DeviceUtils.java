package com.xiaolu.dzsdk.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.xiaolu.dzsdk.common.Constant;
import com.xiaolu.dzsdk.common.SDKContext;


public class DeviceUtils {
    /**
     * 获取手机内部的剩余存储空间
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getAvailableInternalStorage() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableSize = stat.getAvailableBlocks() * blockSize;
        return availableSize;
    }
    
    /**
     * 获取手机系统版本号
     * 
     * @return
     */
    public static String getOSVersion() {
    	return android.os.Build.VERSION.RELEASE;
    }
    
    /**
     * 获取SDK版本号
     * 
     * @return
     */
    public static String getSDKVersion() {
    	return android.os.Build.VERSION.SDK;
    }

    /**
     * 获取手机SDCARD的剩余存储空间
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getAvailableExternalStorage() {
        long availableSize = -1;
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            StatFs stat = new StatFs(Constant.getSdCardPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            availableSize = availableBlocks * blockSize;
        }
        return availableSize;
    }

    /**
     * 获取当前Android系统可用内存大小
     * 
     * @return
     */
    public static long getAvailMemory() {
        ActivityManager am = (ActivityManager)SDKContext.getApp() .getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);// mi.availMem; 当前系统的可用内存
        return mi.availMem;
    }

    /**
     * 获取当前Android系统总内存大小
     * 
     * @return
     */
    public static long getTotalMemory() {
        
        
        final String memInfoFile = "/proc/meminfo"; // 系统内存信息文件
        String tempStr;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader localFileReader = new FileReader(memInfoFile);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            tempStr = localBufferedReader.readLine(); // 读取meminfo第一行，系统总内存大小
            arrayOfString = tempStr.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(tempStr, num + "\t");
            }
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return initial_memory;
    }

    private static String getCpuString() {
        ProcessBuilder cmd;
        String result = "";
        try {
            String[] args = { "/system/bin/cat", "/proc/cpuinfo" };
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();

            InputStream in = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            char[] re = new char[1024];
            StringBuilder sb = new StringBuilder();
            int lens = 0;
            while ((lens = br.read(re)) != -1) {
                sb.append(re, 0, lens);
            }
            result = sb.toString();
            sb = null;
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result.toString();
    }

    public static HashMap<String, String> getCpuInfo() {
        String cpulog = getCpuString();
        HashMap<String, String> map = new HashMap<String, String>();
        String[] infos = cpulog.split("\\n");
        int processor = 0;
        String bogoMIPS = "";
        if (infos == null) {
            return map;
        }

        for (String info : infos) {
            if (info != null) {
                if (info.contains("BogoMIPS")) {
                    bogoMIPS = info.split(":")[1];
                    processor++;
                }
            }
        }

        bogoMIPS = bogoMIPS.trim();
        map.put("cpuNum", processor + "");
        map.put("bogoMIPS", bogoMIPS);

        return map;
    }

    public static boolean isValidUuid(String uuid) {
        if (TextUtils.isEmpty(uuid) || uuid.length() <= 6) {
            return false;
        }
        
        // 包含8个0也认为是非法的
        if (uuid.contains("00000000") || uuid.equals("UNKNOWN") || uuid.contains("1111111111") || uuid.equals("null")) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 返回imei
     * @return
     */
    public static String getDeviceId() {
        String uuid = "";
        TelephonyManager tm = (TelephonyManager) SDKContext.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        uuid = tm.getDeviceId();
        L.d("getDeviceId, uuid:" + uuid);

        if (!isValidUuid(uuid)) {
            uuid = tm.getSimSerialNumber();
            L.d("getSimSerialNumber, uuid:" + uuid);
        }
        
        if (!isValidUuid(uuid)) {
            WifiManager wifi = (WifiManager) SDKContext.getApp().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();  
            uuid = info.getMacAddress(); 
            if (!TextUtils.isEmpty(uuid)) {
                uuid = uuid.replace(":", "");
            }
            
            L.d("getMacAddress, uuid:" + uuid);
        }
        
        if (!isValidUuid(uuid)) {
            uuid = android.provider.Settings.Secure.getString(SDKContext.getApp().getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
            L.d("ANDROID_ID, uuid:" + uuid);
        }
        
        return uuid;
    }
    

    /** 
     * 获取当前设置的电话号码 
     */  
    public static String getNativePhoneNumber() {  
        TelephonyManager telManager = (TelephonyManager) SDKContext.getApp()
                .getSystemService(Context.TELEPHONY_SERVICE); 
        
        String NativePhoneNumber=null;  
        NativePhoneNumber=telManager.getLine1Number();  
        return NativePhoneNumber;  
    }  
  
    /** 
     * Telecom service providers获取手机服务商信息 
     */  
    public static String getIMSI() {  
        TelephonyManager telManager = (TelephonyManager) SDKContext.getApp()
                .getSystemService(Context.TELEPHONY_SERVICE); 
        
        // 返回唯一的用户ID;就是这张卡的编号神马的  
        String IMSI = telManager.getSubscriberId();  
                
        return IMSI;  
    }  
    
    public static boolean existSDcard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else
            return false;
    }
        
}
