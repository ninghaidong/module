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
            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
        });

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