package com.tianze.xiangting.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianze.xiangting.R;
import com.tianze.xiangting.beans.MeItemInfo;
import com.tianze.xiangting.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MeItemAdapter extends RecyclerView.Adapter<MeItemAdapter.ViewHolder> {


    private List<MeItemInfo> itemInfoList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case Constants
                    .HEADER_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_me_header_item, parent, false);
                break;
            case Constants
                    .ABOUT_APP_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_me_about_app_item, parent, false);
                break;
            case Constants
                    .VERSION_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_me_version_item, parent, false);
                break;
            case Constants.CUT_OFF_RULE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_me_group_item, parent, false);
                break;
        }
        assert view != null;
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return itemInfoList.get(position).getItemType();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (itemInfoList.get(position).getItemType()== Constants.HEADER_ITEM) {
            holder.itemView.findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("nihao", "onClick: 我是第一按钮" );
                }
            });
            holder.itemView.findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("nihao", "onClick: 我是第二按钮" );
                }
            });
            holder.itemView.findViewById(R.id.text3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("nihao", "onClick: 我是第三按钮" );
                }
            });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("你好", "onClick: 我是第"+position+"想" );
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.itemInfoList.size()>0) {
            return this.itemInfoList.size();
        }
        return 9;
    }

    public void setItemData(List<MeItemInfo> itemInfoList) {
        if (itemInfoList != null) {
            this.itemInfoList.clear();
            this.itemInfoList.addAll(itemInfoList);
            notifyDataSetChanged();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
