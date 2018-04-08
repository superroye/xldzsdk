package com.xiaolu.dzsdk.common;

import android.os.Environment;
import java.io.File;

public class Constant {
	public static String getGlobalPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + SDKContext.TAG;
	}

	public static String getGlobalPackagePath() {
		return SDKContext.getApp().getCacheDir().getAbsolutePath() + File.separator + SDKContext.TAG;
	}
	
	public static String getSdCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}
}
