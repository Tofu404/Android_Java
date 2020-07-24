package create.by.showme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import create.by.showme.R;
import create.by.showme.beans.CategoriesContent;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {

    List<CategoriesContent.DataBean> mDataBeans = new ArrayList<>();

    @NonNull
    @Override
    public ContentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentAdapter.MyViewHolder holder, int position) {
        CategoriesContent.DataBean dataBean = mDataBeans.get(position);
        assert dataBean != null;
        holder.productDescription.setText(dataBean.getTitle());
        String pict_url = dataBean.getPict_url();
        if (pict_url.startsWith("http:") || pict_url.startsWith("https:")) {
            Glide.with(holder.productImage).load(pict_url).into(holder.productImage);
        } else {
            Glide.with(holder.productImage).load("https:" + pict_url).into(holder.productImage);
        }
    }

    @Override
    public int getItemCount() {
        if (mDataBeans.size() > 0) {
            return mDataBeans.size();
        }
        return 0;
    }

    public void setData(List<CategoriesContent.DataBean> dataBeanList) {
        if (dataBeanList != null) {
            mDataBeans.clear();
            mDataBeans.addAll(dataBeanList);
            notifyDataSetChanged();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productDescription = null;
        ImageView productImage = null;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productDescription = itemView.findViewById(R.id.product_description);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}
