package com.example.draw_a_rectangle;

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
        paint.setColor(0xFFFF6600);//Android默认的颜色是透明的。所以在显示的时候不会显示出来的，要在颜色值上添加“FF”将颜色设置为不透明状态
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(40,40,400,400,paint);
    }
}
