package com.example.gaodemaplib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

/**
 * Created by Administrator on 2017/7/7.
 */

public class GaodeMapView extends AppCompatActivity implements GaodeLocationHelper.AMapLocationListener {
    private MapView mMapView;
    private AMap mMap;
    private GaodeLocationHelper mLocationHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaode_map_view_layout);
        initMap(savedInstanceState);
        init();
    }

    private void init() {
        findViewById(R.id.reLocalBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("gnt","-gnt->onRefresh");
                mLocationHelper.refreshLocation();
            }
        });
    }

    private void initMap(Bundle savedInstanceState) {
        mMapView = (MapView) findViewById(R.id.MapView);
        mMapView.onCreate(savedInstanceState);
        mMap = mMapView.getMap();
        mLocationHelper = new GaodeLocationHelper(this,mMap);
        mLocationHelper.setAMapLocationListener(this);
    }

    @Override
    protected void onResume() {
        super.onPause();
        mMapView.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onStop();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void locationSuccess(AMapLocation aMapLocation) {
        mMap.clear();
        LatLng point = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,17));
        MarkerOptions option = new MarkerOptions();
        option.position(point);
        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
        Marker marker = mMap.addMarker(option);
        marker.setObject("点击Marker，传递过去信息");
    }
}
