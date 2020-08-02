package create.by.gank.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import create.by.gank.R;
import create.by.gank.bean.GirlBean;

import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.MyHolder> {

    private View mErrorStatus;
    private View mFinishStatus;
    private View mLoadingStatus;

    public GirlAdapter() {

    }

    private RecyclerViewListener mRecyclerViewListener = null;

    //是否加载更多标记，为true表示正在加载更多，防止多次触发加载更多
    private boolean isLoadingMore = false;
    private boolean isLadAllData = false;

    //定义两种item
    private static final int LOAD_MORE_ITEM = 0;
    private static final int NORMAL_ITEM = 1;
    private List<GirlBean.DataBean> girlBeanList = new ArrayList<>();

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == NORMAL_ITEM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_girl_rv_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_load_more_item, parent, false);

            FrameLayout framelayout = view.findViewById(R.id.container);
            mErrorStatus = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_girl_rv_error_status, parent, false);
            mFinishStatus = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_girl_rv_finish_status, parent, false);
            mLoadingStatus = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_girl_rv_loading_status, parent, false);

            framelayout.addView(mErrorStatus);
            framelayout.addView(mFinishStatus);
            framelayout.addView(mLoadingStatus);

            setLoadMoreStatus(LOADING);
        }
        return new MyHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() - 1 == position) {
            return LOAD_MORE_ITEM;
        }
        return NORMAL_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        if (getItemViewType(position) == NORMAL_ITEM) {
            GirlBean.DataBean dataBean = this.girlBeanList.get(position);
            assert dataBean != null;
            holder.phase.setText(dataBean.getTitle());
            String replace = dataBean.getDesc().replace("\n", "");
            holder.slogan.setText(replace);
            Glide.with(holder.girlPic).load(dataBean.getUrl()).into(holder.girlPic);
        }
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecyclerViewListener != null) {
                    mRecyclerViewListener.onItemClick(girlBeanList.get(position),holder.girlPic);
                }
            }
        });

        if (position == getItemCount() - 2 && !isLoadingMore && !isLadAllData) {
            if (mRecyclerViewListener != null) {
                mRecyclerViewListener.recyclerViewLoadMore();
            }
            isLoadingMore = true;
        }

    }

    @Override
    public int getItemCount() {
        if (girlBeanList.size() > 0) {
            return girlBeanList.size() + 1;
        }
        return 0;
    }

    public void setData(List<GirlBean.DataBean> girlBeanList) {
        if (girlBeanList != null) {
            this.girlBeanList.clear();
            this.girlBeanList.addAll(girlBeanList);
            notifyDataSetChanged();
        }
    }

    public static class MyHolder extends ViewHolder {

        TextView phase = null;
        TextView slogan = null;
        ImageView girlPic = null;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            phase = itemView.findViewById(R.id.phase);
            slogan = itemView.findViewById(R.id.slogan);
            girlPic = itemView.findViewById(R.id.girl_pic);
        }
    }

    public void setRecyclerViewListener(RecyclerViewListener recyclerViewListener) {
        mRecyclerViewListener = recyclerViewListener;
    }

    public interface RecyclerViewListener {
        void recyclerViewLoadMore();
        void onItemClick(GirlBean.DataBean dataBean, ImageView v);
    }

    //recycler view 尾部的状态
    public static final int SUCCEED = 0;
    public static final int ERROR = 1;
    public static final int EMPTY = 2;
    public static final int LOADING = 3;

    public void setLoadMoreStatus(int status) {
        if (mFinishStatus != null && mLoadingStatus != null && mErrorStatus != null) {
            switch (status) {
                case SUCCEED:
                    mErrorStatus.setVisibility(View.INVISIBLE);
                    mLoadingStatus.setVisibility(View.VISIBLE);
                    mFinishStatus.setVisibility(View.INVISIBLE);
                    isLoadingMore = false;
                    break;
                case ERROR:
                    mErrorStatus.setVisibility(View.VISIBLE);
                    mLoadingStatus.setVisibility(View.INVISIBLE);
                    mFinishStatus.setVisibility(View.INVISIBLE);
                    isLoadingMore = false;
                    break;
                case EMPTY:
                    mErrorStatus.setVisibility(View.INVISIBLE);
                    mLoadingStatus.setVisibility(View.INVISIBLE);
                    mFinishStatus.setVisibility(View.VISIBLE);
                    break;
                case LOADING:
                    mErrorStatus.setVisibility(View.INVISIBLE);
                    mLoadingStatus.setVisibility(View.VISIBLE);
                    mFinishStatus.setVisibility(View.INVISIBLE);
                    isLadAllData = false;
                    isLoadingMore = false;
                    break;
            }
        }
    }
}
