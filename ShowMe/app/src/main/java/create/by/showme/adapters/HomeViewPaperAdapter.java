package create.by.showme.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import create.by.showme.beans.Categories;
import create.by.showme.fragments.PagerFragment;

public class HomeViewPaperAdapter extends FragmentStateAdapter {

    //使用map缓存fragment
    Map<Integer, Fragment> mFragmentMap = new HashMap<>();

    private static Lifecycle sLifecycle = new Lifecycle() {
        @Override
        public void addObserver(@NonNull LifecycleObserver observer) {

        }

        @Override
        public void removeObserver(@NonNull LifecycleObserver observer) {

        }

        @NonNull
        @Override
        public State getCurrentState() {
            return State.RESUMED;
        }
    };
    private List<Categories.DataBean> mDataBeans = new ArrayList<>();

    public HomeViewPaperAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager, sLifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (mFragmentMap.get(position) == null) {
            PagerFragment pagerFragment = new PagerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("materialId", mDataBeans.get(position).getId());
            bundle.putString("materialTitle", mDataBeans.get(position).getTitle());
            pagerFragment.setArguments(bundle);
            mFragmentMap.put(position, pagerFragment);
            return pagerFragment;
        } else {
            return Objects.requireNonNull(mFragmentMap.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return Math.max(mDataBeans.size(), 0);
    }

    public void setData(List<Categories.DataBean> data) {
        if (data != null) {
            mDataBeans.clear();
            mDataBeans.addAll(data);
            notifyDataSetChanged();
        }
    }
}
