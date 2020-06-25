package com.tianze.looppager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    private int[] pics;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = position % 3;
        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageDrawable(container.getContext().getDrawable(pics[realPosition]));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (pics.length > 0) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    void setColorData(int[] pics) {
        if (pics != null && pics.length > 0) {
            this.pics = pics;
        }
    }

    public int PicSize(){
        if (pics!=null){
            return pics.length;
        }
        return 0;
    }
}
