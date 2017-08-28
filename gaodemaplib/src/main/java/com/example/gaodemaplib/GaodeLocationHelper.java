package com.example.gaodemaplib;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;

/**
 * Created by Administrator on 2017/7/7.
 */

public class GaodeLocationHelper implements AMapLocationListener, LocationSource {

    LocationSource.OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    private Context mContext;
    private AMap mMap;
    private AMapLocationListener mLoctionListener;
    public GaodeLocationHelper(Context context,AMap map){
        mMap = map;
        mContext = context;
        mMap.setLocationSource(this);
        mMap.setMyLocationEnabled(true);
    }
    /**
     * 激活定位
     */
    @Override
    public void activate(LocationSource.OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(mContext);
            mlocationClient.setLocationListener(this);
            mLocationOption = new AMapLocationClientOption();
            mLocationOption.setNeedAddress(true);
            // 使用连续
//            mLocationOption.setOnceLocation(false);
//            mLocationOption.setInterval(10 * 1000);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();//启动定位
        }
    }
    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        Log.e("gnt","-gnt->deactivate");
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    public void refreshLocation(){
//        mlocationClient.setLocationOption(mLocationOption);
//        mlocationClient.startLocation();//启动定位
        deactivate();
        activate(mListener);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        Log.e("gnt","-gnt->"+ aMapLocation.toString());
        if (aMapLocation.getErrorCode() == AMapLocation.LOCATION_SUCCESS){
            if (mLoctionListener != null) {
                mLoctionListener.locationSuccess(aMapLocation);
                //mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                mlocationClient.stopLocation();
            }
        }
    }

    public void setAMapLocationListener(AMapLocationListener locationListener){
        mLoctionListener = locationListener;
    }
    interface AMapLocationListener{
        void locationSuccess(AMapLocation aMapLocation);
    }
}
