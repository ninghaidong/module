package com.xbtx.mallmodel;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class MainActivity2 extends AppCompatActivity {
    private ObjectAnimator animator;
    MySecondLineChartView chartView;
    List<String> xValues;   //x轴数据集合
    List<Integer> yValues;  //y轴数据集合
    List<Integer> ySecondValues;  //y轴数据集合
    private SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy.MM.dd");
    private List<String> stringList = new ArrayList<>();
    private static final String url = "http://img.xunbao88.com.cn/download/wechatmsg/video/20210726/kongbu3.mp4?v=1";
    private JzvdStd jzvdStd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = findViewById(R.id.image);
        RelativeLayout relayout = findViewById(R.id.relayout);
        RelativeLayout relayout_bg = findViewById(R.id.relayout_bg);
//        relayout.setBackground(null);
        WeatherChartView line_char = findViewById(R.id.line_char);
        RecyclerView rv_diagram = findViewById(R.id.rv_diagram);
        ImageView image_bg = findViewById(R.id.image_bg);
        FrameLayout fraglayout = findViewById(R.id.fraglayout);
        int screenWidth = getScreenWidth();

        jzvdStd =  findViewById(R.id.jz_video);
        ViewGroup.LayoutParams layoutParams = jzvdStd.getLayoutParams();
        layoutParams.height = screenWidth /16*9;
        jzvdStd.setLayoutParams(layoutParams);
        // 切换 ijk 内核
        jzvdStd.releaseAllVideos();
        JzvdStd.SAVE_PROGRESS = false;


        jzvdStd.setUp(url, "买就完事了哦！铁汁们！", Jzvd.SCREEN_NORMAL);
//        Glide.with(this).load("https://mp.xunbao88.com.cn/appweb/static/task_other/lanse@3x.png").into(jzvdStd.posterImageView);
        jzvdStd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);

        jzvdStd.startVideo();

        // 设置白天温度曲线
        line_char.setTempDay(new int[]{10, 30, 20, 31, 28, 33, 10, 30, 20, 31, 28, 33});
        // 设置夜间温度曲线
        line_char.setTempNight(new int[]{7, 11, 13, 12, 4, 10, 7, 11, 13, 12, 4, 10});
        line_char.invalidate();

        chartView = findViewById(R.id.secondLine);
        xValues = new ArrayList<>();
        yValues = new ArrayList<>();
        ySecondValues = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            xValues.add(i + "");
        }
        yValues.add(26);
        yValues.add(30);
        yValues.add(24);
        yValues.add(28);
        yValues.add(27);
        yValues.add(33);
        yValues.add(30);
        yValues.add(29);
        yValues.add(26);
        yValues.add(22);
        yValues.add(29);
        yValues.add(33);

        ySecondValues.add(7);
        ySecondValues.add(11);
        ySecondValues.add(13);
        ySecondValues.add(12);
        ySecondValues.add(4);
        ySecondValues.add(10);
        ySecondValues.add(7);
        ySecondValues.add(11);
        ySecondValues.add(13);
        ySecondValues.add(12);
        ySecondValues.add(4);
        ySecondValues.add(10);
        // xy轴集合自己添加数据
        chartView.setXValues(xValues);
        chartView.setYValues(yValues);
        chartView.setYSecondValues(ySecondValues);


//        animator = ObjectAnimator.ofFloat(image, "rotation", 0, 360);
//        animator.setDuration(500);
//        animator.setRepeatCount(ObjectAnimator.INFINITE);//无限循环次数
//        animator.setRepeatMode(ValueAnimator.RESTART);//代表动画执行一次结束之后从头开始，如果是reverse那就是反向执行
////        animator.setInterpolator(new DecelerateInterpolator());//减速
//        animator.setInterpolator(new LinearInterpolator());//匀速
//        animator.setAutoCancel(true);
//        animator.start();
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Log.e("MainActivity2", "结束");
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
        image.setX(dp2px(100));
        image.setY(dp2px(200));
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());

        Log.e("---", "Date获取当前日期时间" + FORMAT.format(date));
        stringList.add("https://mp.xunbao88.com.cn/appweb/static/task_other/yu@3x.png");
        stringList.add("https://mp.xunbao88.com.cn/appweb/static/task_other/duoyun@3x.png");
        Glide.with(this).load("https://mp.xunbao88.com.cn/appweb/static/task_other/lanse@3x.png").into(image_bg);
        for (String s : stringList) {
            ImageView imageView = new ImageView(this);
            Glide.with(this).load(s).into(imageView);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//            lp.rightMargin=30;
//            lp.topMargin=30;
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            imageView.setLayoutParams(lp);
            relayout_bg.addView(imageView);
        }
    }

    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public int dp2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 生成一个0 到 count 之间的随机数
     *
     * @param endNum
     * @return
     */
    public static int getNum(int endNum) {
        if (endNum > 0) {
            Random random = new Random();
            return random.nextInt(endNum);
        }
        return 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (jzvdStd!=null){
            jzvdStd.reset();
        }

        JzvdStd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}