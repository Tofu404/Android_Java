package create.by.gank.presenters.impl;

import android.util.Log;

import java.net.HttpURLConnection;

import create.by.gank.base.BaseCallback;
import create.by.gank.bean.GanHuoCategory;
import create.by.gank.network.Api;
import create.by.gank.network.RetrofitBuilder;
import create.by.gank.presenters.GanHuoDataPresenter;
import create.by.gank.view_callback.GanHuoDataCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GanHuoDataPresenterImpl implements GanHuoDataPresenter {

    private static Api sApi = RetrofitBuilder.build();
    private GanHuoDataCallback callback = null;

    @Override
    public void load(String type, int pageNum, int countNum) {
        assert callback != null;
        sApi.getGanHuoCategory(type, pageNum, countNum).enqueue(new Callback<GanHuoCategory>() {
            @Override
            public void onResponse(Call<GanHuoCategory> call, Response<GanHuoCategory> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null && response.body().getData().size() > 0) {
                        callback.loaded(response.body().getData());
                    }
                } else {
                    // TODO: 2020/7/29 请求数据失败
                    callback.loadError();
                }
            }

            @Override
            public void onFailure(Call<GanHuoCategory> call, Throwable t) {
                // TODO: 2020/7/29 输出故障信息
                Log.e("tianze", "onFailure: 加载干货分类信息错误 == > " + t.getMessage());
                callback.loadError();
            }
        });
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void registerCallback(BaseCallback callback) {
        this.callback = (GanHuoDataCallback) callback;
    }

    @Override
    public void nuRegisterCallback() {

    }

}
