package create.by.gank.presenters.impl;

import android.util.Log;

import java.net.HttpURLConnection;
import java.util.List;

import create.by.gank.base.BaseCallback;
import create.by.gank.bean.GanHuoCategory;
import create.by.gank.network.RetrofitBuilder;
import create.by.gank.presenters.GanHuoPresenter;
import create.by.gank.view_callback.GanHuoCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GanHuoPresenterImpl implements GanHuoPresenter {

    private GanHuoCallback mGanHuoCallback = null;

    @Override
    public void load() {
        RetrofitBuilder.build().getGanHuoCategory().enqueue(new Callback<GanHuoCategory>() {
            @Override
            public void onResponse(Call<GanHuoCategory> call, Response<GanHuoCategory> response) {
                assert mGanHuoCallback != null;
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    List<GanHuoCategory.DataBean> data = response.body().getData();
                    if (data != null && data.size() > 0) {
                        mGanHuoCallback.loaded(data);
                    }
                } else {
                    // TODO: 2020/7/29 链接失败
                }
            }

            @Override
            public void onFailure(Call<GanHuoCategory> call, Throwable t) {
                Log.e("tianze", "onFailure: 返回信息== > "+t.getMessage() );
            }
        });
    }

    @Override
    public void registerCallback(BaseCallback callback) {
        mGanHuoCallback = (GanHuoCallback) callback;
    }

    @Override
    public void nuRegisterCallback() {
        mGanHuoCallback = null;
    }
}
