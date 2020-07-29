package create.by.android_interview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import create.by.android_interview.R;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyHolder> {

    private MyOnItemClickListener mMyOnItemClickListener = null;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMyOnItemClickListener != null) {
                    mMyOnItemClickListener.OnItemClickListener();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setMyOnItemClickListener(MyOnItemClickListener listener) {
        mMyOnItemClickListener = listener;
    }

    public interface MyOnItemClickListener {
        void OnItemClickListener();
    }
}
