package com.xbtx.mallmodel;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.Arrays;

public class ThirdActivity extends AppCompatActivity {

    private CountDownProgressBar cpb_countdown;
    private static final int RE_GET_BOUNT = 2;
    private int start = 0;
    private int[] colorArray = new int[]{Color.parseColor("#ffc917"),
            Color.parseColor("#ff211a")};
    private ImageView zhuan;
    private ImageView lottery;

    private int mCount = 6;
    private String[] mStrings = new String[]{"3", "6", "2",
            "4", "1", "5"};
    private int[] angles = new int[mCount];
    private int sweepAngle;
    private int startAngle;
    private int rotateToPosition;
    private ObjectAnimator animator;
    private int position;

    private Handler mHander = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case RE_GET_BOUNT:
                    start = -1;
                    Log.e("ThirdActivity", "结束了");
                    break;

                default:

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button btu = findViewById(R.id.btu);
        cpb_countdown = findViewById(R.id.cpb_countdown);
        zhuan = findViewById(R.id.zhuan);
        lottery = findViewById(R.id.lottery);
        timeGetBouns();
        btu.setOnClickListener(v -> {
            start += 1;
            Log.e("ThirdActivity", "start:" + start);
            cpb_countdown.setDuration(5000, start * 100, (start + 1) * 100, () -> {
                if (cpb_countdown != null) {
                    cpb_countdown.setBackground(getResources().getDrawable(R.drawable.red_bag));
//                    cpb_countdown.setSecondColor(getResources().getColor(R.color.transparent));
                    cpb_countdown.setRestart();

                    mHander.sendEmptyMessageDelayed(RE_GET_BOUNT, 0);
                }
            });
        });
        String s = formatNum("19089", false);
        Log.e("ThirdActivity", s);
        lottery.setOnClickListener(v -> {
            String s1 = "3";
            int pos = 0;

            for (int i = 0; i < mStrings.length; i++) {
                if (s1.equals(mStrings[i])) {
                    pos = i;
                }
            }
            load(pos + 2);
        });
    }

    private void timeGetBouns() {
        try {
            cpb_countdown.setBackground(getResources().getDrawable(R.drawable.red_bag));
            cpb_countdown.setFirstColor(getResources().getColor(R.color.white));
            cpb_countdown.setSecondColor(getResources().getColor(R.color.color_FA8072));
            cpb_countdown.setMaxValue(600);
            cpb_countdown.setColorArray(colorArray);
            cpb_countdown.setShowGradient(true);
            cpb_countdown.setDuration(4000, start, (start + 1) * 100, () -> {
                if (cpb_countdown != null) {
                    cpb_countdown.setBackground(getResources().getDrawable(R.drawable.red_bag));
//                    cpb_countdown.setSecondColor(getResources().getColor(R.color.transparent));
                    cpb_countdown.setRestart();

                    mHander.sendEmptyMessageDelayed(RE_GET_BOUNT, 1000);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void load(int a) {
        sweepAngle = 360 / mCount;
        startAngle = 0;
        for (int i1 = 0; i1 < mStrings.length; i1++) {
            angles[i1] = startAngle;
            Log.d("MainActivity", "onDraw: " + angles[i1] + "     " + i1);
            startAngle += sweepAngle;
        }
        rotateToPosition = 360 / mCount * (mCount - a);
        Log.e("MainActivity", "rotateToPosition:" + rotateToPosition);

        float toDegree = 360f * 5 + rotateToPosition;

        animator = ObjectAnimator.ofFloat(zhuan, "rotation", 0, toDegree);
        animator.setDuration(5000);
        animator.setRepeatCount(0);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setAutoCancel(true);
        animator.start();

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rotateToPosition = 270 - rotateToPosition;
                if (rotateToPosition < 0) {
                    rotateToPosition += 360;
                } else if (rotateToPosition == 0) {
                    rotateToPosition = 270;
                }
                position = -Arrays.binarySearch(angles, rotateToPosition) - 1;
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
     * <pre>
     * 数字格式化显示
     * 小于万默认显示 大于万以1.7万方式显示最大是9999.9万
     * 大于亿以1.1亿方式显示最大没有限制都是亿单位
     * make by dongxh 2017年12月28日上午10:05:22
     * </pre>
     *
     * @param num   格式化的数字
     * @param kBool 是否格式化千,为true,并且num大于999就显示999+,小于等于999就正常显示
     * @return
     */
    public static String formatNum(String num, Boolean kBool) {
        StringBuffer sb = new StringBuffer();
        if (kBool == null)
            kBool = false;

        BigDecimal b0 = new BigDecimal("1000");
        BigDecimal b1 = new BigDecimal("10000");
        BigDecimal b2 = new BigDecimal("100000000");
        BigDecimal b3 = new BigDecimal(num);

        String formatNumStr = "";
        String nuit = "";

        // 以千为单位处理
        if (kBool) {
            if (b3.compareTo(b0) == 0 || b3.compareTo(b0) == 1) {
                return "999+";
            }
            return num;
        }

        // 以万为单位处理
        if (b3.compareTo(b1) == -1) {
            sb.append(b3.toString());
        } else if ((b3.compareTo(b1) == 0 && b3.compareTo(b1) == 1)
                || b3.compareTo(b2) == -1) {
            formatNumStr = b3.divide(b1).toString();
            nuit = "万";
        } else if (b3.compareTo(b2) == 0 || b3.compareTo(b2) == 1) {
            formatNumStr = b3.divide(b2).toString();
            nuit = "亿";
        }
        if (!"".equals(formatNumStr)) {
            int i = formatNumStr.indexOf(".");
            if (i == -1) {
                sb.append(formatNumStr).append(nuit);
            } else {
                i = i + 1;
                String v = formatNumStr.substring(i, i + 1);
                if (!v.equals("0")) {
                    sb.append(formatNumStr.substring(0, i + 1)).append(nuit);
                } else {
                    sb.append(formatNumStr.substring(0, i - 1)).append(nuit);
                }
            }
        }
        if (sb.length() == 0)
            return "0";
        return sb.toString();
    }

}