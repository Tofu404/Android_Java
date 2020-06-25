package com.tianze.recycleviewlooper;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LooperAdapter extends RecyclerView.Adapter<LooperAdapter.ViewHolder> {


    private int[] images;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_looper_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView looperImage = holder.mImageView;
        Drawable drawable = looperImage.getContext().getResources().getDrawable(images[position % images.length], null);
        looperImage.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        if (images.length > 0) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    public void setData(int[] images) {
        if (images != null) {
            this.images = images;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.looper_image);
        }
    }
}
