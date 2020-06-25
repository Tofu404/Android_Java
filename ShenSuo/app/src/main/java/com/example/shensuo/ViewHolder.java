package com.example.shensuo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class  ViewHolder {
    ImageView imageView;
    TextView count_tv;
    TextView pw_tv;

    public ViewHolder(ImageView imageView,TextView count_tv,TextView pw_tv){
        this.imageView = imageView;
        this.count_tv = count_tv;
        this.pw_tv = pw_tv;
    }
}
