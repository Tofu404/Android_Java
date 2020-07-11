package com.tianze.xiangting.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tianze.xiangting.R;
import com.tianze.xiangting.base.BaseApplication;

public abstract class UILoader extends FrameLayout {

    public static UIState CurrentState = UIState.NONE;
    private View lodingView;
    private View successView;
    private View networkErrorView;
    private View emptyView;

    private OnNetworkErrorViewClickListener onNetworkViewClickListener = null;

    public UILoader(@NonNull Context context) {
        this(context, null);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setCurrentState(UIState state) {
        CurrentState = state;
        //在主线程上更新UI
        assert BaseApplication.getHandle() != null;
        BaseApplication.getHandle().post(new Runnable() {
            @Override
            public void run() {
                switchUIByCurrentState();
            }
        });
    }

    private void init() {
        switchUIByCurrentState();
    }

    private void switchUIByCurrentState() {

        //添加正在加载页面
        if (lodingView == null) {
            lodingView = getLoadingView();
            addView(lodingView);
        }
        //根据CurrentState决定是否显示和隐藏正在加载页面
        lodingView.setVisibility(CurrentState == UIState.LOADING ? VISIBLE : GONE);

        //加载成功页面
        if (successView == null) {
            successView = getSuccessView(this);
            addView(successView);
        }
        //根据CurrentState决定是否显示和隐藏正在加载页面
        successView.setVisibility(CurrentState == UIState.SUCCESS ? VISIBLE : GONE);

        //添加网络错误页面
        if (networkErrorView == null) {
            networkErrorView = getNetworkErrorView();
            addView(networkErrorView);
        }
        //根据CurrentState决定是否显示和隐藏正在加载页面
        networkErrorView.setVisibility(CurrentState == UIState.NETWORK_ERRPR ? VISIBLE : GONE);

        //添加正在加载页面
        if (emptyView == null) {
            emptyView = getEmptyView();
            addView(emptyView);
        }
        //根据CurrentState决定是否显示和隐藏正在加载页面
        emptyView.setVisibility(CurrentState == UIState.EMPTY ? VISIBLE : GONE);
    }

    protected View getEmptyView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.layout_empty_view, this, false);
    }

    protected View getNetworkErrorView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_network_error_view, this, false);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNetworkViewClickListener != null) {
                    onNetworkViewClickListener.onNetworkViewClick();
                }
            }
        });
        return view;
    }

    public abstract View getSuccessView(ViewGroup container);

    protected View getLoadingView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.layout_laoding_view, this, false);
    }

    public void setOnNetworkViewClickListener(OnNetworkErrorViewClickListener listener) {
        this.onNetworkViewClickListener = listener;
    }

    public enum UIState {
        LOADING, SUCCESS, NETWORK_ERRPR, EMPTY, NONE
    }

    public interface OnNetworkErrorViewClickListener {
        void onNetworkViewClick();
    }
}
