package com.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.github.mayubao.pay_library.AliPayAPI;
import io.github.mayubao.pay_library.AliPayReq;
import io.github.mayubao.pay_library.PayAPI;
import io.github.mayubao.pay_library.WechatPayReq;


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


    /**
     * 微信支付例子
     */
    private void wXpay(){
//        WechatPayReq wechatPayReq = new WechatPayReq.Builder()
//                .with(this) //activity实例
//                .setAppId(appid) //微信支付AppID
//                .setPartnerId(partnerid)//微信支付商户号
//                .setPrepayId(prepayid)//预支付码
////                              .setPackageValue(wechatPayReq.get)//"Sign=WXPay"
//                .setNonceStr(noncestr)
//                .setTimeStamp(timestamp)//时间戳
//                .setSign(sign)//签名
//                .create();
//        //2.发送微信支付请求
//        PayAPI.getInstance().sendPayRequest(wechatPayReq);
        //关于微信支付的回调
        //wechatPayReq.setOnWechatPayListener(new OnWechatPayListener);
    }


    /**
     * 支付宝支付
     */
    private void zhiFubaoPAy(){

        //appId:2017032906470394
//        AliPayAPI.Config config = new AliPayAPI.Config.Builder()
//                .setRsaPrivate(rsa_private) //设置私钥 (商户私钥，pkcs8格式)
//                .setRsaPublic(rsa_public)//设置公钥(// 支付宝公钥)
//                .setPartner(partner) //设置商户
//                .setSeller(seller) //设置商户收款账号
//                .create();

        //2.创建支付宝支付请求
//        AliPayReq aliPayReq = new AliPayReq.Builder()
//                .with(this)//Activity实例
//                .apply(config)//支付宝支付通用配置
//                .setOutTradeNo(outTradeNo)//设置唯一订单号
//                .setPrice(price)//设置订单价格
//                .setSubject(orderSubject)//设置订单标题
//                .setBody(orderBody)//设置订单内容 订单详情
//                .setCallbackUrl(callbackUrl)//设置回调地址
//                .create()//
//                .setOnAliPayListener(null);//

        //3.发送支付宝支付请求
//        PayAPI.getInstance().sendPayRequest(aliPayReq);

        //关于支付宝支付的回调
        //aliPayReq.setOnAliPayListener(new OnAliPayListener);



    }






}
