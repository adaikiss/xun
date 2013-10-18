package org.adaikiss.xun.accommodation.map;

import android.content.Context;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;

public class BaiduMapManager {

	private static BaiduMapManager mInstance = null;
	public BMapManager mBMapManager = null;

	private static final String MapKey = "0F88c148775fc812a98b53a086ea78d0";

	private BaiduMapManager(Context context){
		mBMapManager = new BMapManager(context);
	}

	public static BaiduMapManager getInstance() {
		return getInstance(null);
	}

	public static BaiduMapManager getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new BaiduMapManager(context);
			mInstance.mBMapManager.init(MapKey, null);
		}
		return mInstance;
	}

	public BMapManager getBMapManager(){
		return mBMapManager;
	}
}