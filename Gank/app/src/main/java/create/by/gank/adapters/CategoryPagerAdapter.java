package create.by.gank.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import create.by.gank.bean.GanHuoCategory;
import create.by.gank.fragments.GanHuoCategoryFragment;

public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    private List<GanHuoCategory.DataBean> data = new ArrayList<>();

    public CategoryPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        GanHuoCategoryFragment categoryFragment = new GanHuoCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", data.get(position).getType());
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.data.get(position).getTitle();
    }

    @Override
    public int getCount() {
        if (this.data.size() > 0) {
            return this.data.size();
        }
        return 0;
    }

    public void setData(List<GanHuoCategory.DataBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
