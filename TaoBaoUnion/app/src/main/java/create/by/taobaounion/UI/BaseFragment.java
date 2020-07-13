package create.by.taobaounion.UI;

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
        int resourceId = setResourceId();
        View inflateView = inflater.inflate(resourceId, container, false);
        mBind = ButterKnife.bind(this, inflateView);
        return inflateView;
    }

    protected abstract int setResourceId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}
