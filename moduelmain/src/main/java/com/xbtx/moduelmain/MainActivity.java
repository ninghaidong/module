package com.xbtx.moduelmain;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private Activity mActivty;
    private Button buttonActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moduelmain_activity_main);
        mActivty = this;
        text = (TextView) findViewById(R.id.text);
        buttonActivity = findViewById(R.id.button_activity);
        TextView text2 = findViewById(R.id.text2);
        text.setText(Html.fromHtml("以岭连花清凉走珠，19.9<br>Mistine防晒，49<br>鼠标，9.9<br>Sakose手膜，9.9 <br>1，  <a href=\"https://uland.taobao.com/coupon/edetail?activityId=e4e4d57245a647cfb6d79d90e812c31b&itemId=633467264294\">网页链接</a>            <br>2，  <a href=\"https://uland.taobao.com/coupon/edetail?activityId=60f3f686e25f46f7a6604e26ebb2f1ce&itemId=564842070490\">网页链接</a>            <br>4，  <a href=\"https://uland.taobao.com/coupon/edetail?e=AIQO%2FxN0rRelhHvvyUNXZfh8CuWt5YH5OVuOuRD5gLJMmdsrkidbOcgNAa9X6T79uG1ZaNECU%2FQkVJFvh9f38PP7TSqNMtm6XRuv7xpz1L7QEUCpE4O7XaPu0UeHNPBWv2Ac2g3cAjwfPV0IojuzbUvPUlU7ppuZfEU6k2RDH%2BP3skwo30vk2ImZwxskUEIk0aoUfJAYAWXM4nPQrVf7aqkstQw2%2BIgckYlayRNqVZNxcXZ4uChJ07EduVXKDczHTmR4x5CnEU613%2BL5hoPgg0wNBUbTsArs&traceId=0bb6ad0216203717987052772e52c5&&union_lens=lensId:TAPI@1620371798@0b59e280_cd41_17945af7acc_8840@01;linkRuleId:1&activityId=b83363929df74574aeb1e003532f7461\">网页链接</a>            <br>"));
        text.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence str = text.getText();
        if (str instanceof Spannable) {
            int end = str.length();
            Spannable sp = (Spannable) text.getText();  //构建Spannable对象、继承Spanned、Spanned对象继承CharSequener
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);  //找出text中的a标签
            //SpannableStringBuilder、SpannableString对象跟String对象差不多、只是比String对象多setSpan，
            //可以给字符串设置样式、大小、背景色...而 SpannableStringBuilder跟SpannableString的关系就跟String跟StringBuffer关系一样
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.clearSpans();//should clear old spans
            for (URLSpan url : urls) {
                MyClickSpan myURLSpan = new MyClickSpan(url.getURL());
                //设置样式其中参数what是具体样式的实现对象，start则是该样式开始的位置，end对应的是样式结束的位置，
                // 参数 flags，定义在Spannable中的常量
                style.setSpan(myURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            text.setText(style);
        }

        buttonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //普通跳转
//                ARouter.getInstance().build("/app/SecondActivity").navigation();
                //普通传参跳转
//                ARouter.getInstance().build("/app/SecondActivity").withString("name", "阿栋").navigation();
                //对象传参跳转
                //ARouter.getInstance().build(ARouterPath.PATH_SECOND).withParcelable("bean", new Bean("阿栋", "24")).navigation();
//                ARouter.getInstance().build(ARouterPath.PATH_MALLMODEL_MAIN).withString("name", "阿栋").navigation();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        double f = 111231.5585;
        double d = 1006;
        double d1 = 100;
        Log.e("MainActivity", "1006/100:" + Double.valueOf(d / 100));
        DecimalFormat df = new DecimalFormat("#.00");

//        BigDecimal b = new BigDecimal(Double.valueOf(d / 100));
//        double f1 = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        Log.e("MainActivity", "f1:" + df.format(d / 100));


        String a = "有2年没这样想想真佩服你们现在,2年没这样想想真,2年没这样想想真佩佩服,2年没这样想想真佩服";
        SpannableString spanText = new SpannableString("图"+a);
        Drawable drawable = getResources().getDrawable(R.drawable.icon_xile_dou);
// 左上右下 控制图片大小
        drawable.setBounds(0, 0, 30, 30);


// 替换0,1的字符
        spanText.setSpan(new ImageSpan(drawable), 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//        text2.append("\n");
        text2.append(spanText);

        Log.e("MainActivity", a.substring(0, 5));

        String b="埃德加·斯诺在《红星照耀中国》一书中写道“冒险、探索、发现、勇气、胜利和狂喜、艰难困苦，英勇牺牲、忠心耿耿、这些千千万万青年人的经久不衰的热情，始终如一的希望，令人惊诧的革命乐观情绪，像一把火焰，贯穿着这一切，他们无论在人力面前，或者在大自然面前，上帝面前，死亡面前，都绝不承认失败。”这是斯诺对工农红军的万里长征的评价。";
        Log.e("MainActivity", "b.length():" + b.length());

    }

    public class MyClickSpan extends ClickableSpan {
        private String mSpan;

        MyClickSpan(String span) {
            mSpan = span;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
            ds.setColor(Color.parseColor("#3F51B5"));
//            super.updateDrawState(ds);
        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(mActivty, mSpan, Toast.LENGTH_SHORT).show();
        }
    }

}