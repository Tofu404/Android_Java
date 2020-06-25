package com.example.binder_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class binderservice extends Service {
    public binderservice() {
    }
    public class MyBinder extends Binder{//创建MyBinder内部类并获取服务对象Service状态
        public binderservice getService(){//创建获取Service的方法
            return binderservice.this;    //返回当前service的类
        }

    }

    //创建获取随机数的方法
    public List getRandomNumber(){
        List resArr = new ArrayList();
        String strNumber = "";
        //通过for循环环获取七个随机数
        for (int i = 0; i < resArr.size(); i++){
            int number = new Random().nextInt(33)+1;//创建1~33的随机数
            if (number < 10){
                strNumber = "0"+String.valueOf(number);
            }else {
                strNumber = String.valueOf(number);
            }
            resArr.add(strNumber);//将各个元素添加到数组当中去
        }
        return resArr;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {//销毁该Service
        super.onDestroy();
    }
}
