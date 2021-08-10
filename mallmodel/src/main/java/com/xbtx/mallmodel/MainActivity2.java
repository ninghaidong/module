package com.xbtx.mallmodel;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    private ObjectAnimator animator;
    MySecondLineChartView chartView;
    List<String> xValues;   //x轴数据集合
    List<Integer> yValues;  //y轴数据集合
    List<Integer> ySecondValues;  //y轴数据集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = findViewById(R.id.image);
        WeatherChartView line_char = findViewById(R.id.line_char);
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
}