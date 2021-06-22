package com.xbtx.mallmodel;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lzj.pass.dialog.PayPassDialog;
import com.lzj.pass.dialog.PayPassView;
import com.xbtx.mylibrary.ARouterPath;

@Route(path = ARouterPath.PATH_MALLMODEL_MAIN)
public class MainActivity extends AppCompatActivity {

    private TextView content;
    @Autowired()
    String name;
    private Button payDialog;
    private RatingBarView ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mallmodel_activity_main);
        ARouter.getInstance().inject(this);
        content = findViewById(R.id.content);
        payDialog = findViewById(R.id.pay_dialog);
        ratingBar = findViewById(R.id.ratingBar);
        content.setText(name);
        int a = 1;
        double b = 1.01;
        Log.e("MainActivity", a > b ? "大于" : "小于");
        payDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payDialog();
            }
        });
    }

    //1 默认方式(推荐)
    private void payDialog() {
        final PayPassDialog dialog=new PayPassDialog(this);
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
}