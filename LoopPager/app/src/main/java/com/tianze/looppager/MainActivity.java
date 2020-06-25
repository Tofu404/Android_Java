package com.tianze.looppager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MyViewPager.OnPagerViewTouchListener {

    @BindView(R.id.view_pager)
    MyViewPager viewPager;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    private Handler myHandle;
    private boolean isTouch = false;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isTouch) {
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++currentItem);
            }
            myHandle.postDelayed(this, 4000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myHandle = new Handler();
        initView();
    }

    public void initView(){

        //初始化颜色数据
        int[] pics = new int[]{
                R.drawable.pic1,
                R.drawable.pic2,
                R.drawable.pic3
        };

        //将选择指示点添加到容器中
        for (int i = 0; i < pics.length; i++) {
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMarginStart(20);
            view.setLayoutParams(params);
            view.setBackground(getDrawable(R.drawable.drawable_normal_point));
            linearLayout.addView(view);
        }

        //为viewpager设置适配器
        viewPager.setOnPagerViewTouchListener(this);

        ViewPagerAdapter adapter = new ViewPagerAdapter();

        adapter.setColorData(pics);

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(adapter.PicSize() * 100);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = viewPager.getCurrentItem();
                int realItem = 0;

                if (pics.length != 0){
                    realItem = currentItem%pics.length;
                }

                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    if (i == realItem){
                        linearLayout.getChildAt(i).setBackground(getDrawable(R.drawable.drawable_selected_point));
                    }else {
                        linearLayout.getChildAt(i).setBackground(getDrawable(R.drawable.drawable_normal_point));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        myHandle.post(runnable);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        myHandle.removeCallbacks(runnable);
    }

    @Override
    public void OnPagerViewTouch(boolean b) {
        isTouch = b;
    }
}
