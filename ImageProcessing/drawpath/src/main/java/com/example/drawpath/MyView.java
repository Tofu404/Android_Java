package com.example.drawpath;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(0xFF0088FF);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        Path path = new Path();
        path.addCircle(500,500,200,Path.Direction.CW);//绘制一个圆形的路径其中最后的一个参数是表示顺时针绘制好事逆时针绘制
        //canvas.drawPath(path,paint);//绘制路径
        canvas.drawTextOnPath("你好呀！这是什么东西！",path,0,0,paint);//将文字沿路径绘制
    }
}
