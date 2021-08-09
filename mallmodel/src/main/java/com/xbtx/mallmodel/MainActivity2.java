package com.xbtx.mallmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    private ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = findViewById(R.id.image);
        WeatherChartView line_char = findViewById(R.id.line_char);
        // 设置白天温度曲线
        line_char .setTempDay(new int[]{10,30,20,14,28,33});
        // 设置夜间温度曲线
        line_char .setTempNight(new int[]{7,11,19,22,4,10});
        line_char .invalidate();

        animator = ObjectAnimator.ofFloat(image, "rotation", 0, 360);
        animator.setDuration(500);
        animator.setRepeatCount(ObjectAnimator.INFINITE);//无限循环次数
        animator.setRepeatMode(ValueAnimator.RESTART);//代表动画执行一次结束之后从头开始，如果是reverse那就是反向执行
//        animator.setInterpolator(new DecelerateInterpolator());//减速
        animator.setInterpolator(new LinearInterpolator());//匀速
        animator.setAutoCancel(true);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("MainActivity2", "结束");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}