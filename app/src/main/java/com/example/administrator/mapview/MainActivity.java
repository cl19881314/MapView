package com.example.administrator.mapview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gaodemaplib.GaodeMapView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tencentMapBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, TencentMapView.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim);
            }
        });
        findViewById(R.id.aMapBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GaodeMapView.class);
                startActivity(intent);
//                overridePendingTransition(R.anim);
            }
        });
    }
}
