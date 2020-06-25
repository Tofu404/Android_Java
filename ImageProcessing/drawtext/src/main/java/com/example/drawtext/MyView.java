package com.example.drawtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);//开启抗锯齿功能
        paint.setColor(0xFF000000);//设置画笔的颜色
        paint.setTextAlign(Paint.Align.LEFT);//设置文字为左对齐
        paint.setTextSize(50);//设置文字的大小

        //绘制文字
        canvas.drawText("你好呀！我是孔天泽",100,100,paint);
        canvas.drawText("你叫什么名字啊？",100,160,paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("你好呀！我叫小红",900,400,paint);
        canvas.drawText("很高兴认识你",900,460,paint);


    }
}
