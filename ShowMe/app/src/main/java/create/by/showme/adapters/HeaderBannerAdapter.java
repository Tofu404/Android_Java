package create.by.showme.adapters;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

import create.by.showme.R;
import create.by.showme.beans.BannerDataBean;

public class HeaderBannerAdapter extends BannerAdapter<BannerDataBean,HeaderBannerAdapter.Holder> {

    public HeaderBannerAdapter(List<BannerDataBean> datas) {
        super(datas);
    }

    @Override
    public Holder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindView(Holder holder, BannerDataBean data, int position, int size) {
        holder.bannerImage.setImageResource(data.getId());
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView bannerImage = null;
        public Holder(@NonNull View itemView) {
            super(itemView);
            bannerImage = itemView.findViewById(R.id.banner_image);
        }
    }

}
