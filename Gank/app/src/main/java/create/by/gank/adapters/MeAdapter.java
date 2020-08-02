package create.by.gank.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import create.by.gank.R;
import create.by.gank.bean.MeItemBean;

public class MeAdapter extends RecyclerView.Adapter<MeAdapter.MyHolder> {


    private List<MeItemBean> meItemBeanList = new ArrayList<>();
    private int mCurrentPosition = 0;
    private int mCurrentGroup = 0;
    private int divideNum = 0;
    private int groupNum = 0;

//    @NonNull
//    @Override
//    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = null;
//        switch (viewType) {
//            case MeItemBean.HEADER_TYPE:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_me_item_header, parent, false);
//                break;
//            case MeItemBean.NORMAL_TYPE:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_me_item_normal, parent, false);
//                break;
//            case MeItemBean.DIVIDE_TYPE:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_me_item_divide, parent, false);
//                break;
//        }
//        assert view != null;
//        return new MyHolder(view);
//    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case MeItemBean.HEADER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_me_item_header, parent, false);
                break;
            case MeItemBean.NORMAL_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_me_item_normal, parent, false);
                break;
            case MeItemBean.DIVIDE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_me_item_divide, parent, false);
                break;
        }
        assert view != null;
        return new MyHolder(view);
    }


    @Override
    public int getItemViewType(int position) {
        mCurrentPosition = position;
        return meItemBeanList.get(position).getItemType();
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MeItemBean meItemBean = meItemBeanList.get(position);
        switch (getItemViewType(position)) {
            case MeItemBean.HEADER_TYPE:
                holder.userIcon.setImageResource(meItemBean.getImageId());
                holder.userName.setText(meItemBean.getUserName());
                holder.userAccount.setText(meItemBean.getUserAccount());
                break;
            case MeItemBean.NORMAL_TYPE:
                holder.itemIcon.setImageResource(meItemBean.getImageId());
                holder.itemTile.setText(meItemBean.getItem_title());
                break;
            case MeItemBean.DIVIDE_TYPE:
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (meItemBeanList.size() > 0) {
            return meItemBeanList.size();
        }
        return 0;
    }

    public void setItemData(List<MeItemBean> meItemBeanList) {
        if (meItemBeanList != null) {
            this.meItemBeanList.clear();
            this.meItemBeanList.addAll(meItemBeanList);
            notifyDataSetChanged();
        }
    }


    public static class MyHolder extends RecyclerView.ViewHolder {
        ImageView userIcon = null;
        TextView userName = null;
        TextView userAccount = null;
        ImageView itemIcon = null;
        TextView itemTile = null;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            userIcon = itemView.findViewById(R.id.user_icon);
            userName = itemView.findViewById(R.id.user_name);
            userAccount = itemView.findViewById(R.id.user_account);
            itemIcon = itemView.findViewById(R.id.item_icon);
            itemTile = itemView.findViewById(R.id.item_title);
        }
    }
}
