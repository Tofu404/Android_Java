package create.by.myview.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import create.by.myview.R;

public class MyButton extends androidx.appcompat.widget.AppCompatButton {

    private int mMyButtonBgColor;
    private int mMyButtonRadius;



    public MyButton(Context context) {
        this(context,null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        @SuppressLint("Recycle")
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyButton, defStyleAttr, 0);
        mMyButtonBgColor = a.getColor(R.styleable.MyButton_bg_color, Color.GRAY);
        mMyButtonRadius = a.getInt(R.styleable.MyButton_radius, 0);
        init();
    }

    private void init() {

    }


}
