package create.by.mysimpleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MySimpleView extends androidx.appcompat.widget.AppCompatTextView {

    //自定义属性值
    private int mTextColor;

    //初始状态值
    private Paint mPaint;
    private int mCurrentX;
    private int mCurrentY;
    private static final int DEFAULT_COLOR = Color.BLACK;

    public MySimpleView(Context context) {
        this(context, null);
    }

    public MySimpleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySimpleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义属性
        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.MySimpleView, defStyleAttr, 0);
        mTextColor = styledAttributes.getColor(R.styleable.MySimpleView_MyTextColor, DEFAULT_COLOR);

        //回收TypedArray
        styledAttributes.recycle();

        //初始化
        init();
        setLayerType(View.LAYER_TYPE_HARDWARE,mPaint);
    }

    private void init() {
        //初始化画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setAntiAlias(true);

        //初始位置
        mCurrentX = 100;
        mCurrentY = 100;


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画一个圆形
        int radius = 50;
        canvas.drawCircle(mCurrentX+radius, mCurrentY+radius, radius, mPaint);

        //画文字
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(30);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Hello Word", mCurrentX+radius, mCurrentY+radius*2+30, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mCurrentX = (int) event.getX();
            mCurrentY = (int) event.getY();
            //重新绘制图形
            invalidate();
        }
        return true;
    }
}
