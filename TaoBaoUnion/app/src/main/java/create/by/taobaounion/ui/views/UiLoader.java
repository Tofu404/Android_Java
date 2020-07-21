package create.by.taobaounion.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import create.by.taobaounion.R;

public abstract class UiLoader extends FrameLayout {

    private View mSucceedView;
    private View mFailView;
    private View mEmptyView;
    private View mErrorView;
    private Map<Status, View> mStatus2View;
    private View mLoadingView;

    public UiLoader(@NonNull Context context) {
        super(context);
        init();
    }

    protected  void init(){

        /**
         * 加载各个状态的view
         */
        mSucceedView = inflateSucceedView(this);
        mFailView = inflateView(R.layout.layout_fail);
        mEmptyView = inflateView(R.layout.layout_empty);
        mErrorView = inflateView(R.layout.layout_error);
        mLoadingView = inflateView(R.layout.layout_loading);

        /**
         * 将状态枚举变量和view关联起来
         */
        mStatus2View = new HashMap<>();
        mStatus2View.put(Status.EMPTY,mEmptyView);
        mStatus2View.put(Status.ERROR,mErrorView);
        mStatus2View.put(Status.FAIL,mFailView);
        mStatus2View.put(Status.SUCCEED,mSucceedView);
        mStatus2View.put(Status.LOADING,mLoadingView);

        /**
         * 将加载好的view添加进来
         */
        for (Status status : mStatus2View.keySet()) {
            addView(mStatus2View.get(status));
        }

        /**
         * 默认显示为空
         */
        showViewByStatus(Status.NONE);
    }

    public void showViewByStatus(Status status){
        for (Status s : mStatus2View.keySet()) {
            View view = mStatus2View.get(s);
            assert view != null;
            if (status == s){
                view.setVisibility(VISIBLE);
            }else {
                view.setVisibility(GONE);
            }
        }
    }

    private View inflateView(int resourceId){
        return LayoutInflater.from(getContext()).inflate(resourceId,this,false);
    }

    public enum Status{
        SUCCEED,FAIL,EMPTY, NONE, ERROR,LOADING
    }

    public abstract View inflateSucceedView(View rootView);
}
