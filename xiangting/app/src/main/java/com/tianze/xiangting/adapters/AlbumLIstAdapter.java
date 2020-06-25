package com.tianze.xiangting.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tianze.xiangting.R;
import com.tianze.xiangting.views.RoundRectImageView;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumLIstAdapter extends RecyclerView.Adapter<AlbumLIstAdapter.ViewHolder> {

    private List<Album> albumList = new ArrayList<>();
    private OnRecommendItemClickListener onRecommendItemClickListener = null;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recommend_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumList.get(position);
        setHolderViewData(holder, album);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecommendItemClickListener != null) {
                    onRecommendItemClickListener.onAlbumItemClick(album);
                }
            }
        });
    }

    private void setHolderViewData(ViewHolder holder, Album album) {
        Glide.with(holder.albumCover.getContext()).load(album.getCoverUrlLarge()).into(holder.albumCover);
        holder.albumTitle.setText(album.getAlbumTitle());
        holder.albumIntro.setText(album.getAlbumIntro());
        holder.playCount.setText((album.getPlayCount() / 10000) + "万");
        holder.includeTrackCount.setText(album.getIncludeTrackCount() + "集");
    }

    @Override
    public int getItemCount() {
        if (albumList != null) {
            return albumList.size();
        }
        return 0;
    }

    public void setData(List<Album> albumList) {
        if (albumList != null) {
            this.albumList.clear();
            this.albumList.addAll(albumList);
            notifyDataSetChanged();
        }
    }

    public void setOnRecommendItemClickListener(OnRecommendItemClickListener listener) {
        this.onRecommendItemClickListener = listener;
    }

    public interface OnRecommendItemClickListener {
        void onAlbumItemClick(Album album);
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.album_cover)
        RoundRectImageView albumCover;
        @BindView(R.id.album_title)
        TextView albumTitle;
        @BindView(R.id.album_intro)
        TextView albumIntro;
        @BindView(R.id.play_count)
        TextView playCount;
        @BindView(R.id.include_track_count)
        TextView includeTrackCount;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
