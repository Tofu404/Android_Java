package create.by.showme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import create.by.showme.R;
import create.by.showme.beans.MeItemBean;

public class MeItemAdapter extends RecyclerView.Adapter<MeItemAdapter.MyHolder> {

    private List<MeItemBean> meItemBeans = new ArrayList<>();

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyHolder myHolder = null;
        View view = null;
        switch (viewType) {
            case MeItemBean.HEADER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.me_item_header, parent, false);
                myHolder = new MyHolder(view);
                break;
            case MeItemBean.NORMAL_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.me_item_normal, parent, false);
                myHolder = new MyHolder(view);
                break;
            case MeItemBean.DRIVER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.me_item_driver, parent, false);
                myHolder = new MyHolder(view);
                break;
        }
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        switch (getItemViewType(position)){
            case MeItemBean.HEADER_TYPE:
                holder.headerImage.setImageResource(meItemBeans.get(position).getImageResourceId());
                holder.userAccount.setText("用户账号：123123123");
                holder.userName.setText("用户名：Tofu");
                break;
            case MeItemBean.NORMAL_TYPE:
                holder.meItemIcon.setImageResource(meItemBeans.get(position).getImageResourceId());
                holder.meItemText.setText("我是第"+(position+1)+"项");
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = -1;
        MeItemBean meItemBean = meItemBeans.get(position);
        switch (meItemBean.getItemType()) {
            case MeItemBean.HEADER_TYPE:
                itemType = MeItemBean.HEADER_TYPE;
                break;
            case MeItemBean.NORMAL_TYPE:
                itemType = MeItemBean.NORMAL_TYPE;
                break;
            case MeItemBean.DRIVER_TYPE:
                itemType = MeItemBean.DRIVER_TYPE;
                break;
        }
        return itemType;
    }

    @Override
    public int getItemCount() {
        if (meItemBeans.size() > 0) {
            return meItemBeans.size();
        }
        return 0;
    }

    public void setData(List<MeItemBean> meItemBeans) {
        if (meItemBeans != null) {
            this.meItemBeans.clear();
            this.meItemBeans.addAll(meItemBeans);
            notifyDataSetChanged();
        }
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        ImageView headerImage = null;
        TextView userName = null;
        TextView userAccount = null;
        ImageView meItemIcon = null;
        TextView meItemText = null;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            headerImage = itemView.findViewById(R.id.header_image);
            userName = itemView.findViewById(R.id.user_name);
            userAccount = itemView.findViewById(R.id.user_account);
            meItemIcon = itemView.findViewById(R.id.me_item_icon);
            meItemText = itemView.findViewById(R.id.me_item_text);
        }
    }
}
