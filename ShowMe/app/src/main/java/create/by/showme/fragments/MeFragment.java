package create.by.showme.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import create.by.showme.R;
import create.by.showme.adapters.MeItemAdapter;
import create.by.showme.base.BaseFragment;
import create.by.showme.beans.MeItemBean;

public class MeFragment extends BaseFragment {

    @BindView(R.id.me_rv)
    RecyclerView mMeRv;
    private MeItemAdapter mMeItemAdapter;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void init() {
        mMeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mMeItemAdapter = new MeItemAdapter();
        mMeRv.setAdapter(mMeItemAdapter);
        List<MeItemBean> meItemBeans = initItemData();
        mMeItemAdapter.setData(meItemBeans);
    }

    private List<MeItemBean> initItemData() {
        List<MeItemBean> meItemBeans =new ArrayList<>();
        meItemBeans.add(new MeItemBean(R.drawable.ic_home,"",MeItemBean.HEADER_TYPE));
        meItemBeans.add(new MeItemBean(0,"",MeItemBean.DRIVER_TYPE));
        meItemBeans.add(new MeItemBean(0,"",MeItemBean.DRIVER_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_search,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_search,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_search,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_search,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_search,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_search,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_search,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_search,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_search,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(0,"",MeItemBean.DRIVER_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_me,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_me,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_me,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_me,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_me,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_me,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_me,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_me,"",MeItemBean.NORMAL_TYPE));
        meItemBeans.add(new MeItemBean(R.drawable.ic_me,"",MeItemBean.NORMAL_TYPE));
        return meItemBeans;
    }


}
