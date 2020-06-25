package com.example.drawbitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        Paint paint = new Paint();//创建画笔对象
        paint.setColor(0xFF000000);//设置画笔的颜色
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.picture2);//获取图片资源
        canvas.drawBitmap(bitmap,10,10,paint);//在画布绘制图片
        paint.setTextSize(50);
        canvas.drawText("这是挖取的一块图片！",10,750,paint);
        //挖取小片的图片进行绘制
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap,10,10,400,400);
        canvas.drawBitmap(bitmap1,10,800,paint);
    }
}
