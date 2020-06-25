package com.example.myview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
/*
public class ItHome extends View{
    //定义两个成员变量，这两个变量是用来记录ithome图片的位置的
    public float x;
    public float y;
    //这是构造方法，通过构造方法指定ithome图片的初始位置
    public ItHome(Context context) {
        super(context);
        x = 200;
        y = 200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();//这是一个无参的构造方法
        */
/**
         * 创建一个Bitmap对象
         * Bitmap:就是一个位图的信息，可以理解为Bitmap是一个画框
         *//*

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ithome);
        canvas.drawBitmap(bitmap,x,y,paint);
        //强制回收图片,原因是为了Android中的图片是很占系统的资源的
        //所以及时的回收资源可以避免出现out fo memory错误
        if(bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}*/

public class ItHome extends View{
    protected float x;
    protected float y;
    public ItHome(Context context) {
        super(context);
        x = 540;
        y = 960;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ithome);
        canvas.drawBitmap(bitmap,x,y,paint);
        if (bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}

