package com.example.qq_picture_gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class myAdapter extends BaseAdapter {
    Context myContext;
    private int[] picture;

    myAdapter(Context c, int a[]) {
        myContext = c;
        picture = a;
    }

    @Override
    //这个方法是用来获取数据的缓存长度的
    public int getCount() {
        return picture.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    //这个方法是用来获取将要显示的下一个元素的
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(myContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300,300));
            //设置图片的纵横比来进行缩放，并完全覆盖ImageView
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(picture[position]);
        return imageView;
    }
}