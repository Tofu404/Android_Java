package create.by.gank.fragments;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import create.by.gank.R;
import create.by.gank.adapters.MeAdapter;
import create.by.gank.base.BaseFragment;
import create.by.gank.bean.MeItemBean;

public class MeFragment extends BaseFragment {

    @BindView(R.id.me_rv)
    RecyclerView meRv;

    private List<MeItemBean> mMeItemBeanList = new ArrayList<>();

    @Override
    protected void init() {
        meRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.left = 20;
                outRect.right = 20;
                //outRect.bottom = 5;
            }
        });
        meRv.setLayoutManager(new LinearLayoutManager(getContext()));
        MeAdapter meAdapter = new MeAdapter();
        meRv.setAdapter(meAdapter);
        initItemData();
        meAdapter.setItemData(mMeItemBeanList);
    }

    private void initItemData() {
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_me,"孔天泽","Nihao_Tofu",MeItemBean.HEADER_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo,"干货",MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo,"干货",MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo,"干货",MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo,"干货",MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo,"干货",MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo,"干货",MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl,"妹纸",MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl,"妹纸",MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl,"妹纸",MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl,"妹纸",MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl,"妹纸",MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_me;
    }
}
