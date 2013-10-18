package org.adaikiss.xun.accommodation;

import org.adaikiss.xun.accommodation.map.BaiduMapManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class MainActivity extends Activity implements BDLocationListener, MKMapViewListener, MKSearchListener {

	Button mRefreshBtn = null;
	EditText mKeyword = null;

	BMapManager mBMapMan = null;
	MapView mMapView = null;
	private MapController mMapController = null;
	public LocationClient mLocationClient = null;
	private MyLocationOverlay myLocationOverlay = null;
	private LocationData locData = null;
	MKSearch mKSearch;
	GeoPoint mPoint;

	private Toast mToast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBMapMan = BaiduMapManager.getInstance(getApplication()).getBMapManager();
		// 注意：请在试用setContentView前初始化BMapManager对象，否则会报错
		setContentView(R.layout.activity_main);
		mRefreshBtn = (Button) findViewById(R.id.refresh_btn);
		mRefreshBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// requestNewLocation();
				searchPoi();
			}

		});
		mKeyword = (EditText) findViewById(R.id.keyword);
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		locData = new LocationData();
		myLocationOverlay = new MyLocationOverlay(mMapView);
		myLocationOverlay.setData(locData);
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		mMapController = mMapView.getController();
		// 设置启用内置的缩放控件
		// 得到mMapView的控制权,可以用它控制和驱动平移和缩放
		MapController mMapController = mMapView.getController();
		// 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		mPoint = new GeoPoint((int) (30.277413 * 1E6), (int) (120.134885 * 1E6));
		mMapController.setCenter(mPoint);// 设置地图中心点
		mMapController.setZoom(12);// 设置地图zoom级别

		mMapView.regMapViewListener(mBMapMan, this);
		mMapView.refresh();

		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(this);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		option.setPoiNumber(5); // 最多返回POI个数
		option.setPoiDistance(1000); // poi查询距离
		option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		mKSearch = new MKSearch();
		MKSearch.setPoiPageCapacity(20);
		mKSearch.init(mBMapMan, this);
		requestNewLocation();
	}

	@Override
	protected void onDestroy() {
		mMapView.destroy();
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mMapView.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		if (location == null)
			return;
		locData.latitude = location.getLatitude();
		locData.longitude = location.getLongitude();
		locData.accuracy = location.getRadius();
		locData.direction = location.getDerect();
		mMapController.animateTo(new GeoPoint((int) (location.getLatitude() * 1e6),
				(int) (location.getLongitude() * 1e6)), null);
		StringBuffer sb = new StringBuffer(256);
		sb.append("time : ");
		sb.append(location.getTime());
		sb.append("\nerror code : ");
		sb.append(location.getLocType());
		sb.append("\nlatitude : ");
		sb.append(location.getLatitude());
		sb.append("\nlontitude : ");
		sb.append(location.getLongitude());
		sb.append("\nradius : ");
		sb.append(location.getRadius());
		if (location.getLocType() == BDLocation.TypeGpsLocation) {
			sb.append("\nspeed : ");
			sb.append(location.getSpeed());
			sb.append("\nsatellite : ");
			sb.append(location.getSatelliteNumber());
		} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
			sb.append("\naddr : ");
			sb.append(location.getAddrStr());
		}

		Log.d("", sb.toString());
	}

	public void onReceivePoi(BDLocation poiLocation) {
		if (poiLocation == null) {
			return;
		}
		StringBuffer sb = new StringBuffer(256);
		sb.append("Poi time : ");
		sb.append(poiLocation.getTime());
		sb.append("\nerror code : ");
		sb.append(poiLocation.getLocType());
		sb.append("\nlatitude : ");
		sb.append(poiLocation.getLatitude());
		sb.append("\nlontitude : ");
		sb.append(poiLocation.getLongitude());
		sb.append("\nradius : ");
		sb.append(poiLocation.getRadius());
		if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
			sb.append("\naddr : ");
			sb.append(poiLocation.getAddrStr());
		}
		if (poiLocation.hasPoi()) {
			sb.append("\nPoi:");
			sb.append(poiLocation.getPoi());
		} else {
			sb.append("noPoi information");
		}
		Log.d("", sb.toString());
	}

	@Override
	public void onMapLoadFinish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapMoveFinish() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onClickMapPoi(MapPoi mapPoiInfo) {
		// TODO Auto-generated method stub
		String title = "";
		if (mapPoiInfo != null) {
			title = mapPoiInfo.strText;
			showToast(title);
		}
	}

	@Override
	public void onGetCurrentMap(Bitmap b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapAnimationFinish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetPoiDetailSearchResult(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetPoiResult(MKPoiResult result, int arg1, int arg2) {
		String info = "";
		if (null == result) {
			showToast("没有POI结果!");
			return;
		}
		// 清除地图上已有的所有覆盖物
		// mapView.getOverlays().clear();
		// PoiOverlay是baidu map api提供的用于显示POI的Overlay
		PoiOverlay poioverlay = new PoiOverlay(MainActivity.this, mMapView);
		// 在地图上显示PoiOverlay（将搜索到的兴趣点标注在地图上）
		poioverlay.setData(result.getAllPoi());
		// 为地图添加覆盖物
		mMapView.getOverlays().add(poioverlay);
		// 刚开始忘记加这几句代码，地图一直没改变，纠结了很长时间
		if (result.getNumPois() > 0) {
			// 设置其中一个搜索结果所在地理坐标为地图的中心
			MKPoiInfo poiInfo = result.getPoi(0);
			mMapController.setCenter(poiInfo.pt);
		}

		// 遍历当前页返回的搜索结果（默认只返回10个）
		for (MKPoiInfo poiInfo : result.getAllPoi()) {
			info = info + "\n" + "名称:" + poiInfo.name + "\n" + "地址:" + poiInfo.address + "\n" + "城市:" + poiInfo.city;
		}

		// 用AlertDialog来显示搜索到的内容
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("搜索结果");
		builder.setMessage(info);
		builder.setPositiveButton("关闭", new android.content.DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.show();
	}

	@Override
	public void onGetShareUrlResult(MKShareUrlResult arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void showToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mToast != null) {
					mToast.setText(message);
					mToast.setDuration(Toast.LENGTH_SHORT);
				} else {
					mToast = Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT);
					mToast.setGravity(Gravity.CENTER, 0, 0);
					mToast.show();
				}
				View iView = mToast.getView();
				mToast.show();
				mToast.setView(iView);
			}
		});
	}

	/**
	 * 重新定位
	 */
	public void requestNewLocation() {
		mLocationClient.requestLocation();
	}

	public void searchPoi() {

		String keyWord = mKeyword.getText().toString().trim();

		if (keyWord.length() > 0) {

			if (mPoint != null) {

				mKSearch.poiSearchNearBy(keyWord, mPoint, 3000);

			} else {

				showToast("未找到你的当前位置，请定位后在使用");

			}

		} else {

			showToast("请输入关键字后在查询");

		}

	}
}
