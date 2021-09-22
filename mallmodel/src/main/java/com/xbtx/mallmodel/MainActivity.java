package com.xbtx.mallmodel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lzj.pass.dialog.PayPassDialog;
import com.lzj.pass.dialog.PayPassView;
import com.xbtx.mylibrary.ARouterPath;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Route(path = ARouterPath.PATH_MALLMODEL_MAIN)
public class MainActivity extends AppCompatActivity {

    private TextView content;
    @Autowired()
    String name;
    private Button payDialog;
    private RatingBarView ratingBar;
    private String string = "";
    Runnable runnable;
    NoLeakHandler handler;
    private String shi = "碧玉_成__高,万条_下绿__";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mallmodel_activity_main);
        ARouter.getInstance().inject(this);
        content = findViewById(R.id.content);
        payDialog = findViewById(R.id.pay_dialog);
        ratingBar = findViewById(R.id.ratingBar);
        Button second = findViewById(R.id.second);
        content.setText(name);
        content.setText(Html.fromHtml("英雄名:芈月<br/>英雄场次:48<br/>标准场次:589<br/>英雄胜率:%<br/>,英雄名:<br/>英雄场次:29<br/>标准场次:26<br/>英雄胜率:54.4%"));
        int a = 1;
        double b = 1.01;
        Log.e("MainActivity", a > b ? "大于" : "小于");
        handler = new NoLeakHandler(this);
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.e("MainActivity", "更新");
                handler.postDelayed(this, 1000);
            }
        };
        payDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                payDialog();
                handler.postDelayed(runnable, 0);
                // handler.sendEmptyMessage(1);
            }
        });
        Log.d("MainActivity", "string.isEmpty():" + string.isEmpty());
        second.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        });

        String tt = "日出江花红胜火,春来江水绿如蓝";

        String regEx="[`~!@#$%^&*()+=|{}':;'\\[\\].<>/?~！@#￥%……&*（）——+|{}【】'；：”“’。、？,，]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(shi);
        String toSpeechText=m.replaceAll("").trim();
        Log.i("lgq","ww正则==="+toSpeechText);

        Pattern pattern = Pattern.compile("\t|\r|\n|\\s*");
        Matcher matcher = pattern.matcher(toSpeechText);
        String dest = matcher.replaceAll("");

        Log.e("lgq","ww正则222==="+dest);

        String[] split = dest.split("");
        Log.e("MainActivity", "split.length:" + split.length);
        for (int i = 1; i < split.length; i++) {
            Log.e("MainActivity", "----------"+split[i]);
        }
        String str="<p>就是感觉很迷茫，没有目标，</p><p>不知道自己喜欢什么，想知道大家怎么找到目标的，怎么知道自己热爱得是什么呢？</p>";
//        String s = str.replaceAll("<p>", "");
//        String s1 = s.replaceAll("</p>", "");
//        Log.e("MainActivity", s1);
        Log.e("MainActivity", removeHtmlTag(str));
    }

    /**
     * 去除富文本编辑器标签
     *
     * @param inputString
     * @return
     */
    public static String removeHtmlTag(String inputString) {
        if (inputString == null)
            return null;
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        try {
            //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textStr;// 返回文本字符串
    }

    private static class NoLeakHandler extends Handler {
        private WeakReference<MainActivity> mActivity;

        public NoLeakHandler(MainActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = mActivity.get();
            if (activity != null) {
                if (!activity.isFinishing()) {
                    Log.e("NoLeakHandler", "msg.what:" + msg.what);
                }
            }
        }
    }

    //1 默认方式(推荐)
    private void payDialog() {
        final PayPassDialog dialog = new PayPassDialog(this);
        dialog.getPayViewPass()
                .setRandomNumber(true)
                .setPayClickListener(new PayPassView.OnPayClickListener() {
                    @Override
                    public void onPassFinish(String passContent) {
                        //6位输入完成回调
                    }

                    @Override
                    public void onPayClose() {
                        dialog.dismiss();
                        //关闭弹框
                    }

                    @Override
                    public void onPayForget() {
                        dialog.dismiss();
                        //点击忘记密码回调
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}