package create.by.gank.adapters;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import create.by.gank.R;
import create.by.gank.bean.GanHuoBean;
import create.by.gank.utils.CustomPicasso;

public class CategoryDataAdapter extends RecyclerView.Adapter<CategoryDataAdapter.MyHolder> {

    private List<GanHuoBean.DataBean> mDataBeans = new ArrayList<>();
    private MyHolder holder;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ganhuo_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        GanHuoBean.DataBean dataBean = mDataBeans.get(position);
        if (dataBean != null) {
            if (dataBean.getImages().size()>0){
                Glide.with(holder.ganhuoIcon.getContext())
                        .load(dataBean.getImages().get(0))
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .error(R.drawable.ic_me)
                        .into(holder.ganhuoIcon);
            }
            holder.type.setText(dataBean.getType());
            holder.description.setText(dataBean.getDesc());
            holder.title.setText(dataBean.getTitle());
            String substring = dataBean.getPublishedAt().substring(0, "1111-11-11".length());
            holder.publishedTime.setText(substring);
            holder.author.setText("作者:" + dataBean.getAuthor());
        }
    }

    @Override
    public int getItemCount() {
        if (mDataBeans.size() > 0) {
            return mDataBeans.size();
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


    public class MyHolder extends RecyclerView.ViewHolder {
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
