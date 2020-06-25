package com.tianze.xiangting.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianze.xiangting.R;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.ViewHloder> {

    private List<Track> tracks = new ArrayList<>();
    private OnTrackListItemClickListener onTrackListItemClickListener = null;

    @NonNull
    @Override
    public ViewHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_track_list_item, parent, false);
        return new ViewHloder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHloder holder, int position) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Track track = tracks.get(position);
        holder.trackId.setText(position + 1 + "");
        holder.trackTitle.setText(track.getTrackTitle());
        holder.trackUpdatedAt.setText(format.format(track.getUpdatedAt()));
        holder.trackPlayCount.setText(track.getPlayCount() + "");
        //将时长单位由秒化成毫秒
        int duration = track.getDuration() * 1000;
        format = new SimpleDateFormat("mm:ss");
        holder.trackDuration.setText(format.format(duration));

        //为item项添加点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTrackListItemClickListener!=null){
                    onTrackListItemClickListener.onTrackListItemClick(position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onTrackListItemClickListener!=null){
                    onTrackListItemClickListener.onTrackListItemLongClick(position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.tracks.size() > 0) {
            return this.tracks.size();
        }
        return 0;
    }

    public void setTracksData(List<Track> tracks) {
        if (tracks != null) {
            this.tracks.clear();
            this.tracks.addAll(tracks);
            notifyDataSetChanged();
        }
    }

    static class ViewHloder extends RecyclerView.ViewHolder {
        @BindView(R.id.track_id)
        TextView trackId;
        @BindView(R.id.track_title)
        TextView trackTitle;
        @BindView(R.id.track_play_count)
        TextView trackPlayCount;
        @BindView(R.id.track_duration)
        TextView trackDuration;
        @BindView(R.id.track_updated_at)
        TextView trackUpdatedAt;

        public ViewHloder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnTrackListItemClickListener{
        void onTrackListItemClick(int position);
        void onTrackListItemLongClick(int position);
    }

    public void setOnTrackListItemClickListener(OnTrackListItemClickListener listener){
        this.onTrackListItemClickListener = listener;
    }
}
