package com.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * WIFI扫描与连接
 * 热点设置密码开启等
 */
public class MainActivity extends AppCompatActivity {
    private Button mBtWifiScan,mBtWifiAp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        mBtWifiScan = (Button) findViewById(R.id.bt_scan_wifi);
        mBtWifiAp = (Button) findViewById(R.id.bt_start_ap);
        mBtWifiScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(MainActivity.this, WifiListActivity.class);
                startActivity(in);
            }
        });

        mBtWifiAp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(MainActivity.this, WifiApActivity.class);
                startActivity(in);
            }
        });
    }


}
