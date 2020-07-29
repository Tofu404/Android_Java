package create.by.version2;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MyHoldr>{
    @NonNull
    @Override
    public MyHoldr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHoldr holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyHoldr extends RecyclerView.ViewHolder {
        public MyHoldr(@NonNull View itemView) {
            super(itemView);
        }
    }
}
