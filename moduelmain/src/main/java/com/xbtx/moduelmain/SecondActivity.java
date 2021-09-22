package com.xbtx.moduelmain;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements DownloadAudio.DownloadAudioListener {
    private MediaPlayer mediaPlayer;

    private Handler handler;

    private List<String> demolist = new ArrayList<>();//你获取的网络数据

    private DownloadAudio downloadAudio;

    private List<String> audiolist = new ArrayList<>();

    private String[] audionamelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button playBtu = findViewById(R.id.playBtu);
        initView();
        playBtu.setOnClickListener(v -> {
            //可以设置加载动画，下载完后调用play()

            play();
        });
    }

    private void initView() {

        handler = new Handler();

        String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Demo";
        Log.e("SecondActivity", savePath);

        demolist.add("https://mp.xunbao88.com.cn/appweb/static/sleep/mp3/8.mp3");
        demolist.add("https://mp.xunbao88.com.cn/appweb/static/sleep/mp3/12.mp3");
        demolist.add("https://mp.xunbao88.com.cn/appweb/static/sleep/mp3/21.mp3");
        for (int i = 0; i < demolist.size(); i++) {

            audiolist.add(StringUtil.makeMd5(demolist.get(i)));//将网络下载url转成md5

        }

        audionamelist = new String[demolist.size()];

        downloadAudio = new DownloadAudio(this, savePath, audiolist);

        //将下载的url转成MD5，下载的文件名为MD5。首次进来拿本地下载好的文件名和网络拿下来的数据对比，

        //没有的就下载，有的就返回文件路径就行

        if (demolist.size() > 0) {

            downloadAudio.isDownloadAudio(demolist.get(0), 0);

        }



    }

    private void play() {

        handler.removeCallbacks(playAudioRun);

        handler.post(playAudioRun);

    }

    private int curItem;//自己去拿你的播放哪一个的下标

    Runnable playAudioRun = new Runnable() {

        @Override

        public void run() {

            if (audionamelist != null) {

                if (audionamelist[curItem] != null) {

                    if (!audionamelist[curItem].equals("failure") && !audionamelist[curItem].equals("")) {

                        playAudio(audionamelist[curItem]);//播放本地路径

                    } else {

                        playAudio(demolist.get(curItem));//下载失败或者本地找不到路径就网络播放

                    }

                } else {

                    playAudio(demolist.get(curItem));//下载失败或者本地找不到路径就网络播放

                }

            } else {

                playAudio(demolist.get(curItem));//下载失败或者本地找不到路径就网络播放

            }

        }

    };

    private void playAudio(String url) {

        mediaPlayer = new MediaPlayer();

        try {

            mediaPlayer.setDataSource(url);

            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        } catch (IOException e) {

            e.printStackTrace();

        }

        if (mediaPlayer == null) {

            return;

        }

        mediaPlayer.setLooping(false);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override

            public void onCompletion(MediaPlayer mp) {

                mediaPlayer.stop();

                mediaPlayer.reset();

            }

        });

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override

            public void onPrepared(MediaPlayer mp) {

                if (mediaPlayer == mp) {

                    mediaPlayer.start();

                }

            }

        });

        mediaPlayer.prepareAsync();

    }

    @Override

    public void DownloadSuccess(String audiourl, int i) {

        if (i < demolist.size()) {

            audionamelist[i] = audiourl;

            i++;

            if (i < demolist.size()) {

                downloadAudio.isDownloadAudio(demolist.get(i), i);

            }

        }

    }

    @Override

    public void DownloadFailure(String audiourl, int i) {

        if (i < demolist.size()) {

            audionamelist[i] = audiourl;

            i++;

            if (i < demolist.size()) {

                downloadAudio.isDownloadAudio(demolist.get(i), i);

            }

        }

    }

}