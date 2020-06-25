package com.tianze.looppager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MyViewPager extends ViewPager {

    private OnPagerViewTouchListener onPagerViewTouchListener = null;

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        assert onPagerViewTouchListener != null;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onPagerViewTouchListener.OnPagerViewTouch(true);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                onPagerViewTouchListener.OnPagerViewTouch(false);
                break;
        }
        return super.onTouchEvent(ev);
    }

    public interface OnPagerViewTouchListener {
        void OnPagerViewTouch(boolean b);
    }

    public void setOnPagerViewTouchListener(OnPagerViewTouchListener listener) {
        this.onPagerViewTouchListener = listener;
    }
}
