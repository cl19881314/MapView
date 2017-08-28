package com.example.tencentmaplib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

/**
 * Created by Administrator on 2017/7/6.
 */

public class TencentMapView extends AppCompatActivity {
    private MapView mMapView;
    private TencentMap mMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tencentmapview_layout);
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        initMap();
    }

    private void initMap() {
        mMap = mMapView.getMap();
        mMapView.getUiSettings().setScaleControlsEnabled(true);

        TencentLocationManager locationManager =  TencentLocationManager.getInstance(this);
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setAllowGPS(true);
        request.setInterval(2000);
        request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_NAME);

        int errorNim = locationManager.requestLocationUpdates(request, new TencentLocationListener() {
            @Override
            public void onLocationChanged(TencentLocation tencentLocation, int error, String s) {
                Log.e("gnt", "-gnt->" + tencentLocation.toString());
                if (TencentLocation.ERROR_OK == error) {
                    Log.e("gnt", "-gnt->" + tencentLocation.getAddress());
                    mMap.clearAllOverlays();
                    LatLng lo = new LatLng(tencentLocation.getLatitude(),tencentLocation.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lo,17));
                    MarkerOptions options = new MarkerOptions();
                    options.position(lo);
                    options.title("提示");
                    options.snippet(tencentLocation.getAddress());
                    Marker marker = mMap.addMarker(options);
                    marker.setTag("传递过去数据");
                }
            }

            @Override
            public void onStatusUpdate(String s, int i, String s1) {
                Log.e("gnt", "-gnt->" + s);
            }
        });
        Log.e("gnt","gnt->"+errorNim);
    }

    @Override
    protected void onRestart() {
        super.onStart();
        mMapView.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
