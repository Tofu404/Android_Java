package com.example.loopthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread extends Thread {

    public Handler handler;
    @Override
    public void run() {
        super.run();
        Looper.prepare();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                    Log.i("nihao",String.valueOf(msg.what));
            }
        };
        Message message = handler.obtainMessage();//获取message对象
        message.what = 0x7;//设置消息代码
        handler.sendMessage(message);//发送消息
        Looper.loop();
    }
}
