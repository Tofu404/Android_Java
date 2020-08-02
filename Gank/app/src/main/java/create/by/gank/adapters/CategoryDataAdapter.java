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
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import create.by.gank.R;
import create.by.gank.bean.GanHuoBean;
import create.by.gank.view_callback.GanHuoDataCallback;

public class CategoryDataAdapter extends RecyclerView.Adapter<CategoryDataAdapter.MyHolder> implements GanHuoDataCallback {

    private static final int NORMAL_TYPE = 0;
    private static final int FOOTER_TYPE = 1;

    private List<GanHuoBean.DataBean> mDataBeans = new ArrayList<>();
    private MyHolder holder;
    private CategoryDataListener mCategoryDataListener = null;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case NORMAL_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ganhuo_item, parent, false);
                break;
            case FOOTER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_load_more_item, parent, false);
                initFoot(view, parent);
                break;
        }
        assert view != null;
        return new MyHolder(view);
    }

    /*************************footer部分*************************/

    private static final int LOADING_STATUS = 0;
    private static final int FINISH_STATUS = 1;
    private static final int ERROR_STATUS = 2;
    Map<Integer, View> machViewWithStatus = new HashMap<>();

    private void initFoot(View view, ViewGroup parent) {
        FrameLayout statusViewContainer = view.findViewById(R.id.container);

        View loadingFooter = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_girl_rv_loading_status, parent, false);
        View errorFooter = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_girl_rv_error_status, parent, false);
        View finishFooter = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_girl_rv_finish_status, parent, false);

        machViewWithStatus.put(LOADING_STATUS, loadingFooter);
        machViewWithStatus.put(FINISH_STATUS, finishFooter);
        machViewWithStatus.put(ERROR_STATUS, errorFooter);

        for (Integer integer : machViewWithStatus.keySet()) {
            statusViewContainer.addView(machViewWithStatus.get(integer));
        }

        //默认显示loading的状态
        showFooterStatus(LOADING_STATUS);
    }

    private void showFooterStatus(int status) {
        for (Integer integer : machViewWithStatus.keySet()) {
            if (status == integer) {
                Objects.requireNonNull(machViewWithStatus.get(integer)).setVisibility(View.VISIBLE);
            } else {
                Objects.requireNonNull(machViewWithStatus.get(integer)).setVisibility(View.INVISIBLE);
            }
        }
    }

    /*************************footer部分*************************/

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        if (position < mDataBeans.size()) {
            GanHuoBean.DataBean dataBean = mDataBeans.get(position);
            if (dataBean.getImages().size() > 0) {
                Glide.with(holder.ganhuoIcon.getContext())
                        .load(dataBean.getImages().get(0))
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_me)
                        .into(holder.ganhuoIcon);
            }
            holder.type.setText(dataBean.getType());
            holder.description.setText(dataBean.getDesc());
            holder.title.setText(dataBean.getTitle());
            String substring = dataBean.getPublishedAt().substring(0, "1111-11-11".length());
            holder.publishedTime.setText(substring);
            holder.author.setText("作者:" + dataBean.getAuthor());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2020/8/2 加载富文本
                    if (mCategoryDataListener != null) {
                        mCategoryDataListener.onItemClick(dataBean.get_id());
                    }
                }
            });
        }

        if (position == (getItemCount() - 3) && mCategoryDataListener != null) {
            mCategoryDataListener.recyclerViewLoadMore();
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = NORMAL_TYPE;
        if (position == getItemCount() - 1) {
            type = FOOTER_TYPE;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        if (mDataBeans.size() > 0) {
            return mDataBeans.size() + 1;
        }
        return 0;
    }

    public void setData(List<GanHuoBean.DataBean> data) {
        if (data != null) {
            mDataBeans.clear();
            mDataBeans.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public void loadMore(List<GanHuoBean.DataBean> data) {
        showFooterStatus(LOADING_STATUS);
    }

    @Override
    public void loadError() {
        showFooterStatus(ERROR_STATUS);
    }

    @Override
    public void loadFinish() {
        showFooterStatus(FINISH_STATUS);
    }

    public void setCategoryDataListener(CategoryDataListener categoryDataListener) {
        mCategoryDataListener = categoryDataListener;
    }

    public interface CategoryDataListener {
        void recyclerViewLoadMore();
        void onItemClick(String id);
    }


    /*************************viewHolder部分*************************/
    public static class MyHolder extends RecyclerView.ViewHolder {
        ImageView ganhuoIcon = null;
        TextView type = null;
        TextView description = null;
        TextView title = null;
        TextView author = null;
        TextView publishedTime = null;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ganhuoIcon = itemView.findViewById(R.id.ganhuo_icon);
            type = itemView.findViewById(R.id.type);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            author = itemView.findViewById(R.id.author);
            publishedTime = itemView.findViewById(R.id.public_time);
        }
    }
}
