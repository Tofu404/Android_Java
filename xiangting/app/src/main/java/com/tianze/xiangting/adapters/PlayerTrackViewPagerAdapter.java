package com.tianze.xiangting.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.tianze.xiangting.R;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.ArrayList;
import java.util.List;

public class PlayerTrackViewPagerAdapter extends PagerAdapter {

    private View mView;
    List<Track> trackList = new ArrayList<>();

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Track track = this.trackList.get(position);
        mView = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_player_cover_item, container, false);
        ImageView trackCover = mView.findViewById(R.id.track_cover);
        if (track != null) {
            Glide.with(trackCover.getContext()).load(track.getCoverUrlLarge()).into(trackCover);
        }
        container.addView(mView);
        return mView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (this.trackList!=null){
            return this.trackList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setData(List<Track> trackList) {
        if (trackList != null) {
            this.trackList.clear();
            this.trackList.addAll(trackList);
            notifyDataSetChanged();
        }
    }
}
