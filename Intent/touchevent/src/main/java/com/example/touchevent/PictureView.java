package com.example.touchevent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * 第一步创建自定义View，用来绘制帽子
 */
public class PictureView extends View {
    public float X;
    public float Y;
    //给一个构造方法
    public PictureView(Context context) {
        super(context);
        X = 80;
        Y = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取画笔
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.login);
        canvas.drawBitmap(bitmap,X,Y,paint);
        if (bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
