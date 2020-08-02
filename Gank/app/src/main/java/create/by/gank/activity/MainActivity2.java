package create.by.gank.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import create.by.gank.R;
import create.by.gank.bean.MeItemBean;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rong_qi)
    LinearLayout rongQi;
    private List<MeItemBean> mMeItemBeanList = new ArrayList<>();
    private int groupNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initItemData();
        initView();
    }

    private void initView() {
        for (int i = 1; i < groupNum; i++) {
            if (i == 1) {
                rongQi.addView(LayoutInflater.from(this).inflate(R.layout.layout_me_item_divide, rongQi, false));
            }
            View view = LayoutInflater.from(this).inflate(R.layout.layout_me_item_group, rongQi, false);
            LinearLayout groupView = view.findViewById(R.id.me_item_group_container);
            for (int j = 0; j < mMeItemBeanList.size(); j++) {
                MeItemBean meItemBean = mMeItemBeanList.get(j);
                if (meItemBean.getGroupId() == j) {
                    int itemType = meItemBean.getItemType();
                    View childView = null;
                    switch (itemType) {
                        case MeItemBean.HEADER_TYPE:
                            childView = LayoutInflater.from(this).inflate(R.layout.layout_me_item_header, groupView, false);
                            childView.setTag(j);
                            break;
                        case MeItemBean.NORMAL_TYPE:
                            childView = LayoutInflater.from(this).inflate(R.layout.layout_me_item_normal, groupView, false);
                            childView.setTag(j);
                            break;
                    }
                    groupView.addView(childView);
                    assert childView != null;
                    initData(childView);
                    childView.setOnClickListener(this);
                }
            }
            rongQi.addView(view);
            rongQi.addView(LayoutInflater.from(this).inflate(R.layout.layout_me_item_divide, rongQi, false));
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
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_me, "孔天泽", "nihao", MeItemBean.HEADER_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo, "干货", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo, "干货", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo, "干货", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo, "干货", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo, "干货", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo, "干货", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo, "干货", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo, "干货", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_ganhuo, "干货", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "妹纸", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "妹纸", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "妹纸", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "妹纸", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "妹纸", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "哈哈哈哈", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "哈哈哈哈", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "哈哈哈哈", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "哈哈哈哈", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "哈哈哈哈", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "哈哈哈哈", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "你好鸭", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "你好鸭", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "你好鸭", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "你好鸭", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(R.drawable.ic_girl, "你好鸭", MeItemBean.NORMAL_TYPE));
        mMeItemBeanList.add(new MeItemBean(MeItemBean.DIVIDE_TYPE));

        //分组
        for (MeItemBean meItemBean : mMeItemBeanList) {
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
        Integer tag = (Integer) v.getTag();
        Log.e("tianze", "onClick: tag == > " + tag);
        Log.e("tianze", "onClick: tag == > " + mMeItemBeanList.get(tag).toString());
    }
}