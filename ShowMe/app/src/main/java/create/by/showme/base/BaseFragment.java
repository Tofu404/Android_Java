package create.by.showme.base;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutId(), container, false);
        mBind = ButterKnife.bind(this, view);
        init();
        return view;
    }

    //设置Layout文件
    public abstract int setLayoutId();

    //fragment在这初始化
    public abstract void init();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}
