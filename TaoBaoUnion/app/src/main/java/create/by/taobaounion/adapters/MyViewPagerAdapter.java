package create.by.taobaounion.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import create.by.taobaounion.modle.Categories;
import create.by.taobaounion.ui.fragments.CategoriesPageFragment;
import create.by.taobaounion.ui.fragments.MeFragment;

public class MyViewPagerAdapter extends FragmentPagerAdapter {


    private List<Categories.DataBean> data = new ArrayList<>();
    private Map<Integer, CategoriesPageFragment> mCategoriesPageFragmentMap = new HashMap<>();

    public MyViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (mCategoriesPageFragmentMap.get(position) == null) {
            CategoriesPageFragment categoriesItemFragment = new CategoriesPageFragment();
            mCategoriesPageFragmentMap.put(position, categoriesItemFragment);
            return categoriesItemFragment;
        } else {
            return Objects.requireNonNull(mCategoriesPageFragmentMap.get(position));
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return Math.max(this.data.size(), 0);
    }

    public void setData(List<Categories.DataBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
}
