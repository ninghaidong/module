package com.xbtx.mallmodel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xbtx.mylibrary.ARouterPath;

@Route(path = ARouterPath.PATH_MALLMODEL_MAIN)
public class MainActivity extends AppCompatActivity {

    private TextView content;
    @Autowired()
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mallmodel_activity_main);
        ARouter.getInstance().inject(this);
        content = findViewById(R.id.content);
        content.setText(name);
    }
}