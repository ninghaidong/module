package com.xbtx.mallmodel;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
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
        Button scroBtu = findViewById(R.id.scroBtu);
        NumberFlipView btNumber = findViewById(R.id.btNumber);
        GoodProgressView progress_view = findViewById(R.id.progress_view);
        ObservableScrollView scrollView = findViewById(R.id.scrollView);
        stringList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            stringList.add("阿栋" + i);
        }
        SecondAdapter secondAdapter = new SecondAdapter(stringList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(secondAdapter);
        scroBtu.setOnClickListener(v -> {
            recyclerView.scrollToPosition(0);
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState ==RecyclerView.SCROLL_STATE_IDLE){
                    //暂停
                    Log.e("SecondActivity", "停止了");
                }
                if (newState==RecyclerView.SCROLL_STATE_DRAGGING){
                    //滑动
                }

            }
        });
        scrollView.setOnScrollStatusListener(new ObservableScrollView.OnScrollStatusListener(){
            @Override
            public void onScrollStop(){
                //滑动停止 1秒后显示按钮
//                if(isHasPos !=1){    //后台返回数据  判断是否需要展示这个按钮
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            iv_cj_guide.setVisibility(View.VISIBLE);
//                        }
//                    }, 1000);
//                }
                Log.e("SecondActivity", "滑动停止");
            }

            @Override
            public void onScrolling(){
                //滑动中 隐藏按钮
//                if(isHasPos!=1){ //后台返回数据  判断是否需要展示这个按钮
//
//                    iv_cj_guide.setVisibility(View.GONE);
//                }

            }
        });


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
            integerList1.add("1." + i);
        }
        stubline.updateTime(integerList, integerList1);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.e("MainActivity", "更新");
                btNumber.jumpNumber();
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 0);
        progress_view.setProgressValue(80);

        Student.StudentBuilder studentBuilder = new Student.StudentBuilder(1, "阿丁");
        Student build = studentBuilder.build();
        build.Toast(this);

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