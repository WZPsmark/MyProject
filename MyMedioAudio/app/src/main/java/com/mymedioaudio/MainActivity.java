package com.mymedioaudio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;


/**
 * 实现音频播放，加service与不加service两种方法
 */
public class MainActivity extends AppCompatActivity {
    private Button btnPause, btnPlayUrl, btnStop;
    private SeekBar skbProgress;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlayUrl = (Button) this.findViewById(R.id.btnPlayUrl);
        btnPlayUrl.setOnClickListener(new ClickEvent());
        btnPause = (Button) this.findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new ClickEvent());
        btnStop = (Button) this.findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new ClickEvent());
        skbProgress = (SeekBar) this.findViewById(R.id.skbProgress);
//        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
//        player = new Player(skbProgress);
        registerReceiver();
    }


    class ClickEvent implements OnClickListener
    {
        @Override
        public void onClick(View arg0)
        {

            Intent intent = new Intent(MainActivity.this, MusicService.class);//实例化一个Intent对象
            int op = -1;//设置中间变量op

            if (arg0 == btnPause)
            {
                op = 2;
//                player.pause();
            }
            else if (arg0 == btnPlayUrl)
            {
                final String url="http://vip2.dailyedu.com/uploads/video/17.mp4";

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        player.playUrl(url);
//                    }
//                }).start();

                op = 1;

            } else if (arg0 == btnStop)
            {
                op = 3;
//                player.stop();
            }
            Bundle bundle = new Bundle();//实例化一个Bundle对象
            bundle.putInt("msg", op);//把op的值放入到bundle对象中
            intent.putExtras(bundle);//再把bundle对象放入intent对象中
            startService(intent);//开启这个服务
        }
    }

    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener
    {
        int progress;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,   boolean fromUser)
        {   // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
             this.progress = progress * player.mediaPlayer.getDuration()  / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {   // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字

             player.mediaPlayer.seekTo(progress);
        }
    }


    @Override
    protected void onDestroy() {
        Intent intent = new Intent(MainActivity.this, MusicService.class);//实例化一个Intent对象
        Bundle bundle = new Bundle();//实例化一个Bundle对象
        bundle.putInt("msg", 4);//把op的值放入到bundle对象中
        intent.putExtras(bundle);//再把bundle对象放入intent对象中
        startService(intent);//开启这个服务
        super.onDestroy();
    }


    private ProgressReceiver progressReceiver;


    private void registerReceiver(){
        progressReceiver = new ProgressReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicService.ACTION_UPDATE_PROGRESS);
        registerReceiver(progressReceiver, intentFilter);
    }




    class ProgressReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (MusicService.ACTION_UPDATE_PROGRESS.equals(action)) {
                int progress = intent.getIntExtra(MusicService.ACTION_UPDATE_PROGRESS, 0);
                if (progress > 0) {
//                    currentPosition = progress; // Remember the current position
                    skbProgress.setProgress(progress / 1000);

                    Log.e("opo","erte");
                }
            }

        }
    }
}
