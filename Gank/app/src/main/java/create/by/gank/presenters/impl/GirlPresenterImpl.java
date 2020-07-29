package create.by.gank.presenters.impl;

import android.util.Log;

import java.net.HttpURLConnection;

import create.by.gank.base.BaseCallback;
import create.by.gank.bean.GirlBean;
import create.by.gank.network.Api;
import create.by.gank.network.RetrofitBuilder;
import create.by.gank.presenters.GirlPresenter;
import create.by.gank.view_callback.GirlCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GirlPresenterImpl implements GirlPresenter {

    private GirlCallback callback = null;
    private static Api sBuild = RetrofitBuilder.build();

    @Override
    public void load(int currentPage, int dataNum) {
        sBuild.girlInfo(1, 10).enqueue(new Callback<GirlBean>() {
            @Override
            public void onResponse(Call<GirlBean> call, Response<GirlBean> response) {
                assert callback != null;
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    assert response.body() != null;
                    GirlBean girlBean = response.body();
                    if (girlBean.getData().size() > 0) {
                        //todo:将数据回调给界面
                        callback.loaded(girlBean.getData());
                    } else {
                        //todo:数据已经加载完成
                        callback.loadEmpty();
                    }
                } else {
                    //todo:链接不成功
                    callback.loadError();
                }
            }

            @Override
            public void onFailure(Call<GirlBean> call, Throwable t) {
                //todo:网络链接错误
                Log.e("tianze", "onFailure: == > " + t.getMessage());
                callback.loadError();
            }
        });
    }

    @Override
    public void loadMore(int pageNum,int countNum) {
        assert callback!=null;
        sBuild.girlInfo(pageNum,countNum).enqueue(new Callback<GirlBean>() {
            @Override
            public void onResponse(Call<GirlBean> call, Response<GirlBean> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    GirlBean body = response.body();
                    if (body!=null&&body.getData().size()>0){
                        callback.loadMore(body.getData());
                    }else {
                        callback.loadEmpty();
                    }
                }else {
                    callback.loadError();
                }
            }

            @Override
            public void onFailure(Call<GirlBean> call, Throwable t) {
                callback.loadError();
            }
        });
    }

    @Override
    public void refresh(int currentPage, int dataNum) {
        load(currentPage,dataNum);
    }


    @Override
    public void registerCallback(BaseCallback callback) {
        this.callback = (GirlCallback) callback;
    }

    @Override
    public void nuRegisterCallback() {
        this.callback = null;
    }
}
