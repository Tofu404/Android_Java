package create.by.gank.fragments;

import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class MeFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.group_container)
    LinearLayout groupContainer;

    private List<MeItemBean> mMeItemBeanList = new ArrayList<>();
    private int groupNum = 0;

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_me;
    }

    @Override
    protected void init() {
        initItemData();
        initView();
    }

    private void initView() {
        for (int groupId = 1; groupId <= groupNum; groupId++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_me_item_group, groupContainer, false);
            LinearLayout groupView = view.findViewById(R.id.me_item_group_container);
            for (int j = 0; j < mMeItemBeanList.size(); j++) {
                MeItemBean meItemBean = mMeItemBeanList.get(j);
                if (meItemBean.getGroupId() == groupId) {
                    int itemType = meItemBean.getItemType();
                    View childView = null;
                    switch (itemType) {
                        case MeItemBean.HEADER_TYPE:
                            childView = LayoutInflater.from(getContext()).inflate(R.layout.layout_me_item_header, groupView, false);
                            childView.setTag(j);
                            break;
                        case MeItemBean.NORMAL_TYPE:
                            childView = LayoutInflater.from(getContext()).inflate(R.layout.layout_me_item_normal, groupView, false);
                            childView.setTag(j);
                            break;
                    }
                    groupView.addView(childView);
                    assert childView != null;
                    initData(childView);
                    childView.setOnClickListener(this);
                }
            }
            groupContainer.addView(LayoutInflater.from(getContext()).inflate(R.layout.layout_me_item_divide, groupContainer, false));
            groupContainer.addView(view);
        }
    }

    private void initData(View childView) {

        ImageView userIcon;
        TextView userName;
        TextView userAccount;
        ImageView itemIcon;
        TextView itemTitle;

        Integer tag = (Integer) childView.getTag();
        MeItemBean meItemBean = mMeItemBeanList.get(tag);
        int itemType = meItemBean.getItemType();

        if (itemType == MeItemBean.HEADER_TYPE) {
            userIcon = childView.findViewById(R.id.user_icon);
            userIcon.setImageResource(meItemBean.getImageId());
            userName = childView.findViewById(R.id.user_name);
            userName.setText(meItemBean.getUserName());
            userAccount = childView.findViewById(R.id.user_account);
            userAccount.setText(meItemBean.getUserAccount());
        } else if (itemType == MeItemBean.NORMAL_TYPE) {
            itemIcon = childView.findViewById(R.id.item_icon);
            itemIcon.setImageResource(meItemBean.getImageId());
            itemTitle = childView.findViewById(R.id.item_title);
            itemTitle.setText(meItemBean.getItem_title());
        }
    }

    private void initItemData() {
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.user_icon, "孔天泽", "Nihao_Tofu", MeItemBean.HEADER_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_save, "收藏", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_me_like, "点赞", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "妹子", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_publish, "发表", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_contribution, "贡献", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_photo_album, "相册", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_emoji, "表情", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_setting, "设置", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_about, "关于", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));

        //依据分割线item进行分组
        for (int i = 0; i < mMeItemBeanList.size(); i++) {
            MeItemBean meItemBean = mMeItemBeanList.get(i);
            if (meItemBean.getItemType() == MeItemBean.DIVIDE_TYPE) {
                groupNum++;
            }

            if (meItemBean.getItemType() == MeItemBean.NORMAL_TYPE || meItemBean.getItemType() == MeItemBean.HEADER_TYPE) {
                meItemBean.setGroupId(groupNum);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), ""+(Integer)v.getTag(), Toast.LENGTH_SHORT).show();
    }
}
