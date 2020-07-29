package create.by.gank.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder mBind;
    private BasePresenter mBasePresenter = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutResource(), container, false);
        mBind = ButterKnife.bind(this, view);
        init();
        mBasePresenter = initPresenter();
        return view;
    }

    protected abstract void init();

    //初始化presenter
    protected BasePresenter initPresenter() {
        return null;
    }

    public abstract int setLayoutResource();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
        if (mBasePresenter != null) {
            mBasePresenter.nuRegisterCallback();
        }
    }
}
