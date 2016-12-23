package com.mymedioaudio;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by smark on 2016/12/23.
 */

public class MusicService extends Service implements MediaPlayer.OnCompletionListener {

    private MediaPlayer player;//声明一个MediaPlayer对象
    public static final String ACTION_UPDATE_PROGRESS="0";


    @Override
    public IBinder onBind(Intent arg0) {
        // TODO 自动生成的方法存根
        return null;
    }



    //创建服务
    @Override
    public void onCreate() {
        // 当player对象为空时
        if (player == null) {

            player = new MediaPlayer();
//            player = MediaPlayer.create(MusicService.this, Uri
//                    .parse("http://219.138.125.22/myweb/mp3/CMP3/JH19.MP3"));//实例化对象，通过播放本机服务器上的一首音乐
            player.setLooping(false);//设置不循环播放
        }

        super.onCreate();
    }

    //销毁服务
    @Override
    public void onDestroy() {
        //当对象不为空时
        if (player != null) {
            player.stop();//停止播放
            player.release();//释放资源
            player = null;//把player对象设置为null
        }
        super.onDestroy();
    }

    //开始服务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO 自动生成的方法存根
        Bundle b = intent.getExtras();//获取到从MainActivity类中传递过来的Bundle对象
        int op = b.getInt("msg");//再获取到MainActivity类中op的值
        switch (op) {
            case 1://当op为1时，即点击播放按钮时
                play();//调用play()方法
                break;
            case 2://当op为2时，即点击暂停按钮时
                pause();//调用pause()方法
                break;
            case 3://当op为3时，即点击停止按钮时
                stop();//调用stop()方法
                break;
            case 4://销毁服务
                stopSelf();
                break;
            default:
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //停止播放音乐方法
    private void stop() {
        // 当player对象不为空时
        if (player != null) {
            player.seekTo(0);//设置从头开始
            player.stop();//停止播放
            try {
                player.prepare();//预加载音乐
            } catch (IllegalStateException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    //暂停播放音乐方法
    private void pause() {
        // 当player对象正在播放时并且player对象不为空时
        if (player.isPlaying() && player != null) {
            player.pause();//暂停播放音乐
        }
    }

    //播放音乐方法
    private void play() {
        // 当player对象不为空并且player不是正在播放时
        if (player != null && !player.isPlaying()) {
            try {
                player.reset();
                player.setDataSource("http://vip2.dailyedu.com/uploads/video/17.mp4");
                player.prepare();//prepare之后自动播放
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.start();//开始播放音乐
        }
    }


    private void toUpdateProgress(){
        if(player != null &&player.isPlaying()){
            int progress = player.getCurrentPosition();
            Intent intent = new Intent();
            intent.setAction(ACTION_UPDATE_PROGRESS);
            intent.putExtra(ACTION_UPDATE_PROGRESS,progress);
            sendBroadcast(intent);
            Log.e("123","154");
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        toUpdateProgress();
    }
}
