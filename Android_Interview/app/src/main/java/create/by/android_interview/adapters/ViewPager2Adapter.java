package create.by.android_interview.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import create.by.android_interview.base.BaseFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {

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
    private List<BaseFragment> fragments = new ArrayList<>();

    public ViewPager2Adapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager, sLifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getItemCount() {
        if (this.fragments.size()>0) {
            return this.fragments.size();
        }
        return 0;
    }

    public void setFragmentData(List<BaseFragment> fragments) {
        if (fragments != null) {
            this.fragments.clear();
            this.fragments.addAll(fragments);
            notifyDataSetChanged();
        }
    }
}
