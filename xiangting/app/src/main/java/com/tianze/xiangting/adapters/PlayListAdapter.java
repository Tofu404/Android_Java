package com.tianze.xiangting.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianze.xiangting.R;
import com.tianze.xiangting.base.BaseApplication;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {

    List<Track> playList = new ArrayList<>();
    private int playIndex = 0;
    private OnItemClickListener onItemClickListener = null;
    private final XmPlayerManager mXmPlayerManager;

    public PlayListAdapter(){
        mXmPlayerManager = XmPlayerManager.getInstance(BaseApplication.context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_player_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Track track = this.playList.get(position);
        holder.trackTitle.setText(track.getTrackTitle());
        if (position == mXmPlayerManager.getCurrentIndex()){
            holder.trackTitle.setSelected(true);
            holder.trackTitle.setTextColor(BaseApplication.context.getResources().getColor(R.color.colorMain));
            holder.playingIv.setVisibility(View.VISIBLE);
        }else {
            holder.trackTitle.setSelected(false);
            holder.trackTitle.setTextColor(BaseApplication.context.getResources().getColor(R.color.colorBlack));
            holder.playingIv.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.playList.size() > 0) {
            return this.playList.size();
        }
        return 0;
    }

    public void setTrackData(List<Track> playList) {
        if (playList != null) {
            this.playList.clear();
            this.playList.addAll(playList);
            notifyDataSetChanged();
        }
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.playing_iv)
        ImageView playingIv;
        @BindView(R.id.track_title)
        TextView trackTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }
}
