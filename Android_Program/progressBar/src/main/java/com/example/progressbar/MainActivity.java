package com.example.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 进度条的实现的步骤：通过子线程进行进度条的更新操作
 *
 */
/*public class MainActivity extends Activity {
    private ProgressBar progressBar;
    private int myProgress;     //这个变量是用来记录进度的变化的程度的
    private Handler handler;        //这个Handle对象是用来更新进程的进度的
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置程序全屏的方式进行显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获取progressBar对象
        progressBar = findViewById(R.id.myProgress);
        handler = new Handler(){
            @Override
            *//**
             * handleMessage方法的处理逻辑
             * 根据handle发回来的message，判断是进度条的进度是否完成，
             * 要是进度条没有完成的话那就更新进度条的进度，完成了的话，
             * 那就设置进度条为不显示，然后通过Toast方法进行提示
             *//*
            public void handleMessage(Message msg) {
                if (msg.what == 0x111){
                    progressBar.setProgress(myProgress);
                }else{
                    Toast.makeText(MainActivity.this,"游戏加载完成！",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        };
        *//**
         * 创建新的进程然后进行进度的更新
         * 这个线程通过while循环检测进度条的进度是否完成，如果完成了的话那就通过Message对象发送完成的信息
         * 要是没有完成的话，那就发送没有完成的信息
         *//*
        new Thread(new Runnable() {
            @Override
            public void run() {
               while(true){
                   myProgress = doWork();
                   Message message = new Message();
                   if (myProgress <= 100){
                       message.what = 0x111;
                       handler.sendMessage(message);
                   }else{
                       message.what = 0x110;
                       handler.sendMessage(message);
                       break;
                   }
               }
            }

            *//**
             * 这个方法就是产生一个随机数，然后将随机数赋值给myProgress变量
             * @return
             *//*
            private int doWork(){
                myProgress += Math.random()*10;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return myProgress;
            }
        }).start();

    }

}*/

public class MainActivity extends Activity {
    int myPrgress;
    ProgressBar progressBar;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar = findViewById(R.id.myProgress);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x111){
                    progressBar.setProgress(myPrgress);
                }else{
                    Toast.makeText(MainActivity.this,"游戏已经加载完成！",Toast.LENGTH_SHORT).show();
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Message message = new Message();
                    myPrgress = doWork();
                    if (myPrgress <= 100){
                        message.what = 0x111;
                        handler.sendMessage(message);
                    }else{
                        message.what = 0x110;
                        handler.sendMessage(message);
                        break;
                    }
                }
            }

            public int doWork(){
                myPrgress += Math.random()*10;
                //进行异常处理
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return myPrgress;
            }
        }).start();

    }

}
