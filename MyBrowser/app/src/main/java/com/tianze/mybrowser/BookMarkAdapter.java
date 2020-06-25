package com.tianze.mybrowser;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianze.mybrowser.db.entity.BookMark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookMarkAdapter extends RecyclerView.Adapter<BookMarkAdapter.ViewHolder> {

    private int[] colors = new int[]{
            0xfffa5a5a,
            0xfff0d264,
            0xff82c8a0,
            0xff7fccde,
            0xff6698cb,
            0xffcb99c5
    };


    private List<BookMark> bookMarkList = new ArrayList<>();

    public void setOnBookMarkClickLinsener(OnBookMarkClickLinsener onBookMarkClickLinsener) {
        mOnBookMarkClickLinsener = onBookMarkClickLinsener;
    }

    private OnBookMarkClickLinsener mOnBookMarkClickLinsener;

    @NonNull
    @Override
    public BookMarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_book_mark_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookMarkAdapter.ViewHolder holder, int position) {
            final BookMark bookMark = bookMarkList.get(position);
            holder.bookMarkTitle.setText(bookMark.getMTitle());

            Random random = new Random();
            int i = random.nextInt(colors.length);
            GradientDrawable background = (GradientDrawable) holder.bookMarkIcon.getBackground();
            background.setColor(colors[i]);
            String substring = bookMark.getMTitle().substring(0, 2);
            holder.bookMarkIcon.setText(substring);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnBookMarkClickLinsener != null) {
                    mOnBookMarkClickLinsener.onLongClick(v,bookMark);
                }
                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBookMarkClickLinsener != null) {
                    mOnBookMarkClickLinsener.onClick(bookMark);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (bookMarkList != null && bookMarkList.size() > 0) {
            return bookMarkList.size();
        }
        return 0;
    }

    public void setData(List<BookMark> all) {
        if (all != null) {
            this.bookMarkList.clear();
            this.bookMarkList.addAll(all);
            notifyDataSetChanged();
        }
    }

    public interface OnBookMarkClickLinsener {
        void onClick(BookMark bookMark);

        void onLongClick(View view,BookMark bookMark);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookMarkIcon = null;
        TextView bookMarkTitle = null;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookMarkIcon = itemView.findViewById(R.id.book_mark_icon);
            bookMarkTitle = itemView.findViewById(R.id.book_mark_title);
        }
    }
}
