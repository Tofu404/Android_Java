package com.example.lunbotupian;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {

    final int FLAG_MSG = 0x001;    //定义要发送的消息代码
    private ViewFlipper flipper;//定义ViewFlipper
    private Message message;//定义Message
    //定义图片数组
    private int [] image = new int[]{
        R.drawable.picture1,
        R.drawable.picture2,
        R.drawable.picture3,
        R.drawable.picture4
    };

    //定义动画资源数组
    private Animation[] animations = new Animation[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flipper = findViewById(R.id.viewFlipper);

        //将图片资源添加到ViewFlipper当中
        for (int i = 0;i < image.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(image[i]);
            flipper.addView(imageView);
        }

        //初始化动画资源数组
        animations[0] = AnimationUtils.loadAnimation(this,R.anim.slide_in_right);
        animations[1] = AnimationUtils.loadAnimation(this,R.anim.slide_out_left);

        //设置ViewFlipper的切换动画
        flipper.setInAnimation(animations[0]);
        flipper.setOutAnimation(animations[1]);

        //启动viewFlipper
        message = Message.obtain();
        message.what = FLAG_MSG;
        handler.sendMessage(message);
    }

    //创建Handle对象
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == FLAG_MSG){
                flipper.showPrevious();
            }
            //获取message对象
            message = handler.obtainMessage(FLAG_MSG);
            handler.sendMessageDelayed(message,3000);
        }
    };
}
