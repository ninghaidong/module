package com.xbtx.mallmodel;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 宁
 * @className:
 * @date: 2021/8/3 20:37
 */
public class SecondActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> stringList;
    private String link = "http://img.xunbao88.com.cn/download/wechatmsg/voice/20210726/20210726_guangnianzhiwai.mp3";
    private String secondLink = "http://img.xunbao88.com.cn/download/wechatmsg/voice/20210726/20210726_baiyueguang.mp3";
    private MediaPlayer player;
    private boolean ifplay = false;
    private Button play;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        play = findViewById(R.id.play);
        StudyBendLine stubline = findViewById(R.id.stubline);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        recyclerView = findViewById(R.id.recyclerView);
        stringList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringList.add("阿栋" + i);
        }
        SecondAdapter secondAdapter = new SecondAdapter(stringList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(secondAdapter);
        player = new MediaPlayer();
        play.setOnClickListener(v -> {
            playAudio(link);
        });
        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(7);
        integerList.add(1);
        integerList.add(15);
        integerList.add(24);
        integerList.add(12);
        integerList.add(31);
        ArrayList<String> integerList1 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            integerList1.add("1."+i);
        }
        stubline.updateTime(integerList,integerList1);
    }

    public void playAudio(String url) {
        if (player != null) {
            //player.reset();
            try {
                player.setDataSource(url);
                player.prepare();// 准备
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.start();// 开始
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.stop();
            player.release();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            if (player.isPlaying()) {
                player.pause();
            }
        }
    }
}