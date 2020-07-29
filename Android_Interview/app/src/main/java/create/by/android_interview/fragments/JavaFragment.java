package create.by.android_interview.fragments;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import create.by.android_interview.R;
import create.by.android_interview.WebActivity;
import create.by.android_interview.adapters.RvAdapter;
import create.by.android_interview.base.BaseFragment;

public class JavaFragment extends BaseFragment implements RvAdapter.MyOnItemClickListener {

    @BindView(R.id.item_rv)
    RecyclerView itemRv;

    @Override
    public void init() {
        itemRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 20;
                outRect.bottom = 20;
                outRect.right = 20;
                outRect.left = 20;
            }
        });
        itemRv.setLayoutManager(new LinearLayoutManager(getContext()));
        RvAdapter rvAdapter = new RvAdapter();
        rvAdapter.setMyOnItemClickListener(this);
        itemRv.setAdapter(rvAdapter);
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_layout;
    }

    @Override
    public void OnItemClickListener() {
        WebActivity.start(getContext(),"https://alleniverson.gitbooks.io/java-basic-introduction/content/");
    }
}
