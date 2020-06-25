package com.tianze.xiangting.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianze.xiangting.R;
import com.ximalaya.ting.android.opensdk.model.word.QueryResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuggestWordAdapter extends RecyclerView.Adapter<SuggestWordAdapter.ViewHolder> {

    List<QueryResult> keyWordList  = new ArrayList<>();
    private OnSuggestItemClickListener mOnSuggestItemClickListener = null;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_suggest_word_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String keyword = keyWordList.get(position).getKeyword();
        holder.suggestWord.setText(keyword);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSuggestItemClickListener != null) {
                    mOnSuggestItemClickListener.onSuggestItemClick(keyword);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (keyWordList.size()>0){
            return keyWordList.size();
        }
        return 0;
    }

    public void setSuggestData(List<QueryResult> keyWordList) {
        if (keyWordList!=null){
            this.keyWordList.clear();
            this.keyWordList.addAll(keyWordList);
            notifyDataSetChanged();
        }
    }

    static

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.suggest_word)
        TextView suggestWord;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnSuggestItemClickListener {
        void onSuggestItemClick(String s);
    }

    public void setOnSuggestItemClickListener(OnSuggestItemClickListener listener){
        this.mOnSuggestItemClickListener = listener;
    }
}
